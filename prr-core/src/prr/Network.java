package prr;

import java.io.Serializable;
import java.io.IOException;
import prr.exceptions.UnrecognizedEntryException;

import java.util.Map;
import java.util.TreeMap;
import java.util.List;
import java.util.ArrayList;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileReader;

import prr.clients.*;
import prr.terminals.*;
import prr.comms.*;
import prr.terminal_state.*;
import prr.notifications.Notification;
 
//export CVSROOT=:ext:ist195615@sigma.tecnico.ulisboa.pt:/afs/ist.utl.pt/groups/leic-po/po22/cvs/143
//export CVS_RSH=ssh
//cvs commit -m "new commit"
//export CLASSPATH=$(pwd)/po-uilib/po-uilib.jar:$(pwd)/project/prr-core/prr-core.jar:$(pwd)/project/prr-app/prr-app.jar
//sh ./runtests.sh

/**
 * Class Store implements a store.
 */
public class Network implements Serializable{

	/**
	 * Serial number for serialization.
	 */
	private static final long serialVersionUID = 202208091753L;

	private Map<String, Client> _clients = new TreeMap<String, Client>(String.CASE_INSENSITIVE_ORDER);
	private Map<String, Terminal> _terminals = new TreeMap<String, Terminal>();
	private List<Comm> _comms = new ArrayList<Comm>();

	private long _payments = 0;
	private long _debts = 0;

	private int _num_comms = 0;

	private Notification notification = new Notification();
	/**
	 * Register entities Client through given fields arguments.
	 *
	 * @param filename name of the text input file
	 */
	public void registerClient(String[] fields){
		if(_clients.containsKey(fields[1])){
			return;
		}
		Client c = new Client(fields[1], fields[2], fields[3]);
		_clients.put(fields[1], c);
	}
	/**
	 * Register entities Terminal through given fields arguments.
	 *
	 * @param fields of a terminal
	 */
	public void registerTerminal(String[] fields){
		if(_terminals.containsKey(fields[1]) || !(_clients.containsKey(fields[2]))){
			return;
		}
		Terminal j;
		// Implement selection of basic or fancy terminal
		if( fields[0].equals("BASIC")){
			j = new BasicTerminal(fields[0], fields[1], fields[2], fields[3]);
		} else{
			j = new FancyTerminal(fields[0], fields[1], fields[2], fields[3]);
		}
		_terminals.put(fields[1],j);

		Client c = showClient(fields[2]);

		c.addTerminal(j);
		
	}
	/**
	 * Method that Returns Map<String, Client> with all clients.
	 */
	public Map<String, Client> getAllClients(){
		return _clients;
	}
	/**
	 * Method that Returns Map<String, Terminal> with all Terminals.
	 */
	public Map<String, Terminal> getAllTerminals(){
		return _terminals;
	}
	/**
	 * Method that Returns private long _payments.
	 */
	public long getPayments(){
		return _payments;
	}
	/**
	 * Method that Returns private long _debts.
	 */
	public long getDebts(){
		return _debts;
	}
	/**
	 * Method that Returns Map<String,Terminal> of unused terminals.
	 */
	public List<String> getAllUnusedTerms(){
		List<String> unused = new ArrayList<String>();

		for (Map.Entry<String, Terminal> terminal : _terminals.entrySet()) {
			if(!terminal.getValue().hasChanged()){   // If it has not changed, it is unused
				unused.add(terminal.getValue().toString());
			}
		}
		return unused;
	}
	/**
	 *Returns the Client to which the specified key is mapped, or null if this map contains no mapping for the key.
	 *
	 * @param field to search for Client
	 */
	public Client showClient(String field) {
		return _clients.get(field);
	}
	/**
	 * Returns the Terminal to which the specified key is mapped, or null if this map contains no mapping for the key.
	 *
	 * @param terminal_id which correspondes to a terminal_id. 
	 */
	public Terminal showTerminal(String terminal_id){
		return _terminals.get(terminal_id);
	}
	/**
	 *Method to Register either a Client,Friends or Terminal based on fields[0] string contents.
	 * @param fields which may vary between  CLIENT, FRIENDS or type of terminal to be attempted to be registered.
	 */
	private void registerEntry(String... fields) throws UnrecognizedEntryException {
		Terminal terminal = null;
		Terminal friend = null;
        int cont = 0;

		if(fields[0].equals("CLIENT") ){
			registerClient(fields);
		} else if (fields[0].equals("BASIC") || fields[0].equals("FANCY")){
			registerTerminal(fields);
		}
		else if(fields[0].equals("FRIENDS") ){
			terminal = showTerminal(fields[1]);
			
			for(String friendKey: fields){
				if(cont >=2 && friendKey != null && !friendKey.equals("")){
					terminal.addFriend(friendKey);
				}
				cont++;
			}
		} else {
			throw new UnrecognizedEntryException(fields[0]);
		}
	}
 
	/**
	 * Read text input file and create corresponding domain entities.
	 *
	 * @param filename name of the text input file
	 * @throws UnrecognizedEntryException if some entry is not correct
	 * @throws IOException if there is an IO erro while processing the text file
	 */
	void importFile(String filename) throws UnrecognizedEntryException, IOException{
		try {
			BufferedReader br = new BufferedReader(new FileReader(filename));
			String line;
			while ((line = br.readLine()) != null) {
				String[] fields = line.split("\\|");
				try {
					registerEntry(fields);
				} catch (UnrecognizedEntryException e) {
					System.out.println(e.getMessage());
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Method that Returns List<String> of clients without debt.
	 */
	public List<String> getAllClientsWithoutDebt(){
		List<String> noDebt = new ArrayList<String>();

        for (Map.Entry<String, Client> j : _clients.entrySet()) {
            if(j.getValue().getDebts() == 0){
                noDebt.add(j.getValue().toString());
            }
        }
		return noDebt;
    }
	/**	
	 * Method that Returns List<String> of clients with debt.
	 */
    public List<String> getAllClientsWithDebt(){
		List<String> withDebt = new ArrayList<String>();

        for (Map.Entry<String, Client> j : _clients.entrySet()) {
            if(j.getValue().getDebts() != 0){
                withDebt.add(j.getValue().toString());
            }
        }
		return withDebt;
    }
	/**
	 * Method that Returns the number of comms;
	 */
	public int getNumComms(){
		return _num_comms;
	}
	/**
	 * Method that Returns List<Comm> of all communications.
	 */
	public List<Comm> getAllComms(){
		return _comms;
	}
	/**
	 * Method that adds a communication to the number of communications.
	 *  @param Comm Comm to be added.
	 */
	public void addComm(Comm comm){
		_comms.add(comm);
		_num_comms++;
	}
	/**
	 * Method that Creates Text Communication.
	 *  @param sender Terminal that called the method.
	 *  @param receiver Terminal that will receive the methods changes.
	 *  @param message Messsage to be passed on to Receiver.
	 */
	public void createTextComm(Terminal sender, Terminal receiver, String message){

		Client client = showClient(sender.getClientID());

		Comm comm = new TextComm(this, sender, receiver, client, message);
		comm.setUnits(message.length());
		long price = 0;

		client.newComm();
		sender.newComm();
		receiver.newComm();

		// Evita exceções em alguns testes
		if(message != null)
			// ao calcular o preço, já adiciona a dívida ao client
			price = client.calculatePrice(message.length() );

		addDebtToAll(client, sender, comm, price);
	}

	/**
	 * Method that creates Interactive Communication.
	 *  @param sender Terminal that called the method.
	 *  @param receiver Terminal that will receive the methods changes.
	 *  @param type Type of Communication that will be created.
	 */
	public void createInteractiveComm(Terminal sender, Terminal receiver, String type){

		Client client = showClient(sender.getClientID());
		Comm comm;

		if(type.equals("VOICE"))
			comm = new VoiceComm(this, sender, receiver, client);
		else
			comm = new VideoComm(this, sender, receiver, client);

		client.newComm();
		sender.newComm();
		receiver.newComm();

		StateManager sender_man = sender.getStateManager();
		sender.setLastState(sender_man.getState() );

		sender_man.setState(new Busy(sender_man));

		StateManager receiver_man = receiver.getStateManager();
		receiver.setLastState(receiver_man.getState() );

		receiver_man.setState(new Busy(receiver_man));

		sender.sender();
	}

	/**
	 * 	Method that ends Interactive Communication.
	 *  @param sender Terminal that called the method.
	 *  @param Client Client whose communication has ended.
	 *  @param comm Communication to be ended.
	 *	@param time Time taken to perform the task.
	 *	@param type Type of communication.
	 */
	public long endInteractiveComm(Terminal sender, Client client, Comm comm, long time, String type){
       
        long price = client.calculatePrice(time, type);
        addDebtToAll(client, sender, comm, price);
        sender.noSender();
		
		comm.setUnits(time);
		
		StateManager sender_man = sender.getStateManager();
		if(sender.getLastState().equals("IDLE") )
			notification.busy_to_idle(sender);
		else
			sender_man.setState(new Silent(sender_man));
			
		StateManager receiver_man = comm.receiver().getStateManager();
		notification.busy_to_idle(comm.receiver() );

		comm.turnOff();
		return price;
    }


	/**
	 * 	Method that adds debt to a client and the terminal.
	 *  @param Client Client whose debt must be changed.
	 *  @param sender Terminal that whose debt must changed.
	 *  @param comm Communication to be ended.
	 *	@param price Price to perform the task.
	 */
	public void addDebtToAll(Client client, Terminal sender, Comm comm, long price){
		// Adiciona as dívidas de todos
		_debts += price;
		client.addDebt(price);
		sender.addDebt(price);

		comm.addPrice(price);
	}
	/**
	 * 	Method that pays a Communication, updating Terminals debt and payments.
	 *  @param Client Client whose debt must be changed.
	 *  @param sender Terminal that whose debt must changed.
	 *  @param comm Communication to be ended.
	 *	@param price Price to perform the task.
	 */
	public void payCommToAll(Client client, Terminal sender, Comm comm, long price){
		// Adiciona as dívidas de todos
		_debts -= price;
		_payments += price;

		client.pay(price);

		sender.pay(price);

		comm.pay();
	}
	/**
	 * 	Method that Returns the communication of the provided id.
	 *  @param id Id to be used in order to fetch the communication.
	 */
	public Comm getComm(int id){
		return _comms.get(id);
	}
	/**
	 * 	Method that sets a communcation as failed.
	 *  @param sender Terminal who tries to send a Communication
	 *  @param receiver Terminal who is unnavailable to enter in Comm.
	 */
	public void failedComm(Terminal sender, Terminal receiver){
		receiver.setFailedID(sender.getKey() );
	}
}
package prr.terminals;

import java.io.Serializable;

import java.util.Arrays;

import prr.Network;
import prr.terminal_state.*;
import prr.comms.*;
import prr.clients.*;

import java.util.ArrayList;
// FIXME add more import if needed (cannot import from pt.tecnico or prr.app)
import java.util.List;
import java.util.ArrayList;
import java.util.TreeSet;
import java.util.Set;
import java.lang.Math;

/**
 * Abstract terminal.
 */
abstract public class Terminal implements Serializable /* FIXME maybe addd more interfaces */{

	/** Serial number for serialization. */
	private static final long serialVersionUID = 202208091753L;

        private Set<String> friendsList = new TreeSet<>();
        private List<Comm> _comms = new ArrayList<Comm>();
        private List<String> failedIDs = new ArrayList<String>();
        private List<String> failedTextIDs = new ArrayList<String>();
        private String _id = "";
        private String _client_id = "";

        // Used in clients
        private long _payments = 0;
        private long _debts = 0;

        // Caso em call, é o sender (para saber se pode terminar)?
        private boolean _sender = false;

        // Ainda não realizou Comms
        private int _num_comms = 0;
        private int num_friends = 0;

        private StateManager _manager = new StateManager();

        private String last_state;

        public Terminal(String type, String id, String clientId, String state){
           
                _manager.setTerminal(this);
                
                if(state.equals("OFF") ){
                        _manager.toOff();
                }
                else if(state.equals("SILENCE") ){
                        _manager.toSilent();
                }
                
                _id = id;
                _client_id = clientId;
        }

        /**
         * Checks if this terminal can end the current interactive communication.
         *
         * @return true if this terminal is busy (i.e., it has an active interactive communication) and
         *          it was the originator of this communication.
         **/
        public boolean canEndCurrentCommunication() {
                String state = _manager.getState();

                if(state.equals("BUSY") && _sender)
                        return true;
                
                return false;
        }

        /**
         * Checks if this terminal can start a new communication.
         *
         * @return true if this terminal is neither off neither busy, false otherwise.
         **/
        public boolean canStartCommunication() {
                String state = _manager.getState();

                if(state.equals("OFF") || state.equals("BUSY") )
                        return false;
                
                return true;
        }
        public String getKey(){
                return _id;
        }

        public long getPayments(){
                return _payments;
        }
        public long getDebts(){
                return _debts;
        }

        public boolean hasChanged(){
                return _num_comms>0;
        }

        public String toString(String type){
                String out =  type + "|" + _id + "|" + _client_id + "|" + _manager.getState() + "|" + String.valueOf(_payments) + "|" + String.valueOf(_debts);
                
                // Show friends list
                if(num_friends > 0){
                        out += "|";
                        for(String id: friendsList) {
                                out += id;
                                out += ",";
                        }
                        out = out.substring(0, out.length() - 1);
                }
                return out;
        }

        public StateManager getStateManager(){
                return _manager;
        }

        public boolean inFriendsList(String key){
                for(String friend: friendsList)
			if(friend.equals(key))
				return true;      
                return false;
        }
        public void addFriend(String key){
                friendsList.add(key);
                num_friends++;
        }
        public void removeFriend(String key){
                for(String friend: friendsList)
                        if(friend.equals(key)){
                                friendsList.remove(key);
                                break;
                        }
        }

        public void addComm(Comm comm){
                _comms.add(comm);
        }

        public String getClientID(){
                return _client_id;
        }

        public void addDebt(long price){
                _debts += price;
        }
        public void pay(long price){
                _debts -= price;
                _payments += price;
        }
        public String getState(){
                return _manager.getState();
        }
        public void newComm(){
                _num_comms++;
        }
        public int getNumComms(){
                return _num_comms;
        }
        public Comm getComm(int index){
                return _comms.get(index);
        }
        public void sender(){
                // Sended started comm
                _sender = true;
        }
        public void noSender(){
                // Sender ended comm
                _sender = false;
        }

        public Comm findOngoing(){
                boolean found = false;
                Comm comm = null;

		for(int i = _num_comms - 1; i >= 0; i--){
			comm = _comms.get(i);

			if( comm.getState().equals("ONGOING") ){
				found = true;
				break;
			}
		}

		if(found)
			return comm;
                return null;
        }
        public void setLastState(String state){
                last_state = state;
        }
        public String getLastState(){
                return last_state;
        }
        public void setFailedID(String id){
                failedIDs.add(id);
        }
        public List<String> getFailedIDs(){
                return failedIDs;
        }

        public void setState(Network _net, State mode, String current_mode){
               _manager.setState(mode);

               if(_manager.getState().equals("IDLE")){
                        for(String id_: failedIDs){
                                Client client = _net.showClient(_net.showTerminal(id_).getClientID() );
                                client.createNotific(current_mode, "IDLE", this.getKey() );
                        }
                        for(String id_: failedTextIDs){
                                Client client = _net.showClient(_net.showTerminal(id_).getClientID() );
                                client.createNotific(current_mode, "IDLE", this.getKey() );
                        }
               }
               else if(current_mode.equals("OFF")){
                for(String id_: failedTextIDs){
                        Client client = _net.showClient(_net.showTerminal(id_).getClientID() );
                        client.createNotific(current_mode, "IDLE", this.getKey() );
                }
       }

        }       
}

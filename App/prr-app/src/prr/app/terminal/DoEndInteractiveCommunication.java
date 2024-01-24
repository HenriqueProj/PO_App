package prr.app.terminal;

import prr.Network;
import prr.terminal_state.*;
import prr.clients.Client;
import prr.terminals.Terminal;
import prr.comms.*;

import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.CommandException;
import pt.tecnico.uilib.Display;

//FIXME add more imports if needed

/**
 * Command for ending communication.
 */
class DoEndInteractiveCommunication extends TerminalCommand {

	private Form form = new Form();
	private Display display = new Display();
	private Client client;
	private Network net;
	private Terminal sender;
	private Comm comm;
	private String comm_type;
	private StateManager _manager;
    DoEndInteractiveCommunication(Network context, Terminal terminal) {
        super(Label.END_INTERACTIVE_COMMUNICATION, context, terminal, receiver -> receiver.canEndCurrentCommunication());
		sender = terminal;
		net = context;
    }

    @Override
    protected final void execute() throws CommandException {
        //FIXME implement command
        long time = form.requestInteger(Prompt.duration());
		long price = 0;

		comm = sender.findOngoing();
		client = net.showClient( sender.getClientID() );
		comm_type = comm.type();
		_manager = sender.getStateManager();
		if(comm != null){
			if(!comm_type.equals( client.lastComm() ) )
				client.lastCommType(comm_type );	
			else
				client.straightComm();
		}
		price = _network.endInteractiveComm(sender, client, comm, time, comm_type);

		comm.turnOff();

        display.popup(Message.communicationCost( Math.round(price) ));
    }
}
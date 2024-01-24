package prr.app.terminal;

import prr.Network;
import prr.comms.*;
import prr.comm_state.*;
import prr.terminals.Terminal;
import pt.tecnico.uilib.menus.CommandException;
import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.Display;
/**
 * Perform payment.
 */
class DoPerformPayment extends TerminalCommand {

	private Terminal _sender;
	private Network _net;
	private Form form = new Form();
	private Display display = new Display();

	DoPerformPayment(Network context, Terminal terminal) {
		super(Label.PERFORM_PAYMENT, context, terminal);
		_net = context;
		_sender = terminal;
	}

	@Override
	protected final void execute() throws CommandException {
           int commID = form.requestInteger( Prompt.commKey() );

		   Comm comm = _net.getComm(commID - 1);

		   	if(!comm.getSenderKey().equals( _sender.getKey() ) || comm.getState().equals("FINISHED") || comm.isPaid() )
				display.popup(Message.invalidCommunication());
			else{
				_net.payCommToAll(_net.showClient(_sender.getClientID() ), _sender, comm, comm.getPrice() );
			}
			
	}
}

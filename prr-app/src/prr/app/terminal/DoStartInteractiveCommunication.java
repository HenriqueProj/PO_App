package prr.app.terminal;

import prr.Network;
import prr.clients.*;

import prr.app.exceptions.UnknownTerminalKeyException;
import prr.terminals.Terminal;
import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.CommandException;
import pt.tecnico.uilib.Display;

/**
 * Command for starting communication.
 */
class DoStartInteractiveCommunication extends TerminalCommand {

	private Network _net;
	private Terminal _sender;
	private Display display = new Display();
	private Form form = new Form();

	DoStartInteractiveCommunication(Network context, Terminal terminal) {
		super(Label.START_INTERACTIVE_COMMUNICATION, context, terminal, receiver -> receiver.canStartCommunication());
		_net = context;
		_sender = terminal;
	}

	@Override
	protected final void execute() throws CommandException {
        String rec_key = form.requestString( Prompt.terminalKey() );
		Terminal receiver = _net.showTerminal(rec_key);
		Client client;

		String type = form.requestOption(Prompt.commType(), "VOICE", "VIDEO");
			
		if(receiver == null)
			throw new UnknownTerminalKeyException(rec_key);
		else{
			String senderState = _sender.getState();
			String receiverState = receiver.getState();

			if(senderState.equals("BUSY") || senderState.equals("OFF") )
				display.popup(Message.unsupportedAtOrigin( _sender.getKey(), senderState ) );

			else if(receiverState.equals("OFF")){
				display.popup(Message.destinationIsOff(rec_key));
				_net.failedComm(_sender, receiver);
			}
			else if(receiverState.equals("BUSY")){
				display.popup(Message.destinationIsBusy(rec_key) );
				_net.failedComm(_sender, receiver);
			
			}
			else if(receiverState.equals("SILENCE")){
				display.popup(Message.destinationIsSilent(rec_key) );
				_net.failedComm(_sender, receiver);
			}
			else
				_net.createInteractiveComm(_sender, receiver, type);
		}
	}
}

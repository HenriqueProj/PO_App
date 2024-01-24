package prr.app.terminal;

import prr.Network;
import prr.terminals.Terminal;
import prr.comms.*;
import prr.comm_state.*;

import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.CommandException;
import pt.tecnico.uilib.Display;
//FIXME add more imports if needed

/**
 * Command for showing the ongoing communication.
 */
class DoShowOngoingCommunication extends TerminalCommand {

	private Network _net;
    private Terminal _terminal;

    private Display display = new Display();
    private Form form = new Form();

	private Comm comm;

	DoShowOngoingCommunication(Network context, Terminal terminal) {
		super(Label.SHOW_ONGOING_COMMUNICATION, context, terminal);
		_net = context;
		_terminal = terminal;
	}

	@Override
	protected final void execute() throws CommandException {
		comm = _terminal.findOngoing();

		if(comm == null)
			display.popup( Message.noOngoingCommunication() );
		else
			display.popup(comm); 
		
	}
}

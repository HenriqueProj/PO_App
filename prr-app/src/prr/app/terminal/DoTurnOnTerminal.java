package prr.app.terminal;

import prr.Network;
import prr.terminals.Terminal;
import prr.notifications.*;

import pt.tecnico.uilib.menus.CommandException;
import pt.tecnico.uilib.Display;

import prr.terminal_state.*;

/**
 * Turn on the terminal.
 */
class DoTurnOnTerminal extends TerminalCommand {
	private Terminal _terminal;
	private Network _net;
	private Display display = new Display();
	private Notification notification = new Notification();

	DoTurnOnTerminal(Network context, Terminal terminal) {
		super(Label.POWER_ON, context, terminal);

		_net = context;
		_terminal = terminal; 
	}
	
	@Override
	protected final void execute() throws CommandException {

		String state = _terminal.getState();

		if(state.equals("IDLE")){
			display.popup(Message.alreadyOn());
		}else if(state.equals("OFF")){
			_terminal.setState(_net, new Idle(_terminal.getStateManager()), "OFF");

		}else{
			_terminal.setState(_net, new Idle(_terminal.getStateManager()), "SILENCE");
		}
	}
}

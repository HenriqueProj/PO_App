package prr.app.terminal;

import prr.Network;
import prr.terminal_state.StateManager;

import prr.terminals.Terminal;
import pt.tecnico.uilib.menus.CommandException;
import pt.tecnico.uilib.Display;
/**
 * Turn off the terminal.
 */
class DoTurnOffTerminal extends TerminalCommand {

	private Terminal _terminal;
	private Display display = new Display();
	DoTurnOffTerminal(Network context, Terminal terminal) {
		super(Label.POWER_OFF, context, terminal);

		_terminal = terminal;
	}

	@Override
	protected final void execute() throws CommandException {
        
		String state = _terminal.getState();

		if(state.equals("OFF"))
			display.popup(Message.alreadyOff());
		else
			_terminal.getStateManager().toOff();
	}
}

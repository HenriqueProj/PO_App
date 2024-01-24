package prr.app.terminal;

import prr.Network;
import prr.terminal_state.StateManager;
import prr.terminals.Terminal;
import pt.tecnico.uilib.menus.CommandException;
import pt.tecnico.uilib.Display;

/**
 * Silence the terminal.
 */
class DoSilenceTerminal extends TerminalCommand {

	private Terminal _terminal;
	private Display display = new Display();
	DoSilenceTerminal(Network context, Terminal terminal) {
		super(Label.MUTE_TERMINAL, context, terminal);

		_terminal = terminal;

	}

	@Override
	protected final void execute() throws CommandException {
		
		String state = _terminal.getState();

		if(state.equals("SILENCE"))
			display.popup(Message.alreadySilent());
		else
			_terminal.getStateManager().toSilent();
	}
}

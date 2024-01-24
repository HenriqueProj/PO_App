package prr.app.terminal;

import prr.Network;
import prr.terminals.*;
import prr.terminals.Terminal;
import prr.app.exceptions.UnknownTerminalKeyException;

import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.CommandException;
import pt.tecnico.uilib.Display;
//FIXME add more imports if needed

/**
 * Remove friend.
 */
class DoRemoveFriend extends TerminalCommand {

	private Network _net;
    private Terminal _terminal;

    private Display display = new Display();
    private Form form = new Form();

	DoRemoveFriend(Network context, Terminal terminal) {
		super(Label.REMOVE_FRIEND, context, terminal);
			_net = context;
			_terminal = terminal;
	}

	@Override
	protected final void execute() throws CommandException {
        String key = form.requestString(Prompt.terminalKey());

		if(_net.showTerminal(key) == null)
			throw new UnknownTerminalKeyException(key);	
		
		if(_terminal.inFriendsList(key))
			_terminal.removeFriend(key);
	}
}

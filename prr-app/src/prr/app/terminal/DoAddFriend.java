package prr.app.terminal;

import prr.Network;
import prr.app.exceptions.UnknownTerminalKeyException;
import prr.terminals.Terminal;
import pt.tecnico.uilib.menus.CommandException;

import pt.tecnico.uilib.forms.Form;
/**
 * Add a friend.
 */
class DoAddFriend extends TerminalCommand {

	private Terminal _terminal;
	private Form form = new Form();
	private Network _net;
	private Terminal _friend;

	DoAddFriend(Network context, Terminal terminal) {
		super(Label.ADD_FRIEND, context, terminal);
		_terminal = terminal;
		_net = context;
	}

	@Override
	protected final void execute() throws CommandException {
		String key = form.requestString(Prompt.terminalKey());

		_friend = _net.showTerminal(key);
		if(_friend == null)
			throw new UnknownTerminalKeyException(key);	
		
		if(!_terminal.inFriendsList(key) && !key.equals(_terminal.getKey() )){
			_terminal.addFriend(key);
		}
	}
}

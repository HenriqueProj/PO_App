package prr.app.terminals;

import prr.terminals.*;
import prr.Network;
import pt.tecnico.uilib.forms.Form;

import prr.app.exceptions.*;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

import prr.app.terminal.Menu;

/**
 * Open a specific terminal's menu.
 */
class DoOpenMenuTerminalConsole extends Command<Network> {

	private Network _network;
	private Menu _menu;
	private String _key;
	private Terminal _terminal;
	private Form form = new Form();

	DoOpenMenuTerminalConsole(Network receiver) {
		super(Label.OPEN_MENU_TERMINAL, receiver);
		//FIXME add command fields
		_network = receiver;
	}

	@Override
	protected final void execute() throws CommandException, TerminalNotProvidedException {
                //FIXME implement command
		_key = form.requestString(Prompt.terminalKey());
		_terminal = _network.showTerminal(_key);
///todo exception terminal null adicionar e fazer o catch
		if (_terminal == null) {
			throw new TerminalNotProvidedException(_key);
		}
		else{
        	_menu = new Menu(_network, _terminal);
			_menu.open();
		}
	}
}
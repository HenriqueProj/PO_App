package prr.app.terminals;

import prr.Network;
import prr.terminals.Terminal;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

import java.util.Map;
import java.util.TreeMap;



/**
 * Show all terminals.
 */
class DoShowAllTerminals extends Command<Network> {

	DoShowAllTerminals(Network receiver) {
		super(Label.SHOW_ALL_TERMINALS, receiver);
	}

	@Override
	protected final void execute() throws CommandException {
		Map<String, Terminal> new_receiver = _receiver.getAllTerminals();
		for (Terminal j : new_receiver.values()) {
			_display.addLine(j);
		}
		_display.display();
	}
}
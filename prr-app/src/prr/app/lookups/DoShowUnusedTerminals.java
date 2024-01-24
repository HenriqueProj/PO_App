package prr.app.lookups;

import prr.Network;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import prr.terminals.Terminal;

import pt.tecnico.uilib.Display;

/**
 * Show unused terminals (without communications).
 */
//Since for the second submission all terminals shall have the same state, the function works as a DoShowAllTerminals
//However for the third it will be checked a list of used terminals in wich we will extract from the list of the ones to be displayed
class DoShowUnusedTerminals extends Command<Network> {

	private Display _display = new Display();

	DoShowUnusedTerminals(Network receiver) {
		super(Label.SHOW_UNUSED_TERMINALS, receiver);
	}
	//Todo chama showUnusedterminals
	@Override
	protected final void execute() throws CommandException {
		List<String> new_receiver = (List<String>) _receiver.getAllUnusedTerms();
		for (String t : new_receiver) {
				_display.addLine(t);
			
		}
		_display.display();
	}
}

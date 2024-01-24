package prr.app.lookups;

import prr.Network;
import prr.clients.Client;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

import java.util.List;
import java.util.ArrayList;
import pt.tecnico.uilib.Display;

/**
 * Show clients with negative balance.
 */
class DoShowClientsWithDebts extends Command<Network> {

	private Network _receiver;
	private Display _display = new Display(); 

	DoShowClientsWithDebts(Network receiver) {
		super(Label.SHOW_CLIENTS_WITH_DEBTS, receiver);
		_receiver = receiver;
	}

	@Override
	protected final void execute() throws CommandException {
        List<String> _clients = _receiver.getAllClientsWithDebt();

		for(String s : _clients)
			_display.addLine(s);
			
		_display.display();
	}
}

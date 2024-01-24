package prr.app.lookups;

import prr.Network;
import prr.clients.Client;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

import java.util.List;
import java.util.ArrayList;

import pt.tecnico.uilib.Display;

/**
 * Show clients with positive balance.
 */
class DoShowClientsWithoutDebts extends Command<Network> {

	private Network _receiver;
	private Display _display = new Display(); 

	DoShowClientsWithoutDebts(Network receiver) {
		super(Label.SHOW_CLIENTS_WITHOUT_DEBTS, receiver);
		_receiver = receiver;
	}

	@Override
	protected final void execute() throws CommandException {
		List<String> _clients = _receiver.getAllClientsWithoutDebt();

		for(String s : _clients)
			_display.addLine(s);
			
		_display.display();
	}
}

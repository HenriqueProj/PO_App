package prr.app.clients;

import prr.Network;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

import java.util.Map;
import java.util.TreeMap;
import prr.clients.Client;

//FIXME add more imports if needed

/**
 * Show all clients.
 */
class DoShowAllClients extends Command<Network> {

	DoShowAllClients(Network receiver) {
		super(Label.SHOW_ALL_CLIENTS, receiver);
	}

	@Override
	protected final void execute() throws CommandException {
                //FIXME implement command
		Map<String, Client> new_receiver = _receiver.getAllClients();
		for (Client j : new_receiver.values()) {
			_display.addLine(j);
		}
		_display.display();
	}
}

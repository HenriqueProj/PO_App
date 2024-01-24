package prr.app.lookups;

import java.util.List;
import java.util.ArrayList;

import prr.Network;
import prr.comms.*;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import pt.tecnico.uilib.Display;

/**
 * Command for showing all communications.
 */
class DoShowAllCommunications extends Command<Network> {

	private Network _net;
	private Display _display = new Display();

	DoShowAllCommunications(Network receiver) {
		super(Label.SHOW_ALL_COMMUNICATIONS, receiver);
		_net = receiver;
	}

	@Override
	protected final void execute() throws CommandException {
        List<Comm> _comms = _net.getAllComms();

		for(Comm c: _comms)
			_display.addLine(c);

		_display.display();
	}
}

package prr.app.clients;

import prr.Network;
import prr.clients.*;

import prr.app.exceptions.UnknownClientKeyException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import pt.tecnico.uilib.Display;
import pt.tecnico.uilib.forms.Form;

/**
 * Show the payments and debts of a client.
 */
class DoShowClientPaymentsAndDebts extends Command<Network> {

	private Network _net;
	private Display _display = new Display();

	DoShowClientPaymentsAndDebts(Network receiver) {
		super(Label.SHOW_CLIENT_BALANCE, receiver);
		_net = receiver;
	}

	@Override
	protected final void execute() throws CommandException {
        String key = Form.requestString(Prompt.key());

		Client client = _net.showClient(key);

		if(client == null)
			throw new UnknownClientKeyException(key);	
		else{
        	_display.popup(Message.clientPaymentsAndDebts(key, client.getPayments(), client.getDebts()) );
		}
	}
}

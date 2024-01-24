package prr.app.clients;

import prr.Network;
import prr.clients.*;

import prr.app.exceptions.UnknownClientKeyException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import pt.tecnico.uilib.Display;
import pt.tecnico.uilib.forms.Form;

/**
 * Disable client notifications.
 */
class DoDisableClientNotifications extends Command<Network> {

	private Network _net;
	private Client client;
	private Display display = new Display();
	private Form form = new Form();
	private String _key = "";

	DoDisableClientNotifications(Network receiver) {
		super(Label.DISABLE_CLIENT_NOTIFICATIONS, receiver);
		_net = receiver;
	}

	@Override
	protected final void execute() throws CommandException {
        _key = form.requestString(Prompt.key());

		if(_net.showClient(_key) == null){
			throw new UnknownClientKeyException(_key);
		}
		else{
			client = _net.showClient(_key);

			if(!client.checkNotifications()){
				display.popup(Message.clientNotificationsAlreadyDisabled());
			}
			else{
				client.disableNotifications();
			}
		}
	}
}

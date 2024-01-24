package prr.app.clients;

import javax.naming.CommunicationException;

import prr.Network;
import prr.clients.*;

import prr.app.exceptions.UnknownClientKeyException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import pt.tecnico.uilib.Display;
import pt.tecnico.uilib.forms.Form;
/**
 * Enable client notifications.
 */
class DoEnableClientNotifications extends Command<Network> {

	private Network _net;
	private Client client;
	private Display display = new Display();
	private Form form = new Form();
	private String _key = "";

	DoEnableClientNotifications(Network receiver) {
		super(Label.ENABLE_CLIENT_NOTIFICATIONS, receiver);
		_net = receiver;
	}

	@Override
	protected final void execute() throws CommandException {

		_key = form.requestString(Prompt.key());
		client = _net.showClient(_key);

		if(client == null){
			throw new UnknownClientKeyException(_key);
		}
		else{
			if(client.checkNotifications()){
				display.popup(Message.clientNotificationsAlreadyEnabled());
			}
			else{
				client.enableNotifications();
			}
		}
			/**
			 * try{
			_key = form.requestString(Prompt.key());
			client = _net.showClient(_key);

			if(client.checkNotifications())
				display.popup(Message.clientNotificationsAlreadyEnabled());			
			else
				client.enableNotifications();
		}
		catch (CommandException e){
			throw new UnknownClientKeyException(_key);
		}
		finally{
			if(client.checkNotifications())
				display.popup(Message.clientNotificationsAlreadyEnabled());
			
			else
				client.enableNotifications();
		}
			 */
	}
}

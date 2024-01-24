package prr.app.clients;

import prr.Network;
import prr.app.exceptions.UnknownClientKeyException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//FIXME add more imports if needed
import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.Display;
import prr.clients.*;
/**
 * Show specific client: also show previous notifications.
 */
class DoShowClient extends Command<Network> {

	private Network _network = new Network();
	private String _key;
	private Form form = new Form();
	private Display display = new Display();
	private Client client;

	DoShowClient(Network receiver) {
		super(Label.SHOW_CLIENT, receiver);
		//FIXME add command fields
		_network = receiver;
	}

	@Override
	protected final void execute() throws CommandException {
        _key = form.requestString(Prompt.key());

		if(_network.showClient(_key) == null){
			throw new UnknownClientKeyException(_key);
		}
		else{
			client = _network.showClient(_key);

			display.addLine(client);
			if(!client.getLastNotification().equals("") )
				display.addLine(client.getLastNotification() );
			
			display.display();
		}
	}
}

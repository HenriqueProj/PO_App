package prr.app.clients;

import prr.Network;
import prr.app.exceptions.DuplicateClientKeyException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//FIXME add more imports if needed
import prr.app.clients.*;
import prr.*;
import prr.app.exceptions.*;
import pt.tecnico.uilib.forms.Form;

import java.util.List;
import java.util.ArrayList;

/**
 * Register new client.
 */
class DoRegisterClient extends Command<Network> {

	private String[] fields = new String[4];
	private Network _network;
	private Form form = new Form();

	DoRegisterClient(Network receiver) {
		super(Label.REGISTER_CLIENT, receiver);
		
		_network = receiver;
	}

	@Override
	protected final void execute() throws CommandException{
		fields[0] = "CLIENT";

		fields[1] = form.requestString(Prompt.key());
/*
		try {
			_network.getAllClients().containsKey(fields[1]);
		}catch (DuplicateClientKeyException e) {
			throw new DuplicateClientKeyException(fields[1]);
		}finally {
			fields[2] = form.requestString(Prompt.name());
			fields[3] = String.valueOf(form.requestInteger(Prompt.taxId()) );

			_network.registerClient(fields);
		}
*/

		//_network.getAllClients().containsKey(fields[1]);

		fields[2] = form.requestString(Prompt.name());
		fields[3] = String.valueOf(form.requestInteger(Prompt.taxId()) );


		if(_network.getAllClients().containsKey(fields[1])){
			throw new DuplicateClientKeyException(fields[1]);
		}
		else{
			_network.registerClient(fields);
		}
	}

}

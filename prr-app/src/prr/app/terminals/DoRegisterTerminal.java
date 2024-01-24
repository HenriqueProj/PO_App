package prr.app.terminals;

import prr.Network;
import prr.clients.Client;

import prr.terminals.Terminal;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//FIXME add more imports if needed
import prr.app.terminals.*;
import prr.app.exceptions.*;
import pt.tecnico.uilib.forms.Form;

/**
 * Register terminal.
 */
class DoRegisterTerminal extends Command<Network> {

	private String[] fields = new String[4];
	private Network _network;
	private Form form = new Form();

	DoRegisterTerminal(Network receiver) {
		super(Label.REGISTER_TERMINAL, receiver);

		_network = receiver;
	}

	@Override
	protected final void execute() throws CommandException,UnknownClientKeyException,DuplicateTerminalKeyException, InvalidTerminalKeyException{
		String key = form.requestString(Prompt.terminalKey());
		
		fields[0] = form.requestOption(Prompt.terminalType(), "BASIC", "FANCY");

		fields[2] = form.requestString(Prompt.clientKey());

		fields[3] = "IDLE";

		try{
			Integer.parseInt(key);

			fields[1] = key;

			// Inicialização do estado
			fields[3] = "IDLE";
			//limpar isto como no outro DoOpenMenuTerminalConsole e fazer o catch
			if(key.length() != 6){
				throw new InvalidTerminalKeyException(key);
			}
			else if (_network.showClient(fields[2]) == null) {
				throw new UnknownClientKeyException(fields[2]);
			} else if (_network.showTerminal(fields[1]) != null) {
				throw new DuplicateTerminalKeyException(fields[1]);
			} else {
				_network.registerTerminal(fields);
			}
		}
		catch(NumberFormatException e){
			throw new InvalidTerminalKeyException(key);
		}
		
	}
}

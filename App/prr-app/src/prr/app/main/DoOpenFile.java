package prr.app.main;

import prr.NetworkManager;
import prr.app.exceptions.FileOpenFailedException;
import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

//Add more imports if needed
import prr.exceptions.UnavailableFileException;
import prr.app.exceptions.FileOpenFailedException;
import java.lang.ClassNotFoundException;
import java.io.IOException;
/**
 * Command to open a file.
 */
class DoOpenFile extends Command<NetworkManager> {

	DoOpenFile(NetworkManager receiver) {
		super(Label.OPEN_FILE, receiver);
                //FIXME add command fields
		addStringField("Filename", Prompt.openFile());
	}

	@Override
	protected final void execute() throws CommandException {
		try {
			_receiver.load(stringField("Filename"));
                	//FIXME implement command
		} catch (UnavailableFileException e) {
			throw new FileOpenFailedException(new UnavailableFileException(stringField("Filename")));
		}
		catch (ClassNotFoundException e) {
			throw new FileOpenFailedException(new UnavailableFileException(stringField("Filename")));
		}
		catch(IOException e) {
			throw new FileOpenFailedException(new UnavailableFileException(stringField("Filename")));
		}
	}
}

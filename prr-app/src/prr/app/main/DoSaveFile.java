package prr.app.main;

import prr.NetworkManager;

import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

import java.io.FileNotFoundException;
import prr.app.exceptions.FileOpenFailedException;
import prr.exceptions.MissingFileAssociationException;
import java.lang.ClassNotFoundException;
import pt.tecnico.uilib.Display;
import java.io.IOException;

//FIXME add more imports if needed

/**
 * Command to save a file.
 */
class DoSaveFile extends Command<NetworkManager> {

	private Display display = new Display();
	private NetworkManager _receiver; 

	DoSaveFile(NetworkManager receiver) {
		super(Label.SAVE_FILE, receiver);

		_receiver = receiver;
	}

	@Override
	protected final void execute() throws CommandException{
                //FIXME implement command and create a local Form
		if(!_receiver.get_filename().equals("")){
			try {
				_receiver.save();
			} catch (MissingFileAssociationException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			try {
				Form f = new Form();
				f.addStringField("Filename", Prompt.newSaveAs());
				f.parse();
				_receiver.saveAs(f.stringField("Filename"));
			}
			catch (FileNotFoundException e) {
				e.printStackTrace();
			}catch (MissingFileAssociationException e) {
				e.printStackTrace();
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}

package prr.comms;

import prr.clients.*;
import prr.Network;
import prr.comm_state.*;
import prr.terminals.Terminal;

public class TextComm extends Comm{
    private String _message;

    public TextComm(Network net, Terminal sender, Terminal receiver, Client client, String message){
        super(net, sender, receiver, client);

        _message = message;

        StateManager manager = super.getManager();
        manager.setState(new Off(manager));
    }

    public String toString(){
        return super.toString("TEXT");
    }

    public int get_len_message(){
        return _message.length();
    }
    @Override
    public String type(){
        return "TEXT";
    }
}

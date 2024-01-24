package prr.comms;

import prr.clients.*;
import prr.Network;
import prr.terminals.Terminal;

public class VoiceComm extends Comm{
    private long _duration;

    public VoiceComm(Network net, Terminal sender, Terminal receiver, Client client){
        super(net, sender, receiver, client);
    }

    public void endComm(long duration){
        _duration = duration;
    }
    
    public String toString(){
        return super.toString("VOICE");
    }
    @Override
    public String type(){
        return "VOICE";
    }
}
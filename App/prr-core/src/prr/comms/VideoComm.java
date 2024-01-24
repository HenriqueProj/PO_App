package prr.comms;

import prr.clients.*;
import prr.Network;
import prr.terminals.Terminal;

public class VideoComm extends VoiceComm{

    public VideoComm(Network net, Terminal sender, Terminal receiver, Client client){
        super(net, sender, receiver, client);
    }

    @Override
    public void endComm(long duration){
        super.endComm(duration);
    }
    
    public String toString(){
        return super.toString("VIDEO");
    }
    @Override
    public String type(){
        return "VIDEO";
    }
}
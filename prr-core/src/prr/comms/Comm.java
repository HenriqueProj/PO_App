package prr.comms;

import java.io.Serializable;

import prr.clients.*;
import prr.Network;
import prr.terminals.Terminal;
import prr.comm_state.*;

public class Comm implements Serializable{
    private int _id;

    private StateManager _manager = new StateManager();

    private Terminal _sender;
    private Terminal _receiver;
    private Client _client;

    private long _price;

    private long _units;

    private boolean _paid = false;

    public Comm(Network net, Terminal sender, Terminal receiver, Client client){
        _id = net.getNumComms() + 1;
        net.addComm(this);
        sender.addComm(this);
        receiver.addComm(this);

        _sender = sender;
        _receiver = receiver;
        _client = client;
        
        _manager.setComm(this);
        _manager.setState(new Ongoing(_manager));
    }

    public String toString(String type){
        if(_manager.getState().equals("ONGOING"))
            return type + "|" + _id + "|" + _sender.getKey() + "|" + _receiver.getKey() + "|0|0|" + _manager.getState();
    
        else{
            return type + "|" + _id + "|" + _sender.getKey() + "|" + _receiver.getKey() + "|" + String.valueOf(_units) + "|" + String.valueOf(_price) + "|" + _manager.getState();
        }
    }

    public void setPrice(long price){
        _price = price;
    }

    public void setUnits(long units){
        _units = units;
    }

    public void turnOff(){
        _manager.setState(new Off(_manager));
    }

    public String getState(){
        return _manager.getState();
    }
    public StateManager getManager(){
        return _manager;
    }
    public void addPrice(long p){
        _price = p;
    }
    public String getSenderKey(){
        return _sender.getKey();
    }
    public void pay(){
        _paid = true;        
    }
    public boolean isPaid(){
        return _paid;
    }
    public long getPrice(){
        return _price;
    }
    public String type(){
        return "";
    }
    public Terminal receiver(){
        return _receiver;
    }
    
}

package prr.payplans;

import java.io.Serializable;

import prr.clients.*;

public class Payplan implements Serializable{
    private Client _client;

    private State _mode = new Normal(this);
    
    public void upgrade(){ _mode.upgrade(); };

    public void downgrade(){ _mode.downgrade(); };

    public void doubleDowngrade(){ _mode.doubleDowngrade(); };

    public long calculatePrice(long chars){return _mode.calculatePrice(chars); };

    public long calculatePrice(long time, String state){return _mode.calculatePrice(time, state); };

    public String getState(){
        return _mode.getState();
    }

    public void setState(State mode) {
        _mode = mode;
    }
  
    public void setClient(Client client) {
        _client = client;
    }

    public Client getClient(){
        return _client;
    }

    public void checkStateChange(){
        _mode.checkState();
    }
    
}
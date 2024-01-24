package prr.comm_state;

import java.io.Serializable;

import prr.comms.*;

public class StateManager implements Serializable{
    private Comm _comm;

    private State _mode = new Ongoing(this);

    public void toOff(){_mode.toOff();}

    public String getState(){
        return _mode.getState();
    }
    public void setState(State mode) {
        _mode = mode;
    }

    public void setComm(Comm comm){
        _comm = comm;
    }
}

package prr.comm_state;

import java.io.Serializable;

import prr.comms.*;

public abstract class State implements Serializable{
    private Comm _comm;

    private Comm getComm(){
        return _comm;
    }

    public abstract void toOff();

    public abstract String getState();
}

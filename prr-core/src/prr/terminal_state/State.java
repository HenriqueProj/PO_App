package prr.terminal_state;

import java.io.Serializable;

import prr.terminals.*;
public abstract class State implements Serializable{

    private Terminal _terminal;

    public Terminal getTerminal(){
        return _terminal;
    }

    public abstract void toIdle();

    public abstract void toSilent();

    public abstract void toOff();

    public abstract void startedComm();

    public abstract String getState();


}
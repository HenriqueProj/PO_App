package prr.terminal_state;

import java.io.Serializable;

import prr.terminals.*;

public class StateManager implements Serializable{
    private Terminal _terminal;

    private State _mode = new Idle(this);
    
    public void toIdle(){_mode.toIdle();}

    public void toSilent(){_mode.toSilent();}

    public void toOff(){_mode.toOff();}

    public void startedComm(){_mode.startedComm();}

    public String getState(){
        return _mode.getState();
    }

    public void setState(State mode) {
        _mode = mode;
    }
    
    public void setTerminal(Terminal terminal) {
        _terminal = terminal;
    }

    public Terminal getTerminal(){
        return _terminal;
    }
}
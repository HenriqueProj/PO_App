package prr.terminal_state;

import prr.clients.*;
import prr.terminals.*;

public class Idle extends State{

    private StateManager _manager;

    public Idle(StateManager manager){
        super();
        _manager = manager;
    }

    @Override
    public void toIdle(){
    }

    @Override
    public void toSilent(){
        _manager.setState(new Silent(_manager));
    }

    @Override
    public void toOff(){
        _manager.setState(new Off(_manager));
    }

    @Override
    public void startedComm(){
        _manager.setState(new Busy(_manager));
    }

    @Override
    public String getState(){
        return "IDLE";
    }
}
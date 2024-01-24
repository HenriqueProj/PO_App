package prr.terminal_state;

import prr.clients.*;
import prr.terminals.*;

public class Off extends State{

    private StateManager _manager;

    public Off(StateManager manager){
        super();
        _manager = manager;
    }

    @Override
    public void toOff(){
    }

    @Override
    public void startedComm(){
    }

    @Override
    public void toSilent(){
        _manager.setState(new Silent(_manager));
    }

    @Override
    public void toIdle(){
        _manager.setState(new Idle(_manager));
    }

    @Override
    public String getState(){
        return "OFF";
    }
}
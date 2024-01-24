package prr.terminal_state;

import prr.clients.*;
import prr.terminals.*;

public class Silent extends State{

    private StateManager _manager;

    public Silent(StateManager manager){
        super();
        _manager = manager;
    }

    @Override
    public void toSilent(){
    }

    @Override
    public void toIdle(){
        _manager.setState(new Idle(_manager));
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
        return "SILENCE";
    }

}
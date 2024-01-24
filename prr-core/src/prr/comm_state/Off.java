package prr.comm_state;

public class Off extends State{
    private StateManager _manager;

    public Off(StateManager manager){
        _manager = manager;
    }   

    @Override
    public void toOff(){
    }

    @Override
    public String getState(){
        return "FINISHED";
    }
}
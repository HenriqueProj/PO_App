package prr.comm_state;

public class Ongoing extends State{
    private StateManager _manager;

    public Ongoing(StateManager manager){
        _manager = manager;
    }   

    @Override
    public void toOff(){
        _manager.setState(new Off(_manager));
    }

    @Override
    public String getState(){
        return "ONGOING";
    }
}

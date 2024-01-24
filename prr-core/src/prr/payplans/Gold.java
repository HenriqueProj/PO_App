package prr.payplans;

import prr.clients.*;

public class Gold extends State{

    private Payplan _payplan;
    private Client _client;

    public Gold(Payplan payplan){
        super();
        _payplan = payplan;
        _client = _payplan.getClient();
    }

    @Override
    public void doubleDowngrade(){
    }
    
    @Override
    public void upgrade(){
        _payplan.setState(new Platinum(_payplan));
    }

    @Override
    public void downgrade(){
        _payplan.setState(new Normal(_payplan));
    }

    @Override
    public void checkState(){
        if(_client.getBalance() < 0){
            downgrade();
        }
        else if(_client.getTypeComms() == "VIDEO" && _client.getNumComms() >= 5){
            upgrade();
        }
    }

    @Override
    public String getState(){
        return "GOLD";
    }

    @Override
    public long calculatePrice(long chars){
        if(chars < 100)
            return 10;
        return 2 * chars;
    }

    @Override
    public long calculatePrice(long time, String state){
        if(state.equals("VOICE"))
            return 10 * time;
        return 20 * time;
    }
}
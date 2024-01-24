package prr.payplans;

import prr.clients.*;

public class Platinum extends State{

    private Payplan _payplan;
    private Client _client;

    public Platinum(Payplan payplan){
        super();
        _payplan = payplan;
        _client = _payplan.getClient();
    }

    @Override
    public void upgrade(){
    }

    @Override
    public void downgrade(){
        _payplan.setState(new Gold(_payplan));
    }

    @Override
    public void doubleDowngrade(){
        _payplan.setState(new Normal(_payplan));
    }

    @Override
    public void checkState(){
        if(_client.getBalance() < 0){
            doubleDowngrade(); 
        }
        else if(_client.getTypeComms() == "TEXT" && _client.getNumComms() >= 2){
            downgrade();
        }
    }

    @Override
    public String getState(){
        return "PLATINUM";
    }

    @Override
    public long calculatePrice(long chars){
        if(chars < 50)
            return 0;
        return 4;
    }

    @Override
    public long calculatePrice(long time, String state){
        return time * 10;
    }
}
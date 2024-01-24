package prr.payplans;

import prr.clients.*;

public class Normal extends State{

    private Payplan _payplan;
    private Client _client;

    public Normal(Payplan payplan){
        super();
        _payplan = payplan;
        _client = _payplan.getClient();
    }

    @Override
    public void downgrade(){
    }

    @Override
    public void doubleDowngrade(){
    }

    @Override
    public void upgrade(){
        _payplan.setState(new Gold(_payplan));
    }

    @Override
    public void checkState(){
        if(_client.getBalance() > 500){
            upgrade();
        }
    }

    @Override
    public String getState(){
        return "NORMAL";
    }

    @Override
    public long calculatePrice(long chars){
        if(chars < 50)
            return 10;
        if(chars >= 100)
            return 2 * chars;
        return 16;
    }

    @Override
    public long calculatePrice(long time, String state){

        if(state.equals("VOICE"))
            return 20 * time;
        return 30*time;
    }
}
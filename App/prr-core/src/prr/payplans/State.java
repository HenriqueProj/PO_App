package prr.payplans;

import java.io.Serializable;

public abstract class State implements Serializable{

    private Payplan _payplan;

    public Payplan getPayplan(){
        return _payplan;
    }

    public abstract void upgrade();

    public abstract void downgrade();

    public abstract void doubleDowngrade();

    public abstract void checkState();

    public abstract String getState();

    public abstract long calculatePrice(long chars);

    public abstract long calculatePrice(long time, String state);
}
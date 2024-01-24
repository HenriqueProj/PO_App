package prr.clients;

// Imports
import java.util.List;
import java.io.Serializable;
import java.util.ArrayList;

import prr.payplans.*;
import prr.terminals.*;
import prr.notifications.*;

import java.util.Iterator;

public class Client implements Serializable{
    
    private List<Terminal> _terminals;

    private int _num_comms = 0;
    private int num_terminals = 0;

    private long _payments = 0;
    private long _debts = 0;

    private Payplan _payplan;

    private String _id = "";
    private String _name = "";
    private String _nif = "";
    private String typeComms = "";

    private String _notifications = "YES";
    
    private Notification notification = new Notification();

    private String last_notif = "";
    public Client(String id, String name, String nif){
        _terminals = new ArrayList<Terminal>();
        
        _payplan = new Payplan();
        _payplan.setClient(this);
        
        _id = id;
        _name = name;
        _nif = nif;
    }


    public void endedComm(long price){
        // Função chamada pelo terminal que realizou a comunicação, no fim da mesma

        // Dívida - "price" é negativo e posto na "_debts", caso contrário mete o valor no "_payments"
        if(price < 0)
            _debts += - price;
        else
            _payments += price;

        // Verifica se há mudança de estado
        _payplan.checkStateChange();
    }
    public long getPayments(){
        return _payments;
    }
    public long getDebts(){
        return _debts;
    }
    public long getBalance(){
        return _payments - _debts;
    }

    public String getTypeComms(){
        return typeComms;
    }

    public int getNumComms(){
        return _num_comms;
    }
    
    public String toString(){
        return "CLIENT|" + _id + "|" + _name + "|" + String.valueOf(_nif) + "|" + _payplan.getState() + "|" + _notifications + "|" +  String.valueOf(num_terminals) + "|" + String.valueOf(_payments) + "|" + String.valueOf(_debts);
    }

    public void addTerminal(Terminal term){
        _terminals.add(term);
        num_terminals++;
    }   

    public boolean checkNotifications(){
        if(_notifications.equals("YES"))
            return true;

        return false;
    }
    public void enableNotifications(){
        _notifications = "YES";
    }
    public void disableNotifications(){
        _notifications = "NO";
    }

    public long calculatePrice(long chars){
        long price =  _payplan.calculatePrice(chars);
        return price;
    }

    public long calculatePrice(long time, String state){
        return  _payplan.calculatePrice(time, state);
    }

    public void addDebt(long price){
        _debts += price;
    }
    public void pay(long price){
        _debts -= price;
        _payments += price;
    }
    public void newComm(){
        _num_comms++;
    }
    public void lastCommType(String type){
        typeComms = type;
        _num_comms = 1;
    }
    public String lastComm(){
        return typeComms;
    }
    public void straightComm(){
        _num_comms++;
    }
    public void createNotific(String mode, String new_mode, String key){
        if(new_mode.equals("IDLE")){
            if(mode.equals("SILENCE"))
                last_notif = notification.silent_to_idle(key);
            else if(mode.equals("BUSY") )
                last_notif = notification.busy_to_idle(key);
            else    
                last_notif = notification.off_To_Idle(key);
        }
    }
    public String getLastNotification(){
        return last_notif;
    }
}
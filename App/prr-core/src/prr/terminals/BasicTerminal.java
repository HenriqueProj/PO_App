package prr.terminals;

public class BasicTerminal extends Terminal{
    // Não faz VideoComms
    public BasicTerminal(String type, String id, String clientId, String state){
        super(type, id, clientId, state);
    }

    public String toString(){
        return super.toString("BASIC");
    } 
}

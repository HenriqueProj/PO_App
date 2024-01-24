package prr.terminals;

public class FancyTerminal extends BasicTerminal{
    // Basic com VideoComms
    public FancyTerminal(String type, String id, String clientId, String state){
        super(type, id, clientId, state);
    }

    public String toString(){
        return super.toString("FANCY");
    } 
}

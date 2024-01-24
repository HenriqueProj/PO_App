package prr.notifications;

import prr.terminal_state.StateManager;
import prr.terminals.Terminal;

import java.io.Serializable;

public class Notification implements Serializable { 

    // FIXME: Exçeções

    public String off_To_Silent(Terminal terminal) {
        terminal.getStateManager().toSilent();
        return "Terminal " + terminal.getKey() + " is now in SILENCE mode";
    }
    public String off_To_Idle(Terminal terminal) {
        terminal.getStateManager().toIdle();
        return "O2I|" + terminal.getKey();
    }
    public String busy_to_idle(Terminal terminal) {
        terminal.getStateManager().toIdle();
        return "Terminal " + terminal.getKey() + " is now in IDLE mode";
    }
    public String silent_to_idle(Terminal terminal) {
        terminal.getStateManager().toIdle();
        return "S2I|" + terminal.getKey();

    }
    public String off_To_Silent(String s) {
        return "Terminal " + s + " is now in SILENCE mode";
    }
    public String off_To_Idle(String s) {
        return "O2I|" + s;
    }
    public String busy_to_idle(String s) {
        return "Terminal " + s + " is now in IDLE mode";
    }
    public String silent_to_idle(String s) {
        return "S2I|" + s;
    }
}
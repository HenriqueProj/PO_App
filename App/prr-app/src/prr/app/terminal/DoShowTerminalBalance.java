package prr.app.terminal;

import prr.Network;
import prr.terminals.Terminal;
import pt.tecnico.uilib.menus.CommandException;

import pt.tecnico.uilib.Display;
/**
 * Show balance.
 */
class DoShowTerminalBalance extends TerminalCommand {

	private Display display = new Display();
	private Terminal _terminal;

	DoShowTerminalBalance(Network context, Terminal terminal) {
		super(Label.SHOW_BALANCE, context, terminal);
		_terminal = terminal;
	}

	@Override
	protected final void execute() throws CommandException {
	
        display.popup(Message.terminalPaymentsAndDebts(_terminal.getKey(), Math.round(_terminal.getPayments()), Math.round(_terminal.getDebts())));
	}
}
package prr.app.main;

import prr.Network;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//FIXME add more imports if needed

/**
 * Show global balance.
 */
class DoShowGlobalBalance extends Command<Network> {

	private Network _net;

	DoShowGlobalBalance(Network receiver) {
		super(Label.SHOW_GLOBAL_BALANCE, receiver);
		_net = receiver;
	}

	@Override
	protected final void execute() throws CommandException {
		long payments = _net.getPayments();
		long debts = _net.getDebts();

		_display.popup(Message.globalPaymentsAndDebts(payments, debts) );
	}
}
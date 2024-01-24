package prr.app.terminal;

import prr.clients.Client;
import prr.Network;
import prr.terminals.Terminal;
import prr.comms.*;
import prr.comm_state.*;

import prr.app.exceptions.UnknownTerminalKeyException;

import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.CommandException;
import pt.tecnico.uilib.Display;

/**
 * Command for sending a text communication.
 */
class DoSendTextCommunication extends TerminalCommand {

        private Network _net;
        private Terminal _sender;
        private Client client;
        private Display display = new Display();
        private Form form = new Form(); 

        DoSendTextCommunication(Network context, Terminal terminal) {
                super(Label.SEND_TEXT_COMMUNICATION, context, terminal, receiver -> receiver.canStartCommunication());
                _net = context;
                _sender = terminal;
        }

        @Override
        protected final void execute() throws CommandException {
                String receiverKey = form.requestString(Prompt.terminalKey());

                Terminal receiver = _net.showTerminal(receiverKey);
                String message = form.requestString(Prompt.textMessage());
                client = _net.showClient( _sender.getClientID() );
                if(receiver == null){
                        throw new UnknownTerminalKeyException(receiverKey);
                }
                else{
                        if(receiver.getStateManager().getState().equals("OFF")){
                                display.popup(Message.destinationIsOff(receiverKey));
				_net.failedComm(_sender, receiver);

                        }
                        else{
                                if(!client.lastComm().equals("TEXT"))
                                        client.lastCommType("TEXT");	
                                else
                                        client.straightComm();
                                _net.createTextComm(_sender, receiver, message);                              
                        }
                }
        }
} 

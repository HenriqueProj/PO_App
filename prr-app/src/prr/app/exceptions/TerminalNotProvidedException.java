package prr.app.exceptions;

import pt.tecnico.uilib.menus.CommandException;

    /** Exception for null terminals. */
    public class TerminalNotProvidedException extends CommandException {

        /** Serial number for serialization. */
        private static final long serialVersionUID = 202208091753L;

        /** @param key Null terminal to report. */
        public TerminalNotProvidedException(String key) {
            super(Message.unknownTerminalKey(key));
        }

    }
package model.excpetion;

import application.UI;

public class InvalidPhoneNumberException extends InvalidDataException{
	private static final long serialVersionUID = 1L;
	public InvalidPhoneNumberException(String message) {
		super(UI.ANSI_YELLOW + message + UI.ANSI_RESET);
	}
}
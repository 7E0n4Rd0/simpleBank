package model.excpetion;

import application.UI;

public class InvalidNameException extends InvalidDataException{
	private static final long serialVersionUID = 1L;
	public InvalidNameException(String message) {
		super(UI.ANSI_YELLOW + message + UI.ANSI_RESET);
	}
}
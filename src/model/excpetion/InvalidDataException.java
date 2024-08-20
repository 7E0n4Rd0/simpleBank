package model.excpetion;

import application.UI;

public class InvalidDataException extends Exception{
	private static final long serialVersionUID = 1L;
	public InvalidDataException(String message) {
		super("\t\t\tError: " + UI.ANSI_RED + message + UI.ANSI_RESET);
	}
}
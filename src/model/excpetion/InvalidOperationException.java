package model.excpetion;

import application.UI;

public class InvalidOperationException extends Exception{
	private static final long serialVersionUID = 1L;
	public InvalidOperationException(String message) {
		super(UI.ANSI_RED + message + UI.ANSI_RESET);
	}
}
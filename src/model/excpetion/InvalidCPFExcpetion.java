package model.excpetion;

import application.UI;

public class InvalidCPFExcpetion extends InvalidDataException{
	private static final long serialVersionUID = 1L;
	public InvalidCPFExcpetion(String message) {
		super(UI.ANSI_YELLOW + message + UI.ANSI_RESET);
	}
}
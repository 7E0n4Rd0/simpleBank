package model.excpetion;

public class InvalidPhoneNumberException extends InvalidDataException{
	private static final long serialVersionUID = 1L;
	public InvalidPhoneNumberException(String message) {
		super(message);
	}
}
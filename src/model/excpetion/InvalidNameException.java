package model.excpetion;

public class InvalidNameException extends InvalidDataException{
	private static final long serialVersionUID = 1L;
	public InvalidNameException(String message) {
		super(message);
	}
}
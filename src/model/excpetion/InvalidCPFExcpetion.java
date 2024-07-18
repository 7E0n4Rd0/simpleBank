package model.excpetion;

public class InvalidCPFExcpetion extends InvalidDataException{
	private static final long serialVersionUID = 1L;
	public InvalidCPFExcpetion(String message) {
		super(message);
	}
}
package db;

public class DBIntegrityException extends RuntimeException{
	/***
	 * It will throw this exception when the user try to delete any data from database that uses a Foreign Key
	 * Example: an Agency was 100 Accounts registered, if you try to delete from the database that agency
	 * will throw this exception because a hundred accounts has the agency code from that Agency.   
	 */
	private static final long serialVersionUID = 1L;
	
	public DBIntegrityException(String msg) {
		super(msg);
	}
}

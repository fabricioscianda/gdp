package gdp.exceptions;

public class DAOException extends Exception{

	private static final long serialVersionUID = 1L;

	public DAOException() {
		super();
	}
	
	public DAOException(Exception e) {
		super(e);
	}
	
	public DAOException(String message) {
		super(message);
	}
	
}

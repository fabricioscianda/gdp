package mseg.erp.exceptions;

public class UniqueConstraintException extends Exception {

	private static final long serialVersionUID = 3521104883550325409L;
	
	public UniqueConstraintException(){
		super();
	}
	public UniqueConstraintException(String message){
		super(message);
	}
	

}

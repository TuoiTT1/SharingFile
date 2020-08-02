package training.java.sharing_file.exception;

public class UsernameNotFoundException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public UsernameNotFoundException(String message, Throwable error) {
		super(message, error);
	}

}

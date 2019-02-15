package exception;

/**
 * A coordinate file type read error
 * 
 * @author aaudelin
 *
 */
public class FileReadException extends Exception {

	private static final long serialVersionUID = -4901828751119584073L;

	public FileReadException(String message) {
		super(message);
	}

}

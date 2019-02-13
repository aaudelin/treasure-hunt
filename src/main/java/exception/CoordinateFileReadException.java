package exception;

/**
 * A coordinate file type read error
 * 
 * @author aaudelin
 *
 */
public class CoordinateFileReadException extends Exception {

	private static final long serialVersionUID = -4901828751119584073L;

	public CoordinateFileReadException(String message) {
		super(message);
	}

}

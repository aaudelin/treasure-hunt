package exception;

/**
 * Error when and entity is created/updated
 * 
 * @author aaudelin
 *
 */
public class EntityException extends Exception {

	private static final long serialVersionUID = -3749007226571664192L;

	public EntityException(String message) {
		super(message);
	}
}

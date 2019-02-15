package exception;

/**
 * Invalid order
 * 
 * @author aaudelin
 *
 */
public class OrderException extends EntityException {

	private static final long serialVersionUID = -9156163724704174967L;

	public OrderException(String message) {
		super(message);
	}

}

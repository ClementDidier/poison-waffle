package exceptions;

public class OutOfWaffleException extends Exception {

	private static final long serialVersionUID = 1L;

	public OutOfWaffleException(String message) {
		super(message);
	}
}

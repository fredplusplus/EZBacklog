package webplus.ezbacklog.exceptions;

public class ValidationException extends EZBacklogException {

	private static final long serialVersionUID = 1L;

	public ValidationException(Exception e) {
		super(e);
	}

}

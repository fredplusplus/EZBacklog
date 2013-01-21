package webplus.ezbacklog.exceptions;

public class EZBacklogException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private Exception cause;

	public EZBacklogException(Exception e) {
		cause = e;
	}

	@Override
	public Exception getCause() {
		return cause;
	}
}

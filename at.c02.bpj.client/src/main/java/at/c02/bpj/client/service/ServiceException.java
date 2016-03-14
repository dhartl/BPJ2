package at.c02.bpj.client.service;

public class ServiceException extends Exception {
	private static final long serialVersionUID = -2349560478154156243L;

	public ServiceException(String message) {
		super(message);
	}

	public ServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	public ServiceException(Throwable cause) {
		super(cause);
	}
}

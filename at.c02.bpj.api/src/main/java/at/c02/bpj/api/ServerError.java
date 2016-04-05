package at.c02.bpj.api;

public class ServerError {
	private String message;

	public ServerError(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}

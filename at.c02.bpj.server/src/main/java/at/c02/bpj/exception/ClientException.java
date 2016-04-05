package at.c02.bpj.exception;

/**
 * Diese Exception verwenden, um Fehlermeldungen zum Client zu transportieren!
 * 
 */
public class ClientException extends RuntimeException {
	private static final long serialVersionUID = -7268327939207968390L;

	public ClientException(String message) {
		super(message);
	}
}

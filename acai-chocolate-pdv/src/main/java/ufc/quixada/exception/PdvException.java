package ufc.quixada.exception;

public class PdvException extends Exception{

	private static final long serialVersionUID = 1L;
	
	private String message;
	
	public PdvException(String message) {
		this.message = message;
	}

	@Override
	public String getMessage() {
		return this.message;
	}
}

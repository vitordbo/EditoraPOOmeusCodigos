package exceptions;

public class AutenticarExceptions extends Exception {
	private static final long serialVersionUID = 1L;
	
	public AutenticarExceptions() {
		super("login ou senha não encontrados");
	}

}

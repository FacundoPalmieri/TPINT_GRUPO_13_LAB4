package excepcion;

public class DniInvalido extends Exception {

	@Override
	public String getMessage() {
		return "El formato del dni es invalido";
	}
	
	
}

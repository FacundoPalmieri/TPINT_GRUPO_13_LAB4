package excepcion;

public class DniInexistente extends Exception {

	@Override
	public String getMessage() {
		return "El dni no existe";
	}
	
}

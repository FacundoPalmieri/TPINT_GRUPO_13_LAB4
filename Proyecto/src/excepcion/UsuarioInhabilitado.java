package excepcion;

public class UsuarioInhabilitado extends Exception {

	@Override
	public String getMessage() {
		return "El usuario se encuentra inhabilitado";
	}
	
}

package excepcion;

public class fechaInvalida extends Exception {

	
	public fechaInvalida() {
		
	}
	
	
	@Override
	public String getMessage() {
		return "La fechas ingresadas no son válidas.";
	}
	
}

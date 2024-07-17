package excepcion;

public class FechaInvalida extends Exception {

	
	public FechaInvalida() {
		
	}
	
	
	@Override
	public String getMessage() {
		return "La fecha no puede ser anterior a hoy y/o Desde no puede ser mayor a Hasta o viceversa";
	}
	
}

package excepcion;

import java.time.LocalDate;

public class Validacion {
	
	public static void validarFormatoDNI(String dni) throws DniInvalido {
		try {
			Integer.parseInt(dni);
		}
		catch(Exception e) {
			throw new DniInvalido();
		}
		
		if(dni.length()>20) {
			throw new DniInvalido();
		}
	}
	
	
	public static void verificarFechas(LocalDate fecha1, LocalDate fecha2) throws FechaInvalida {
		if(!fecha1.isBefore(fecha2)) {
			throw new FechaInvalida();
		}
		if(!fecha2.isBefore(LocalDate.now())) {
			throw new FechaInvalida();
		}
	}
}

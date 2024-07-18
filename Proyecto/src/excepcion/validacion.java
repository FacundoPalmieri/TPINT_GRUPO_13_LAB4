package excepcion;

import java.time.LocalDate;

public class validacion {
	
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
	
	
	public static void verificarFechas(LocalDate fecha1, LocalDate fecha2) throws fechaInvalida {
		 LocalDate hoy = LocalDate.now();
	        
	        if (fecha1.isAfter(hoy) || fecha2.isAfter(hoy)) {
	            throw new fechaInvalida();
	        }
	        if (fecha1.isAfter(fecha2)) {
	            throw new fechaInvalida();
	        }
	}
}

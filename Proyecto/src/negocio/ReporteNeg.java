package negocio;

import java.util.ArrayList;

import entidad.Movimientos;
import entidad.Prestamo;

public interface ReporteNeg {
	
	public boolean busquedaDNI(String dni);
	
	public boolean busquedaUsuario(String nombreUsuario);
	
	public ArrayList<Prestamo> prestamos(String dni, int estado);
	
	public ArrayList<Movimientos> movimientos();
}

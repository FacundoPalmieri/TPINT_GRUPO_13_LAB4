package datos;

import java.time.LocalDate;
import java.util.ArrayList;

import entidad.Movimientos;
import entidad.PagosPrestamos;
import entidad.Prestamo;

public interface ReporteDao {
	
	public boolean busquedaDNI(String dni);
	
	public boolean busquedaUsuario(String nombreUsuario);
	
	public ArrayList<Prestamo> prestamos(String dni, ArrayList<Integer> estado,LocalDate fecha1, LocalDate fecha2);
	
	public ArrayList <Movimientos> PromedioIngresosMensuales(String fechaInicio, String fechaFin);
	
	public ArrayList<PagosPrestamos> pagosPrestamos(int idPrestamo);
}

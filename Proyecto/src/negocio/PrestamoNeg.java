package negocio;

import java.util.ArrayList;

import entidad.EstadoPrestamo;
import entidad.PagosPrestamos;
import entidad.Prestamo;

public interface PrestamoNeg {
	
	public boolean guardarPrestamo(Prestamo prestamo, String ClienteDni, int estadoPrestamo, int nCuenta);
	
	public ArrayList<Prestamo> obtenerPrestamos();
	public ArrayList<Prestamo> obtenerPrestamosPorCliente(String DNI);
    public int actualizarEstadoPrestamo(int idPrestamo, int estadoPrestamo);
    public ArrayList<EstadoPrestamo> obtenerListadeEstado();
    public int registrarCuotas(PagosPrestamos pagosPrestamo);
    public int actualizarCuota(int idPrestamo, int cuota, int estado);
    public int actualizarCuotaPrestamo(int id, int cuota);    

 
}
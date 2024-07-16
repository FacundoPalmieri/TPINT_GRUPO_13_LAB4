package datos;

import java.util.ArrayList;

import entidad.EstadoPrestamo;
import entidad.PagosPrestamos;
import entidad.Prestamo;

public interface PrestamoDao {
	public boolean guardarPrestamo(Prestamo prestamo, String clienteDni, int estadoPrestamo, int nCuenta); // OK	
	public ArrayList<Prestamo> obtenerPrestamos(); // OK
    public ArrayList<Prestamo> obtenerPrestamosPorCliente(String DNI);
    public int actualizarEstadoPrestamo(int idPrestamo, int estadoPrestamo);
    public ArrayList<EstadoPrestamo> obtenerListadeEstado();
    public int registrarCuotas(PagosPrestamos pagosPrestamo);
    public int actualizarCuota(int idPrestamo, int cuota, int estado);
    public int actualizarCuotaPrestamo(int id, int cuota);    

}
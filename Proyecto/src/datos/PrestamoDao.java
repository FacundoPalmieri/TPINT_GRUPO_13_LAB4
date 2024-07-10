package datos;

import java.util.ArrayList;
import entidad.Prestamo;

public interface PrestamoDao {
	public boolean guardarPrestamo(Prestamo prestamo, String clienteDni, int estadoPrestamo); // OK
	
	public ArrayList<Prestamo> obtenerPrestamos(); // OK

	/*
    List<Prestamo> obtenerPrestamosPorCliente(int clienteId);

    Prestamo obtenerPrestamoPorId(int prestamoId);

    boolean actualizarPrestamo(Prestamo prestamo);

    boolean eliminarPrestamo(int prestamoId);
    */

}

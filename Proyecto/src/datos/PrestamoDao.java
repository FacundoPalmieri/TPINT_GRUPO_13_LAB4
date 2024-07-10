package datos;

import java.util.ArrayList;
import entidad.Prestamo;

public interface PrestamoDao {
	public boolean guardarPrestamo(Prestamo prestamo, String clienteDni, int estadoPrestamo); // OK
	
	public ArrayList<Prestamo> obtenerPrestamos(); // OK
    public ArrayList<Prestamo> obtenerPrestamosPorCliente(String DNI);

	/*


    Prestamo obtenerPrestamoPorId(int prestamoId);

    boolean actualizarPrestamo(Prestamo prestamo);

    boolean eliminarPrestamo(int prestamoId);
    */

}

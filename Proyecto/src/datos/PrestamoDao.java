package datos;

import java.util.List;

import entidad.Persona;
import entidad.Prestamo;

public interface PrestamoDao {
	boolean guardarPrestamo(Prestamo prestamo, String clienteDni, int estadoPrestamo);

    List<Prestamo> obtenerPrestamosPorCliente(int clienteId);

    Prestamo obtenerPrestamoPorId(int prestamoId);

    boolean actualizarPrestamo(Prestamo prestamo);

    boolean eliminarPrestamo(int prestamoId);

}

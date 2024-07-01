package datos;

import java.util.List;

import entidad.Prestamo;

public interface PrestamoDao {
	boolean guardarPrestamo(Prestamo prestamo);

    List<Prestamo> obtenerPrestamosPorCliente(int clienteId);

    Prestamo obtenerPrestamoPorId(int prestamoId);

    boolean actualizarPrestamo(Prestamo prestamo);

    boolean eliminarPrestamo(int prestamoId);

}

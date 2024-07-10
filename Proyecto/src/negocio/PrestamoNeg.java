package negocio;

import java.util.List;

import entidad.Prestamo;

public interface PrestamoNeg {
	
	public boolean solicitarPrestamo(Prestamo prestamo, String ClienteDni, int estadoPrestamo);

    public List<Prestamo> obtenerPrestamosPorCliente(int clienteId);

    public Prestamo obtenerPrestamoPorId(int prestamoId);

    public boolean actualizarPrestamo(Prestamo prestamo);

    public boolean eliminarPrestamo(int prestamoId);
}

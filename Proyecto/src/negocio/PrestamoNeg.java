package negocio;

import java.util.ArrayList;
import entidad.Prestamo;

public interface PrestamoNeg {
	
	public boolean solicitarPrestamo(Prestamo prestamo, String ClienteDni, int estadoPrestamo);
	
	public ArrayList<Prestamo> obtenerPrestamos();
	public ArrayList<Prestamo> obtenerPrestamosPorCliente(String DNI);

	/*
   

    public Prestamo obtenerPrestamoPorId(int prestamoId);

    public boolean actualizarPrestamo(Prestamo prestamo);

    public boolean eliminarPrestamo(int prestamoId);
    */
}

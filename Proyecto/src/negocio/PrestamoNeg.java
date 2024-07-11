package negocio;

import java.util.ArrayList;

import entidad.EstadoPrestamo;
import entidad.Prestamo;

public interface PrestamoNeg {
	
	public boolean solicitarPrestamo(Prestamo prestamo, String ClienteDni, int estadoPrestamo);
	
	public ArrayList<Prestamo> obtenerPrestamos();
	public ArrayList<Prestamo> obtenerPrestamosPorCliente(String DNI);
    public int actualizarEstadoPrestamo(int idPrestamo, int estadoPrestamo);
    public ArrayList<EstadoPrestamo> obtenerListadeEstado();

	/*
   

    public Prestamo obtenerPrestamoPorId(int prestamoId);


    public boolean eliminarPrestamo(int prestamoId);
    */
}

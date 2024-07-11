package negocioimpl;

import java.util.ArrayList;

import datos.PrestamoDao;
import datosimpl.PrestamoDaoImpl;
import entidad.EstadoPrestamo;
import entidad.Prestamo;
import negocio.PrestamoNeg;

public class PrestamoNegImpl implements PrestamoNeg {

	private PrestamoDao prestamoDao;
	public PrestamoNegImpl () {
		
		prestamoDao = new PrestamoDaoImpl();
	}
	
	@Override
	public boolean solicitarPrestamo(Prestamo prestamo, String clienteDni, int estadoPrestamo) {

		return prestamoDao.guardarPrestamo(prestamo, clienteDni, estadoPrestamo);
		
	}
	
	@Override
	public ArrayList<Prestamo> obtenerPrestamos() {
		ArrayList<Prestamo> listaPrestamos= null;
		listaPrestamos = prestamoDao.obtenerPrestamos();
		return listaPrestamos;
		
		
	}

	@Override
	public ArrayList<Prestamo> obtenerPrestamosPorCliente(String DNI) {
		// TODO Auto-generated method stub
		return prestamoDao.obtenerPrestamosPorCliente(DNI);
	}

	@Override
	public int actualizarEstadoPrestamo(int idPrestamo, int estadoPrestamo) {
		return prestamoDao.actualizarEstadoPrestamo(idPrestamo, estadoPrestamo);
	}

	@Override
	public ArrayList<EstadoPrestamo> obtenerListadeEstado() {
		ArrayList<EstadoPrestamo> listaEstadoPrestamos= null;
		listaEstadoPrestamos = prestamoDao.obtenerListadeEstado();
		return listaEstadoPrestamos;
	}


	/*
	

	@Override
	public Prestamo obtenerPrestamoPorId(int prestamoId) {
		// TODO Auto-generated method stub
		return prestamoDao.obtenerPrestamoPorId(prestamoId);
	}


	@Override
	public boolean eliminarPrestamo(int prestamoId) {
		// TODO Auto-generated method stub
		return prestamoDao.eliminarPrestamo(prestamoId);
	}

	
	*/

}

package negocioimpl;

import java.util.ArrayList;

import datos.PrestamoDao;
import datosimpl.PrestamoDaoImpl;
import entidad.EstadoPrestamo;
import entidad.PagosPrestamos;
import entidad.Prestamo;
import negocio.PrestamoNeg;

public class PrestamoNegImpl implements PrestamoNeg {

	private PrestamoDao prestamoDao;
	public PrestamoNegImpl () {
		
		prestamoDao = new PrestamoDaoImpl();
	}
	
	@Override
	public boolean guardarPrestamo(Prestamo prestamo, String clienteDni, int estadoPrestamo, int nCuenta) {

		return prestamoDao.guardarPrestamo(prestamo, clienteDni, estadoPrestamo, nCuenta);
		
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

	@Override
	public int registrarCuotas(PagosPrestamos pagosPrestamo) {
		return prestamoDao.registrarCuotas(pagosPrestamo);
	}



}

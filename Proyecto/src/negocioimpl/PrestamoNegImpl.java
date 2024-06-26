package negocioimpl;

import java.util.List;

import datos.PrestamoDao;
import datosimpl.PrestamoDaoImpl;
import entidad.Prestamo;
import negocio.PrestamoNeg;

public class PrestamoNegImpl implements PrestamoNeg {

	private PrestamoDao prestamoDao;
	public PrestamoNegImpl () {
		
		prestamoDao = new PrestamoDaoImpl();
	}
	
	@Override
	public boolean solicitarPrestamo(Prestamo prestamo) {
		// TODO Auto-generated method stub
		return prestamoDao.guardarPrestamo(prestamo);
	}

	@Override
	public List<Prestamo> obtenerPrestamosPorCliente(int clienteId) {
		// TODO Auto-generated method stub
		return prestamoDao.obtenerPrestamosPorCliente(clienteId);
	}

	@Override
	public Prestamo obtenerPrestamoPorId(int prestamoId) {
		// TODO Auto-generated method stub
		return prestamoDao.obtenerPrestamoPorId(prestamoId);
	}

	@Override
	public boolean actualizarPrestamo(Prestamo prestamo) {
		// TODO Auto-generated method stub
		return prestamoDao.actualizarPrestamo(prestamo);
	}

	@Override
	public boolean eliminarPrestamo(int prestamoId) {
		// TODO Auto-generated method stub
		return prestamoDao.eliminarPrestamo(prestamoId);
	}
	

}

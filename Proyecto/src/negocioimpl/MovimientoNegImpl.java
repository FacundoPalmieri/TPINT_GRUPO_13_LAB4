package negocioimpl;
import java.util.ArrayList;

import  datos.MovimientoDao;
import datosimpl.MovimientoDaoImpl;
import entidad.Movimientos;
import negocio.MovimientoNeg;

public class MovimientoNegImpl implements MovimientoNeg{
	
	private MovimientoDao movimientoDao;
	
	 public MovimientoNegImpl() {
	        movimientoDao = new MovimientoDaoImpl();
	    }

	@Override
	public int CrearMovimiento(int CuentaOrigen,String detalle, double importe, int CuentaDestino, int tipoMovimiento) {
		return movimientoDao.CrearMovimiento(CuentaOrigen, detalle, importe, CuentaDestino,tipoMovimiento);
	}

	@Override
	public ArrayList<Movimientos> ObtenerMovimientosPorCliente(int CuentaDestino) {
		ArrayList<Movimientos> listaMovimientos = new ArrayList<Movimientos>();
		listaMovimientos = movimientoDao.ObtenerMovimientosPorCliente(CuentaDestino);
		System.out.println("MOVIMIENTO DAO");
		return listaMovimientos;
	}

}

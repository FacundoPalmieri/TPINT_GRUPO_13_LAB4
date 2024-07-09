package negocioimpl;
import  datos.MovimientoDao;
import datosimpl.MovimientoDaoImpl;
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

}

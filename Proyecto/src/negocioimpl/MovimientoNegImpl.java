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
	public int CrearMovimiento(int nCuenta, String detalle, float Importe, int tipoMovimiento) {
		return movimientoDao.CrearMovimiento(nCuenta, detalle, Importe, tipoMovimiento);
	}

}

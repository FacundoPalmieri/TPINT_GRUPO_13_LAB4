package datos;

import java.util.ArrayList;



import entidad.Movimientos;

public interface MovimientoDao {
	
	public int CrearMovimiento (int CuentaOrigen,String detalle, double importe, int tipoMovimiento);
	public ArrayList<Movimientos> ObtenerMovimientosPorCliente (int nCuenta);
	public ArrayList<Movimientos> ObtenerMovimientosConFiltro (int nCuenta, String parametro);

}

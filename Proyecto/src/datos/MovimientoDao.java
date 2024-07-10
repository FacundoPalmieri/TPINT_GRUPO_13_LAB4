package datos;

import java.util.ArrayList;



import entidad.Movimientos;

public interface MovimientoDao {
	
	public int CrearMovimiento (int CuentaOrigen,String detalle, double importe, int CuentaDestino, int tipoMovimiento);
	public ArrayList<Movimientos> ObtenerMovimientosPorCliente (int CuentaDestino);

}

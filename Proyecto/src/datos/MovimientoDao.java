package datos;

public interface MovimientoDao {
	
	public int CrearMovimiento (int CuentaOrigen,String detalle, double importe, int CuentaDestino, int tipoMovimiento);
}

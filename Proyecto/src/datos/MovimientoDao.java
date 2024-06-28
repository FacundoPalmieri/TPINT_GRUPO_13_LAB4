package datos;

public interface MovimientoDao {
	
	public int CrearMovimiento (int nCuenta, String detalle, float Importe, int tipoMovimiento);

}

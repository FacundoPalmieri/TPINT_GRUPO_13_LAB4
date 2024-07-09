package negocio;

public interface MovimientoNeg {
	
	public int CrearMovimiento(int CuentaOrigen,String detalle, double importe, int CuentaDestino, int tipoMovimiento);

}

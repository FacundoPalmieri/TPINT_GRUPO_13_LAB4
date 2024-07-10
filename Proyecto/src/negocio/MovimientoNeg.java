package negocio;

import java.util.ArrayList;

import entidad.Movimientos;

public interface MovimientoNeg {
	
	public int CrearMovimiento(int CuentaOrigen,String detalle, double importe, int CuentaDestino, int tipoMovimiento);
	public ArrayList<Movimientos> ObtenerMovimientosPorCliente (int CuentaDestino);

}

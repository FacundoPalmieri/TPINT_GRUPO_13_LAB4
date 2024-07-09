package entidad;

import java.time.LocalDate;

public class Movimientos {
	private int id;
	private Cuenta cuenta_origen;
	private LocalDate fecha;
	private String detalle;
	private double importe;
	private Cuenta cuenta_destino;
	private TipoMovimiento tipo_Movimiento_id;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Cuenta getCuenta_origen() {
		return cuenta_origen;
	}
	public void setCuenta_origen(Cuenta cuenta_origen) {
		this.cuenta_origen = cuenta_origen;
	}
	public LocalDate getFecha() {
		return fecha;
	}
	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}
	public String getDetalle() {
		return detalle;
	}
	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}
	public double getImporte() {
		return importe;
	}
	public void setImporte(double importe) {
		this.importe = importe;
	}
	public Cuenta getCuenta_destino() {
		return cuenta_destino;
	}
	public void setCuenta_destino(Cuenta cuenta_destino) {
		this.cuenta_destino = cuenta_destino;
	}
	public TipoMovimiento getTipo_Movimiento_id() {
		return tipo_Movimiento_id;
	}
	public void setTipo_Movimiento_id(TipoMovimiento tipo_Movimiento_id) {
		this.tipo_Movimiento_id = tipo_Movimiento_id;
	}
	

}

package entidad;

import java.time.LocalDate;

public class Movimientos {
	private int id;
	private int cuenta_origen;
	private LocalDate fecha;
	private String detalle;
	private float importe;
	private int cuenta_destino;
	private int tipo_Movimiento_id;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCuenta_origen() {
		return cuenta_origen;
	}
	public void setCuenta_origen(int cuenta_origen) {
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
	public float getImporte() {
		return importe;
	}
	public void setImporte(float importe) {
		this.importe = importe;
	}
	public int getCuenta_destino() {
		return cuenta_destino;
	}
	public void setCuenta_destino(int cuenta_destino) {
		this.cuenta_destino = cuenta_destino;
	}
	public int getTipo_Movimiento_id() {
		return tipo_Movimiento_id;
	}
	public void setTipo_Movimiento_id(int tipo_Movimiento_id) {
		this.tipo_Movimiento_id = tipo_Movimiento_id;
	}
	

}

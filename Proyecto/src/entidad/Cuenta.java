package entidad;

import java.time.LocalDate;

public class Cuenta {

	private int numeroCuenta;
	private int clienteDni;
	private LocalDate fechaCreacion;
	private int idTipoCuenta;
	private String cbu;
	private float saldo;
	private int habilitado;
	public int getNumeroCuenta() {
		return numeroCuenta;
	}
	public void setNumeroCuenta(int numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}
	public int getClienteDni() {
		return clienteDni;
	}
	public void setClienteDni(int clienteDni) {
		this.clienteDni = clienteDni;
	}
	public LocalDate getFechaCreacion() {
		return fechaCreacion;
	}
	public void setFechaCreacion(LocalDate fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	public int getIdTipoCuenta() {
		return idTipoCuenta;
	}
	public void setIdTipoCuenta(int idTipoCuenta) {
		this.idTipoCuenta = idTipoCuenta;
	}
	public String getCbu() {
		return cbu;
	}
	public void setCbu(String cbu) {
		this.cbu = cbu;
	}
	public float getSaldo() {
		return saldo;
	}
	public void setSaldo(float saldo) {
		this.saldo = saldo;
	}
	public int getHabilitado() {
		return habilitado;
	}
	public void setHabilitado(int habilitado) {
		this.habilitado = habilitado;
	}
	
	
}
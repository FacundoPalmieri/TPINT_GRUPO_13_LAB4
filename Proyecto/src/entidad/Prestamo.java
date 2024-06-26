package entidad;

import java.time.LocalDate;

public class Prestamo {

	private int id;
	private String clienteDni;
	private LocalDate fecha;
	private float importeSolicitado;
	private float importeAPagar;
	private float importeCuota;
	private int cuotas;
	private int estado;
	private int cuotasAbonadas;
	private float saldoRestante;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getClienteDni() {
		return clienteDni;
	}
	public void setClienteDni(String clienteDni) {
		this.clienteDni = clienteDni;
	}
	public LocalDate getFecha() {
		return fecha;
	}
	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}
	public float getImporteSolicitado() {
		return importeSolicitado;
	}
	public void setImporteSolicitado(float importeSolicitado) {
		this.importeSolicitado = importeSolicitado;
	}
	public float getImporteAPagar() {
		return importeAPagar;
	}
	public void setImporteAPagar(float importeAPagar) {
		this.importeAPagar = importeAPagar;
	}
	public float getImporteCuota() {
		return importeCuota;
	}
	public void setImporteCuota(float importeCuota) {
		this.importeCuota = importeCuota;
	}
	public int getCuotas() {
		return cuotas;
	}
	public void setCuotas(int cuotas) {
		this.cuotas = cuotas;
	}
	public int getEstado() {
		return estado;
	}
	public void setEstado(int estado) {
		this.estado = estado;
	}
	public int getCuotasAbonadas() {
		return cuotasAbonadas;
	}
	public void setCuotasAbonadas(int cuotasAbonadas) {
		this.cuotasAbonadas = cuotasAbonadas;
	}
	public float getSaldoRestante() {
		return saldoRestante;
	}
	public void setSaldoRestante(float saldoRestante) {
		this.saldoRestante = saldoRestante;
	}
	
	
	
}

package entidad;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Prestamo {

	private int id;
	private Persona clienteDni;
	private LocalDate fecha;
	private float importeSolicitado;
	private float importeAPagar;
	private float importeCuota;
	private int cuotas;
	private EstadoPrestamo estado;
	private int cuotasAbonadas;
	private float saldoRestante;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Persona getClienteDni() {
		return clienteDni;
	}
	public void setClienteDni(Persona clienteDni) {
		this.clienteDni = clienteDni;
	}
	public LocalDate getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	    this.fecha = LocalDate.parse(fecha, formatter);
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
	public EstadoPrestamo getEstado() {
		return estado;
	}
	public void setEstado(EstadoPrestamo estado) {
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

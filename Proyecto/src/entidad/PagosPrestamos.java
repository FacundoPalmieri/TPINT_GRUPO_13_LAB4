package entidad;


import java.time.LocalDate;



public class PagosPrestamos {

	private int id;
	private int idPrestamo;
	private LocalDate fechaPago;
	private float importePago;
	private int cuota;
	private int estado;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getIdPrestamo() {
		return idPrestamo;
	}
	public void setIdPrestamo(int idPrestamo) {
		this.idPrestamo = idPrestamo;
	}
	public LocalDate getFechaPago() {
		return fechaPago;
	}
	public void setFechaPago(LocalDate fechaPago) {
		this.fechaPago = fechaPago;
	}
	public float getImportePago() {
		return importePago;
	}
	public void setImportePago(float importePago) {
		this.importePago = importePago;
	}
	public int getCuota() {
		return cuota;
	}
	public void setCuota(int cuota) {
		this.cuota = cuota;
	}
	public int getEstado() {
		return estado;
	}
	public void setEstado(int estado) {
		this.estado = estado;
	}

	
	
	
	
}

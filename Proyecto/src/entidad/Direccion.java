package entidad;

public class Direccion {
	
	private int id;
	
	private String calle;
	private int altura;
	private String piso;
	private String departamento;
	private int localidad_id;
	private Localidad localidad;
	
	
	public Localidad getLocalidad() {
		return localidad;
	}
	
	public void setLocalidad(Localidad localidad) {
		this.localidad = localidad;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCalle() {
		return calle;
	}
	public void setCalle(String calle) {
		this.calle = calle;
	}
	public int getAltura() {
		return altura;
	}
	public void setAltura(int altura) {
		this.altura = altura;
	}
	public String getPiso() {
		return piso;
	}
	public void setPiso(String piso) {
		this.piso = piso;
	}
	public String getDepartamento() {
		return departamento;
	}
	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}
	public int getLocalidad_id() {
		return localidad_id;
	}
	public void setLocalidad_id(int localidad_id) {
		this.localidad_id = localidad_id;
	}


}

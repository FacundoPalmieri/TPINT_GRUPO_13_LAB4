package entidad;

public class TipoCuenta {

	private int id;
	private String descripcion;
	
	
	
	public TipoCuenta(int id, String descripcion) {
		super();
		this.id = id;
		this.descripcion = descripcion;
	}
	
	public TipoCuenta() {
	
		this.id = 0;
		this.descripcion = "";
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	

}

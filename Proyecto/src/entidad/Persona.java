package entidad;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Persona {
	private int id;
    private String dni;
    private String cuil;
    private String nombre;
    private String apellido;
    private String sexo;
    private String Celular;
    private String Telefono;
    private int direccion_id;
	private String nacionalidad;
    private LocalDate fechaNacimiento;
    private String email;
    private Usuario usuario;
    private Direccion direccion;
    

	public Direccion getDireccion() {
		return direccion;
	}

	public void setDireccion(Direccion direccion) {
		this.direccion = direccion;
	}
    


	public Usuario getUsuario() {
		return usuario;
	}
    
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public int getDireccion_id() {
		return direccion_id;
	}
	
	public void setDireccion_id(int direccion_id) {
		this.direccion_id = direccion_id;
	}

    
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public String getCuil() {
		return cuil;
	}
	public void setCuil(String cuil) {
		this.cuil = cuil;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	public String getCelular() {
		return Celular;
	}
	public void setCelular(String celular) {
		Celular = celular;
	}
	public String getTelefono() {
		return Telefono;
	}
	public void setTelefono(String telefono) {
		Telefono = telefono;
	}
	public String getNacionalidad() {
		return nacionalidad;
	}
	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}
	 public String getFechaNacimiento() {
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyy-MM-dd");
	        return this.fechaNacimiento.format(formatter);
	    }
	 
	

	  public void setFechaNacimiento(String fechaNacimiento) {
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyy-MM-dd");
	        this.fechaNacimiento = LocalDate.parse(fechaNacimiento, formatter);
	    }


    
    
    
    
}
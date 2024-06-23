package entidad;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Usuario {
	private int id;
    private String dni;
    private String cuil;
    private String nombre;
    private String apellido;
    private String sexo;
    private String Celular;
    private String Telefono;
    private String nacionalidad;
    private LocalDate fechaNacimiento;
    private String direccion;
    private String provincia;
    private String localidad;
    private String email;
    private String usuario;
    private String contrasena;
    private int tipoUsuarioId;
    private int habilitado;


    public Usuario() {
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


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getUsuario() {
		return usuario;
	}


	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}


	public String getContrasena() {
		return contrasena;
	}


	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}


	public int getTipoUsuarioId() {
		return tipoUsuarioId;
	}


	public void setTipoUsuarioId(int tipoUsuarioId) {
		this.tipoUsuarioId = tipoUsuarioId;
	}


	public int getHabilitado() {
		return habilitado;
	}


	public void setHabilitado(int habilitado) {
		this.habilitado = habilitado;
	}


	public String getDireccion() {
		return direccion;
	}


	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}


	public String getProvincia() {
		return provincia;
	}


	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}


	public String getLocalidad() {
		return localidad;
	}


	public void setLocalidad(String localidad) {
		this.localidad = localidad;
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
	
	
    
    

}

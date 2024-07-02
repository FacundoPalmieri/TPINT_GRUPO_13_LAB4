package entidad;


public class Usuario {
	private int id;
    private String persona_dni;
    private String usuario;
    private String Pass;
    private int tipoUsuarioId;
    private int habilitado;


 
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getPersona_dni() {
		return persona_dni;
	}


	public void setPersona_dni(String persona_dni) {
		this.persona_dni = persona_dni;
	}


	public String getUsuario() {
		return usuario;
	}


	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}


	public String getPass() {
		return Pass;
	}


	public void setPass(String contrasena) {
		this.Pass = contrasena;
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
       
    

}

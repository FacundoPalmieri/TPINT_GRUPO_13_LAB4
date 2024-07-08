package datos;
import java.util.ArrayList;

import entidad.Direccion;
import entidad.Persona;
import entidad.Usuario;

public interface UsuarioDao {
	
	  public int validarLogin(String usuario, String contrasenia);
	  
	  public boolean validarUsuario(String DNI, String usuario);
	
	  public boolean agregarCliente(Usuario usuario, Persona persona, Direccion direccion);
	  
	  public Usuario ObtenerUsuario (String usuario);
	  
	  public Persona ObtenerCliente (String usuario);
	  
	  public Usuario ObtenerUsuarioPorDni (String DNI);
	  
	  public boolean editarUsuario(Usuario usuario);
	  
	  public boolean editarContrasena(Usuario usuario);
	  
	  public boolean eliminarUsuario(Usuario usuario);
	   
	  public ArrayList<Usuario> listarUsuarios();
	  
	  public ArrayList<Persona> listarPersonas();
	  
	  public ArrayList <Direccion> listarDirecciones();
	  
	  public ArrayList<Persona> listarPersonasComposicion();
}

package datos;
import java.util.ArrayList;

import entidad.Direccion;
import entidad.Localidad;
import entidad.Persona;
import entidad.Provincia;
import entidad.Usuario;

public interface UsuarioDao {
	
	  public int validarLogin(String usuario, String contrasenia);
	  
	  public boolean validarDNI(String DNI);
	
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
	  
	  public Direccion ObtenerDireccionCliente (int IDdireccion);
	  
	  public Provincia ObtenerProvinciaCliente (int IDprovincia);
	  
	  public Localidad ObtenerLocalidadCliente (int IDlocalidad);
	  
	  public ArrayList<Persona> listarPersonasComposicion();
	  
	  public Persona ObtenerPersonaCompleta(String usuario);
	  
	  public Usuario obtenerUsuarioEstado1o2(String usuario);
	  
	  public Persona obtenerClientePorDNI (int dni);
	  
	  public Persona obtenerPersonaCompleta(String dni);
	  
	  public boolean actualizarPersonaCompleta(Persona persona);
	  
	  public int validarMail(String email);
	  
	  public boolean validarUsuario(String usuario);
	  
	  
}

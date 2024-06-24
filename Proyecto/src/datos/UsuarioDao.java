package datos;
import java.util.ArrayList;

import entidad.Usuario;

public interface UsuarioDao {
	
	  public int validarLogin(String usuario, String contrasenia);
	  
	  public boolean validarUsuario(String DNI, String usuario);
	
	  public boolean agregarCliente(Usuario usuario);
	  
	  public Usuario ObtenerUsuario (String usuario);
	  
	  public boolean editarUsuario(Usuario usuario);
	   
	  public ArrayList<Usuario> listarUsuarios();
}

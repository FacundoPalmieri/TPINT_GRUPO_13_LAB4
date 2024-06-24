package negocio;

import java.util.ArrayList;

import entidad.Usuario;

public interface UsuarioNeg {
	
	public int validarLogin(String usuario, String contrasenia);
	public boolean validarUsuario(String DNI, String usuario);
	public boolean agregarCliente (Usuario usuario);
	public Usuario obtenerCliente (String usuario);
	public Usuario obtenerClientePorDNI (String DNI);
	public boolean editarUsuario(Usuario usuario);
	public boolean editarContraseña(Usuario usuario);
	public ArrayList<Usuario> listaUsuarios();
}

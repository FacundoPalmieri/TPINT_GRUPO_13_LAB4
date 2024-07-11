package negocio;

import java.util.ArrayList;

import entidad.Direccion;
import entidad.Localidad;
import entidad.Persona;
import entidad.Provincia;
import entidad.Usuario;

public interface UsuarioNeg {
	
	public int validarLogin(String usuario, String contrasenia);
	public boolean validarUsuario(String DNI, String usuario);
	public boolean agregarCliente (Usuario usuario, Persona persona, Direccion direccion);
	public Usuario obtenerUsuario (String usuario);
	public Usuario obtenerUsuarioPorDNI (String DNI);
	public Persona ObtenerCliente (String usuario);
	public boolean editarUsuario(Usuario usuario);
	public boolean editarContrasena(Usuario usuario);
	public boolean eliminarUsuario(Usuario usuario);
	public ArrayList<Usuario> listaUsuarios();
    public ArrayList<Persona> listarPersonas();
    public ArrayList <Direccion> listarDirecciones();
    public Direccion ObtenerDireccionCliente (int IDdireccion);
	public Provincia ObtenerProvinciaCliente (int IDprovincia);
	public Localidad ObtenerLocalidadCliente (int IDlocalidad);
	public ArrayList<Persona> listarPersonasComposicion();
	public Persona ObtenerPersonaCompleta(String usuario);
}

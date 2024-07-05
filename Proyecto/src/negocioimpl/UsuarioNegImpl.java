package negocioimpl;

import java.util.ArrayList;

import datos.UsuarioDao;
import datosimpl.UsuarioDaoImpl;
import entidad.Direccion;
import entidad.Persona;
import entidad.Usuario;
import negocio.UsuarioNeg;

public class UsuarioNegImpl implements UsuarioNeg{
	private UsuarioDao usuarioDao;

    public UsuarioNegImpl() {
        usuarioDao = new UsuarioDaoImpl();
    }
    
    
    @Override
	public int validarLogin(String usuario, String contrasenia) {
		return usuarioDao.validarLogin(usuario, contrasenia);
		
	}

    @Override
    public boolean validarUsuario(String DNI, String usuario) {
    	
        return usuarioDao.validarUsuario(DNI, usuario);
    }

	@Override
	public boolean agregarCliente(Usuario usuario, Persona persona, Direccion direccion) {
		return usuarioDao.agregarCliente(usuario, persona, direccion);
	}


	@Override
	public Usuario obtenerUsuario(String usuario) {
		return usuarioDao.ObtenerUsuario(usuario);
	}
	

	@Override
	public Persona ObtenerCliente(String usuario) {
		return usuarioDao.ObtenerCliente(usuario);
	}

	
	@Override
	public Usuario obtenerUsuarioPorDNI(String DNI) {
		return usuarioDao.ObtenerUsuarioPorDni(DNI);
	}


	@Override
	public boolean editarUsuario(Usuario usuario) {
		// TODO Auto-generated method stub
		return usuarioDao.editarUsuario(usuario);
	}
	
	@Override
	public boolean editarContrasena(Usuario usuario) {
		return usuarioDao.editarContrasena(usuario);
	}


	
	public ArrayList<Usuario> listaUsuarios(){
		ArrayList<Usuario> lista = null;
		lista = usuarioDao.listarUsuarios();
		return lista;
	}


	@Override
	public boolean eliminarUsuario(Usuario usuario) {
		return usuarioDao.eliminarUsuario(usuario);
	}



	

	
}

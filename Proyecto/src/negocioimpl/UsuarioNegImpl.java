package negocioimpl;

import java.util.ArrayList;

import datos.UsuarioDao;
import datosimpl.UsuarioDaoImpl;
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
	public boolean agregarCliente(Usuario usuario) {
		return usuarioDao.agregarCliente(usuario);
	}


	@Override
	public Usuario obtenerCliente(String usuario) {
		return usuarioDao.ObtenerUsuario(usuario);
	}
	
	@Override
	public Usuario obtenerClientePorDNI(String DNI) {
		return usuarioDao.ObtenerUsuarioPorDni(DNI);
	}


	@Override
	public boolean editarUsuario(Usuario usuario) {
		// TODO Auto-generated method stub
		return usuarioDao.editarUsuario(usuario);
	}
	
	@Override
	public boolean editarContraseña(Usuario usuario) {
		return usuarioDao.editarContraseña(usuario);
	}


	
	public ArrayList<Usuario> listaUsuarios(){
		ArrayList<Usuario> lista = null;
		lista = usuarioDao.listarUsuarios();
		return lista;
	}


	

	
}

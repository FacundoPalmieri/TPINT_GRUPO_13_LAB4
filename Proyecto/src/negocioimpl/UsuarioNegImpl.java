package negocioimpl;

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
	public boolean validarLogin(String usuario, String contrasenia) {
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
	public boolean editarUsuario(Usuario usuario) {
		// TODO Auto-generated method stub
		return usuarioDao.editarUsuario(usuario);
	}

	
	
}

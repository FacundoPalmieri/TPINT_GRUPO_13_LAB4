package datos;

import java.util.ArrayList;

import entidad.Movimientos;
import entidad.Prestamo;
import excepcion.DniInvalido;
import excepcion.UsuarioInhabilitado;

public interface ReporteDao {
	
	public boolean busquedaDNI(String dni);
	
	public boolean busquedaUsuario(String nombreUsuario) throws UsuarioInhabilitado;
	
	public ArrayList<Prestamo> prestamos(String dni, int estado);
	
	public ArrayList<Movimientos> movimientos();
}

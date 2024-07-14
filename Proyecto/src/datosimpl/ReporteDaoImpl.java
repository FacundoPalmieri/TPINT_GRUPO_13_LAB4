package datosimpl;

import java.util.ArrayList;

import datos.ReporteDao;
import entidad.Movimientos;
import entidad.Prestamo;
import excepcion.UsuarioInhabilitado;

public class ReporteDaoImpl implements ReporteDao {
	private Conexion cn;
	
	
	public boolean busquedaDNI(String dni) {
		cn = new Conexion();
		String query = "";
		
		System.out.println("QUERY OBTENER DNI: " + query);
		try {
			cn.Open();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			cn.close();
		}
		return false;
	}
	
	
	public boolean busquedaUsuario(String nombreUsuario) throws UsuarioInhabilitado{
		cn = new Conexion();
		String query = "";
		
		System.out.println("QUERY OBTENER USUARIO: " + query);
		try {
			cn.Open();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			cn.close();
		}
		
		return false;
	}
	
	
	
	public ArrayList<Prestamo> prestamos(String dni, int estado){
		return new ArrayList<Prestamo>();
	}
	
	
	
	public ArrayList<Movimientos> movimientos(){
		return new ArrayList<Movimientos>();
	}

}

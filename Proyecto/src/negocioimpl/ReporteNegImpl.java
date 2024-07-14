package negocioimpl;

import java.util.ArrayList;

import datosimpl.ReporteDaoImpl;
import entidad.Movimientos;
import entidad.Prestamo;
import excepcion.DniInexistente;
import excepcion.UsuarioInhabilitado;
import negocio.ReporteNeg;

public class ReporteNegImpl implements ReporteNeg{
	private ReporteDaoImpl reporteDao;
	
	
	public boolean busquedaDNI(String dni) throws DniInexistente {
		boolean resultado = false;
		try {
			resultado = reporteDao.busquedaDNI(dni);
		}
		catch(Exception e) {
			e.getMessage();
		}
		return resultado;
	}

	
	public boolean busquedaUsuario(String nombreUsuario) throws UsuarioInhabilitado {
		boolean resultado = false;
		try {
			resultado = reporteDao.busquedaUsuario(nombreUsuario);
		}
		catch(Exception e) {
			e.getMessage();
		}
		return resultado;
	} 
	
	
	public ArrayList<Prestamo> prestamos(String dni, int estado){
		return new ArrayList<Prestamo>();
	}
	
	
	public ArrayList<Movimientos> movimientos(){
		return new ArrayList<Movimientos> ();
	}

}

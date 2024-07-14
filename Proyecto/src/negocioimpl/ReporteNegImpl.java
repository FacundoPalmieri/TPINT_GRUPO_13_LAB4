package negocioimpl;

import java.util.ArrayList;

import datosimpl.ReporteDaoImpl;
import entidad.Movimientos;
import entidad.Prestamo;
import excepcion.DniInvalido;
import excepcion.UsuarioInhabilitado;
import negocio.ReporteNeg;

public class ReporteNegImpl implements ReporteNeg{
	private ReporteDaoImpl reporteDao;
	
	
	@Override
	public boolean validarFormatoDNI(String dni) throws DniInvalido {
		if(dni.length()>8) {
			throw new DniInvalido();
		}
		
		try {
			Integer.parseInt(dni);
			return true;
		}
		catch(Exception e) {
			throw new DniInvalido();
		}
	}
	
	
	public boolean busquedaDNI(String dni) {
		boolean resultado = false;
		resultado = reporteDao.busquedaDNI(dni);
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

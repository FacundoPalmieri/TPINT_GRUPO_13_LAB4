package negocioimpl;

import java.util.ArrayList;

import datosimpl.ReporteDaoImpl;
import entidad.Movimientos;
import entidad.Prestamo;
import negocio.ReporteNeg;

public class ReporteNegImpl implements ReporteNeg{
	private ReporteDaoImpl reporteDao;
	
	
	public boolean busquedaDNI(String dni) {
		boolean resultado = false;
		resultado = reporteDao.busquedaDNI(dni);
		return resultado;
	}

	
	public boolean busquedaUsuario(String nombreUsuario) {
		boolean resultado = false;
		resultado = reporteDao.busquedaUsuario(nombreUsuario);
		return resultado;
	} 
	
	
	public ArrayList<Prestamo> prestamos(String dni, int estado){
		return reporteDao.prestamos(dni,estado);
	}
	
	
	public ArrayList<Movimientos> movimientos(){
		return new ArrayList<Movimientos> ();
	}

}

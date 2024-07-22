package negocioimpl;

import java.time.LocalDate;
import java.util.ArrayList;

import datos.ReporteDao;
import datosimpl.ReporteDaoImpl;
import entidad.Movimientos;
import entidad.Prestamo;
import negocio.ReporteNeg;

public class ReporteNegImpl implements ReporteNeg{
	private ReporteDao reporteDao;
	
	public ReporteNegImpl() {
		reporteDao= new ReporteDaoImpl();
	}
	
	
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
	
	
	public ArrayList<Prestamo> prestamos(String dni, ArrayList<Integer> estado, LocalDate fecha1, LocalDate fecha2){
		ArrayList<Prestamo> prestamos = null;
		prestamos = reporteDao.prestamos(dni,estado,fecha1,fecha2);
		return prestamos;
	}
	
	
	public ArrayList<Movimientos> movimientos(){
		return new ArrayList<Movimientos> ();
	}


	@Override
	public ArrayList<Movimientos> PromedioIngresosMensuales(String fechaInicio, String fechaFin) {
		ArrayList<Movimientos> listaMovimientos = new ArrayList<Movimientos>();
		listaMovimientos =  reporteDao.PromedioIngresosMensuales(fechaInicio, fechaFin);
		return listaMovimientos;
		
	}

}

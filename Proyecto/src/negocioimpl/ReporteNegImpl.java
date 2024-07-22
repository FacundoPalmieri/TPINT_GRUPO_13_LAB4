package negocioimpl;

import java.time.LocalDate;
import java.util.ArrayList;

import datos.ReporteDao;
import datosimpl.ReporteDaoImpl;
import entidad.Movimientos;
import entidad.PagosPrestamos;
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


	@Override
	public ArrayList<Movimientos> PromedioIngresosMensuales(String fechaInicio, String fechaFin) {
		ArrayList<Movimientos> listaMovimientos = new ArrayList<Movimientos>();
		listaMovimientos =  reporteDao.PromedioIngresosMensuales(fechaInicio, fechaFin);
		return listaMovimientos;
	}
	
	
	//Este metodo recibe un ArrayList de Prestamos y obtiene los pagos
		public ArrayList<Prestamo> verificarPagos(ArrayList<Prestamo> prestamos){
			//Por cada prestamo busca en la db el estado de esos pagos con el idPrestamo
			for (Prestamo p : prestamos ){
				ArrayList<PagosPrestamos> pagos = new ArrayList<PagosPrestamos>();
				pagos = reporteDao.pagosPrestamos(p.getId());
				
				//Si el prestamo tiene pagos, el ArrayList de PagosPrestamos se agrega a el Prestamo
				if (pagos.size()>0) {
					p.setPagosPrestamos(pagos);
				}
			}
			
			//Se devuelve el mismo ArrayList de Prestamos pero con el ArrayList PagosPrestamos seteado en cada Prestamo
			return prestamos;
		}

}

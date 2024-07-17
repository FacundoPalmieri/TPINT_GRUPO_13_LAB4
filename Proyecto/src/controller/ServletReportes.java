package controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entidad.Cuenta;
import entidad.EstadoPrestamo;
import entidad.PagosPrestamos;
import entidad.Prestamo;
import excepcion.DniInvalido;
import excepcion.FechaInvalida;
import excepcion.Validacion;
import negocio.CuentaNeg;
import negocio.MovimientoNeg;
import negocio.PrestamoNeg;
import negocio.ReporteNeg;
import negocioimpl.CuentaNegImpl;
import negocioimpl.MovimientoNegImpl;
import negocioimpl.PrestamoNegImpl;
import negocioimpl.ReporteNegImpl;

/**
 * Servlet implementation class ServletReportes
 */
@WebServlet("/ServletReportes")
public class ServletReportes extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ReporteNeg reporteNeg; //ver
	private PrestamoNeg prestamoNeg;
	private CuentaNeg cuentaNeg;
	private MovimientoNeg movimientoNeg;
       
	 public void init() throws ServletException {
	        super.init();
	        reporteNeg = new ReporteNegImpl(); //ver
	        prestamoNeg = new PrestamoNegImpl();
	        cuentaNeg = new CuentaNegImpl();
	        movimientoNeg = new MovimientoNegImpl();
	        
	    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	
	/*		ArrayList<Cuenta> listaCuentas = new ArrayList<Cuenta>();
			ArrayList<Prestamo> listaPrestamosCliente = new ArrayList<Prestamo>();
	*/			
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//HttpSession session = request.getSession();
		String DNI = (String) request.getParameter("dniCliente");
		String ma = (String)request.getParameter("fecha1");
		String ma2 = (String)request.getParameter("fecha2");
		LocalDate fecha1;
		LocalDate fecha2;
		//Se valida que el dni sea numero y no mayor a 20 caracteres
		//Se valida que la fecha2 no sea mayor a fecha1 y que las fechas no excedan al dia de hoy
		try {
			Validacion.validarFormatoDNI(DNI);	
			YearMonth mesAnio = YearMonth.parse(ma, DateTimeFormatter.ofPattern("yyyy-MM"));
			fecha1 = mesAnio.atDay(1);
			YearMonth mesAnio2 = YearMonth.parse(ma2, DateTimeFormatter.ofPattern("yyyy-MM"));
			fecha2 = mesAnio2.atDay(1);
			System.out.println("DESDE: "+fecha1+" HASTA: "+fecha2);
			Validacion.verificarFechas(fecha1, fecha2);
		}
		catch(DniInvalido dniI) {
			System.out.println("Error: "+dniI.getMessage());
			return;
		}
		catch(FechaInvalida fi) {
			System.out.println("Error: "+fi.getMessage());
			return;
		}
		catch(Exception e) {
			System.out.println("Error: "+e.getMessage());
			return;
		}
		
		//REPORTE
		if(request.getParameter("btnReporte")!=null) {
			System.out.println("reporte");
			ArrayList<Prestamo> listaPrestamos = new ArrayList<Prestamo>();
			ArrayList<Integer> estados = null;
			listaPrestamos = reporteNeg.prestamos(DNI,estados,fecha1,fecha2);
			
			if(listaPrestamos != null) {
				System.out.println("reporte prestamos");
				
				request.setAttribute("listaPrestamos", listaPrestamos);	

				RequestDispatcher dispatcher = request.getRequestDispatcher("/Reporte.jsp");
				dispatcher.forward(request, response);	
				
			}else {
				   request.setAttribute("Mensaje","No hay prestamos solicitados ");
				   RequestDispatcher dispatcher = request.getRequestDispatcher("/Reporte.jsp");
		           dispatcher.forward(request, response);
				
			}
		
		}		
		//doGet(request, response);
	}

}

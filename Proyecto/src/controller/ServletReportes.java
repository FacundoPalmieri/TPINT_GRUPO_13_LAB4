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
import excepcion.fechaInvalida;
import excepcion.validacion;
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
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if(request.getParameter("btnReporte")!=null) {
			System.out.println("reporte");
			String DNI = (String) request.getParameter("dniCliente");
			String ma = (String)request.getParameter("fecha1");
			String ma2 = (String)request.getParameter("fecha2");
			LocalDate fecha1;
			LocalDate fecha2;
			
			//Se valida que el dni sea numero y no mayor a 20 caracteres
			//Se valida que la fecha2 no sea mayor a fecha1 y que las fechas no excedan al dia de hoy
			try {
				validacion.validarFormatoDNI(DNI);	
				if(!reporteNeg.busquedaDNI(DNI)) {
					request.setAttribute("dniInexistente","El dni no existe");
					RequestDispatcher dispatcher = request.getRequestDispatcher("/InicioReporte.jsp");
					dispatcher.forward(request, response);	
					return;
				}
				fecha1 = LocalDate.parse(ma, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
				fecha2 = LocalDate.parse(ma2, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
				System.out.println("DESDE: "+fecha1+" HASTA: "+fecha2);
				validacion.verificarFechas(fecha1, fecha2);
			}
			catch(DniInvalido dniI) {
				System.out.println("Error: "+dniI.getMessage());
				request.setAttribute("errorDni",dniI.getMessage());
				RequestDispatcher dispatcher = request.getRequestDispatcher("/InicioReporte.jsp");
				dispatcher.forward(request, response);	
				return;
			}
			catch(fechaInvalida fi) {
				System.out.println("Error: "+fi.getMessage());
				System.out.println("Error: "+fi.getMessage());
				request.setAttribute("errorFecha",fi.getMessage());
				RequestDispatcher dispatcher = request.getRequestDispatcher("/InicioReporte.jsp");
				dispatcher.forward(request, response);	
				return;
			}
			catch(Exception e) {
				System.out.println("Error: "+e.getMessage());
				return;
			}
			ArrayList<Prestamo> listaPrestamos = new ArrayList<Prestamo>();
			ArrayList<Integer> estados = null;
			
			String[] checkBox = request.getParameterValues("opciones");
			if(checkBox!=null) {
				estados = new ArrayList<Integer>();
				for (String opcion : checkBox) {
					estados.add(Integer.parseInt(opcion));
				}
			}
			
			listaPrestamos = reporteNeg.prestamos(DNI,estados,fecha1,fecha2);
			
			if(listaPrestamos.size()==0) {
				request.setAttribute("sinPrestamos","El cliente no tiene prestamos en esas fechas y/o en el estado seleccionado");
				RequestDispatcher dispatcher = request.getRequestDispatcher("/InicioReporte.jsp");
				dispatcher.forward(request, response);	
			}
			
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
	}

}

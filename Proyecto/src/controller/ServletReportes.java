package controller;

import java.io.IOException;
import java.time.LocalDate;
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
			HttpSession session = request.getSession();
			String DNI = (String) session.getAttribute("dni");
					  
			
			//REPORTE
			if(request.getParameter("btnReporte")!=null) {
				System.out.println("reporte");
				
				ArrayList<Prestamo> listaPrestamos = new ArrayList<Prestamo>();
				
				
				listaPrestamos = ReporteNeg.prestamos();
				
				
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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

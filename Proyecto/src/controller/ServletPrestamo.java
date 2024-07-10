package controller;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entidad.Cuenta;
import entidad.Prestamo;
import negocio.CuentaNeg;
import negocio.PrestamoNeg;
import negocioimpl.CuentaNegImpl;
import negocioimpl.PrestamoNegImpl;


@WebServlet("/ServletPrestamo")
public class ServletPrestamo extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PrestamoNeg prestamoNeg;
	private CuentaNeg cuentaNeg;
       
	 public void init() throws ServletException {
	        super.init();
	        prestamoNeg = new PrestamoNegImpl();
	        cuentaNeg = new CuentaNegImpl();
	    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(request.getParameter("Param")!= null) {
		ArrayList<Cuenta> listaCuentas = new ArrayList<Cuenta>();
		ArrayList<Prestamo> listaPrestamosCliente = new ArrayList<Prestamo>();
		
		HttpSession session = request.getSession();
		String DNI = (String) session.getAttribute("dni");
		System.out.println("dni del cliente en servlet prestamos: " + DNI);
				   
		listaCuentas = cuentaNeg.obtenerCuentasPorDNI(DNI);
		listaPrestamosCliente = prestamoNeg.obtenerPrestamosPorCliente(DNI);
		    
		request.setAttribute("listaCuentas", listaCuentas);
		request.setAttribute("listaPrestamos", listaPrestamosCliente);
		System.out.println("listacuentas"+  listaCuentas);
		System.out.println("listaprestamos"+  listaPrestamosCliente);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/ClientePrestamo.jsp");
		dispatcher.forward(request, response);	
		
		}	
		
		
		if(request.getParameter("PrestamoAdmin")!= null) {
			
			ArrayList<Prestamo> listaPrestamos = new ArrayList<Prestamo>();
			
			listaPrestamos = prestamoNeg.obtenerPrestamos();
			
			if(listaPrestamos != null) {
				request.setAttribute("listaPrestamos", listaPrestamos);	
				RequestDispatcher dispatcher = request.getRequestDispatcher("/AdminPrestamo.jsp");
				dispatcher.forward(request, response);	
				
			}else {
				   request.setAttribute("Mensaje","No hay prestamos solicitados ");
				   RequestDispatcher dispatcher = request.getRequestDispatcher("/AdminPrestamo.jsp");
		           dispatcher.forward(request, response);
				
			}
		
		}
	
	}
	
	
		

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("btnSolicitarPrestamo")!= null) {
			
			HttpSession session = request.getSession();
			String DNI = (String) session.getAttribute("dni");
			Prestamo prestamo = new Prestamo();
			boolean estadoPrestamo = false;

		
			prestamo.setFecha(request.getParameter("fecha"));
			prestamo.setImporteSolicitado(Float.parseFloat(request.getParameter("importeSolicitado")));
			prestamo.setImporteAPagar(Float.parseFloat(request.getParameter("importeTotal")));
			prestamo.setImporteCuota(Float.parseFloat(request.getParameter("importeCuotas")));
			prestamo.setCuotas(Integer.parseInt(request.getParameter("cuotas")));
			prestamo.setCuotasAbonadas(0);
			prestamo.setSaldoRestante(Float.parseFloat(request.getParameter("importeTotal")));
			
			estadoPrestamo = prestamoNeg.solicitarPrestamo(prestamo, DNI, 1);
			
			if(estadoPrestamo == true) {
				
			   request.setAttribute("Mensaje","Préstamo solicitado");
			   RequestDispatcher dispatcher = request.getRequestDispatcher("/ClientePrestamo.jsp");
	           dispatcher.forward(request, response);
	
			}else {
				   request.setAttribute("Mensaje","Ups! ha ocurrido un error inesperado ");
				   RequestDispatcher dispatcher = request.getRequestDispatcher("/ClientePrestamo.jsp");
		           dispatcher.forward(request, response);
				
			}	
			
		}
		
	
    }

}

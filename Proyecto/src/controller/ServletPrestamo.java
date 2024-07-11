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
import entidad.EstadoPrestamo;
import entidad.Prestamo;
import negocio.CuentaNeg;
import negocio.MovimientoNeg;
import negocio.PrestamoNeg;
import negocioimpl.CuentaNegImpl;
import negocioimpl.MovimientoNegImpl;
import negocioimpl.PrestamoNegImpl;


@WebServlet("/ServletPrestamo")
public class ServletPrestamo extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PrestamoNeg prestamoNeg;
	private CuentaNeg cuentaNeg;
	private MovimientoNeg movimientoNeg;
       
	 public void init() throws ServletException {
	        super.init();
	        prestamoNeg = new PrestamoNegImpl();
	        cuentaNeg = new CuentaNegImpl();
	        movimientoNeg = new MovimientoNegImpl();
	        
	    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Lista cuentas y prestamos en vista CLIENTE
		
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
		
		
		//LISTADO PRESTAMOS ADMIN 
		if(request.getParameter("PrestamoAdmin")!= null) {
			System.out.println("entra a PrestamoAdmin");
			
			ArrayList<Prestamo> listaPrestamos = new ArrayList<Prestamo>();
			ArrayList<EstadoPrestamo> listaEstadosPrestamo= new ArrayList<EstadoPrestamo>();
			
			
			listaPrestamos = prestamoNeg.obtenerPrestamos();
			listaEstadosPrestamo = prestamoNeg.obtenerListadeEstado();
			
			
			if(listaPrestamos != null && listaEstadosPrestamo != null) {
				System.out.println("completa lista prestamos");
				
				request.setAttribute("listaPrestamos", listaPrestamos);	
				request.setAttribute("listaEstadosPrestamo", listaEstadosPrestamo);	
				RequestDispatcher dispatcher = request.getRequestDispatcher("/AdminPrestamo.jsp");
				dispatcher.forward(request, response);	
				
			}else {
				   request.setAttribute("Mensaje","No hay prestamos solicitados ");
				   RequestDispatcher dispatcher = request.getRequestDispatcher("/AdminPrestamo.jsp");
		           dispatcher.forward(request, response);
				
			}
		
		}
		
		
		//ACTUALIZACIÓN ESTADO PRESTAMO ADMIN
		if(request.getParameter("idPrestamo")!= null) {

	     int idPrestamo =  Integer.parseInt(request.getParameter("idPrestamo"));
		 int estadoPrestamo = Integer.parseInt(request.getParameter("estadoPrestamo"));
		 
	
		
		 int estadoActualizacion = prestamoNeg.actualizarEstadoPrestamo(idPrestamo, estadoPrestamo);
		
		
		//Si la actualización en la base es correcta genero movimiento y cargo la lista actualizada
		if(estadoActualizacion == 1) {
			
			
			//GENERO MOVIMIENTO
			int EstadoMovimiento = -1;
			
			if(estadoPrestamo == 3) {
				String Dni = request.getParameter("dniCliente");
				int nCuenta = cuentaNeg.buscarNCuenta(Dni);
				float importeSolicitado = Float.parseFloat(request.getParameter("importeSolicitado"));	
				EstadoMovimiento = movimientoNeg.CrearMovimiento(1, "Alta prestamo", importeSolicitado, nCuenta, 2);
			}
			
			// SE LISTA PRESTAMOS ACTUALIZADOS
			ArrayList<Prestamo> listaPrestamos = new ArrayList<Prestamo>();
			ArrayList<EstadoPrestamo> listaEstadosPrestamo= new ArrayList<EstadoPrestamo>();
			
			
			listaPrestamos = prestamoNeg.obtenerPrestamos();
			listaEstadosPrestamo = prestamoNeg.obtenerListadeEstado();
			
			
				if(listaPrestamos != null || listaEstadosPrestamo != null || EstadoMovimiento != 0) {
					System.out.println("completa lista prestamos");
					
					request.setAttribute("listaPrestamos", listaPrestamos);	
					request.setAttribute("listaEstadosPrestamo", listaEstadosPrestamo);	
					request.setAttribute("Mensaje","Operación realizada con éxito");
					
				
					}else {
				    request.setAttribute("Mensaje","No hay prestamos solicitados ");
						  
						
					}
			}else {
				   request.setAttribute("Mensaje","Ups! ha ocurrido un error inesperado ");			
			}	
			RequestDispatcher dispatcher = request.getRequestDispatcher("/AdminPrestamo.jsp");
			dispatcher.forward(request, response);	
	
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

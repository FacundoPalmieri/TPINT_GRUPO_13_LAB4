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
		
		//Obtener próxima cuota pendiente y su importe
		
		    
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
			
			// ACTUALIZO SALDO Y GENERO MOVIMIENTO 
			int EstadoMovimiento = 0;
			int EstadoSaldoCuenta = 0;
			int EstadoCuotas = 0;
			float saldo = 0;
			Cuenta cuenta = new Cuenta();
			
			if(estadoPrestamo == 3) {
			
				int nCuenta = Integer.parseInt(request.getParameter("numeroCuenta"));
				float importeSolicitado = Float.parseFloat(request.getParameter("importeSolicitado"));	
				
				// Genero movimiento
				EstadoMovimiento = movimientoNeg.CrearMovimiento(1, "Alta prestamo", importeSolicitado, nCuenta, 2);
				
				// Actualizo saldo
				cuenta = cuentaNeg.obtenerSaldo(nCuenta);
				saldo = cuenta.getSaldo() + importeSolicitado;
				EstadoSaldoCuenta = cuentaNeg.modificarSaldo(nCuenta, saldo);
				
				//Actualizo cuotas
				
				PagosPrestamos pagosPrestamos = new PagosPrestamos();
				
			
				pagosPrestamos.setIdPrestamo(idPrestamo);
				System.out.println("ID PRESTAMO " + idPrestamo);
		
				
				// Obtener la fecha de hoy
				LocalDate fechaPago = LocalDate.now();

				// Establecer la fecha de pago en el objeto pagosPrestamos
				pagosPrestamos.setFechaPago(fechaPago);
				
				float ImporteCuota = Float.parseFloat(request.getParameter("importeCuota"));
				pagosPrestamos.setImportePago(ImporteCuota);
				
				int Cuotas = Integer.parseInt(request.getParameter("cuotas"));
				pagosPrestamos.setCuota(Cuotas);
				
				pagosPrestamos.setEstado(1);
				
				EstadoCuotas = prestamoNeg.registrarCuotas(pagosPrestamos);
		
			}
			
			// SE LISTA PRESTAMOS ACTUALIZADOS
			ArrayList<Prestamo> listaPrestamos = new ArrayList<Prestamo>();
			ArrayList<EstadoPrestamo> listaEstadosPrestamo= new ArrayList<EstadoPrestamo>();
			
			
			listaPrestamos = prestamoNeg.obtenerPrestamos();
			listaEstadosPrestamo = prestamoNeg.obtenerListadeEstado();
			
			
				if(listaPrestamos != null || listaEstadosPrestamo != null || EstadoMovimiento != 0 || EstadoSaldoCuenta !=0 || EstadoCuotas !=0) {
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
		
		// SOLICITAR PRESTAMO CLIENTE
		if(request.getParameter("btnSolicitarPrestamo")!= null) {
			
			HttpSession session = request.getSession();
			String DNI = (String) session.getAttribute("dni");
			Prestamo prestamo = new Prestamo();
			int cuenta = 0;
			boolean estadoPrestamo = false;

		
			prestamo.setFecha(request.getParameter("fecha"));
			prestamo.setImporteSolicitado(Float.parseFloat(request.getParameter("importeSolicitado")));
			prestamo.setImporteAPagar(Float.parseFloat(request.getParameter("importeTotal")));
			prestamo.setImporteCuota(Float.parseFloat(request.getParameter("importeCuotas")));
			prestamo.setCuotas(Integer.parseInt(request.getParameter("cuotas")));
			prestamo.setCuotasAbonadas(0);
			prestamo.setSaldoRestante(Float.parseFloat(request.getParameter("importeTotal")));
			
			cuenta =Integer.parseInt(request.getParameter("cuentaDestino"));
			
			
			estadoPrestamo = prestamoNeg.guardarPrestamo(prestamo, DNI, 1, cuenta);
			
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
		
		
		
		// ABONAR PRESTAMO
		if(request.getParameter("btnPagar")!= null) {
			
		  //Verificar que el saldo de la cuenta sea mayor a la cuota del prestamo
			 String cuentaYSaldo = request.getParameter("cuenta");

			 // Separar el valor capturado en número de cuenta y saldo
		     String[] partes = cuentaYSaldo.split("-");
		     
		     int nCuenta = Integer.parseInt(partes[0]);
		     System.out.println("CUENTA id " + nCuenta);
		     
		     float saldo = Float.parseFloat(partes[1]);
		     System.out.println("SALDO" + saldo);
		     
		     
		     float cuota = Float.parseFloat( request.getParameter("cuota"));
			 System.out.println("CUOTA " + cuota);
		     
		     if(saldo > cuota) {
		     int estadoModificarSaldo = 0;
		     estadoModificarSaldo= cuentaNeg.modificarSaldo(nCuenta, (cuota * -1));
		    	 
		    	 
		    	 
		     }
		    	 
		    	 
		     
			
			
				 String prestamoId = request.getParameter("prestamo");
				 System.out.println("PRESTAMO ID " + prestamoId);
			
				
			    
				
				 
				
				 
				 
				
				
				
				
				
			}
	
    }

}

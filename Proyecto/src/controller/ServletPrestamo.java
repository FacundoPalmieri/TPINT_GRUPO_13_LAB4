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
			
		// GENERO LISTAS
		ArrayList<Cuenta> listaCuentas = new ArrayList<Cuenta>();
		ArrayList<Prestamo> listaPrestamosCliente = new ArrayList<Prestamo>();
		
		// OBTENGO VALOR DE DNI DE LA SESION
		HttpSession session = request.getSession();
		String DNI = (String) session.getAttribute("dni");
		System.out.println("dni del cliente en servlet prestamos: " + DNI);
		
		// OBTENGO LISTA Y PRESTAMOS POR DNI
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
			
			// GENERO LISTAS
			ArrayList<Prestamo> listaPrestamos = new ArrayList<Prestamo>();
			ArrayList<EstadoPrestamo> listaEstadosPrestamo= new ArrayList<EstadoPrestamo>();
			
			// OBTENGO LISTAR DE PRESTAMOS Y POSIBLES ESTADOS
			listaPrestamos = prestamoNeg.obtenerPrestamos();
			listaEstadosPrestamo = prestamoNeg.obtenerListadeEstado();
			
			// SI NO SON NULOS LOS MUESTRO EN EL JSP
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
		 
		// OBTENGO ID PRESTAMO Y ESTADO DEL JSP
	     int idPrestamo =  Integer.parseInt(request.getParameter("idPrestamo"));
		 int estadoPrestamo = Integer.parseInt(request.getParameter("estadoPrestamo"));
		
		 // ACTUALIZO EL ESTADO 
		 int estadoActualizacion = prestamoNeg.actualizarEstadoPrestamo(idPrestamo, estadoPrestamo);
		
		
		//Si la actualización en la base es correcta genero movimiento y cargo la lista actualizada
		if(estadoActualizacion == 1) {
			
			 
			int EstadoMovimiento = 0;
			int EstadoSaldoCuenta = 0;
			int EstadoCuotas = 0;
			
			// SI EL PRESTAMO ES APROBADO
			if(estadoPrestamo == 3) {
				
				// OBTENGO NCUENTA E IMPORTE DEL JSP
				int nCuenta = Integer.parseInt(request.getParameter("numeroCuenta"));
				float importeSolicitado = Float.parseFloat(request.getParameter("importeSolicitado"));	
				
				// GENERO MOVIMIENTO Y MODIFICO SALDO
				EstadoMovimiento = movimientoNeg.CrearMovimiento(nCuenta, "Alta prestamo", importeSolicitado, 2);	
				EstadoSaldoCuenta = cuentaNeg.modificarSaldo(nCuenta, importeSolicitado);
				
				//REGISTRO TODAS LAS CUOTAS EN LA TABLA 
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
			
			
				if(listaPrestamos != null && listaEstadosPrestamo != null && EstadoMovimiento != 0 && EstadoSaldoCuenta !=0 && EstadoCuotas !=0) {
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
			
			// OBTENGO EL DNI DE LA SESSION
			HttpSession session = request.getSession();
			String DNI = (String) session.getAttribute("dni");
			int cuenta = 0;
			boolean estadoPrestamo = false;
			
			
			//OBTENGO EL RESTO DE LOS VALORES DEL JSP Y GUARDO PRESTAMO
			Prestamo prestamo = new Prestamo();
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
			
		   // OBTENGO CUENTA Y SALDO DEL JSP Y LO SEPARO
			 String cuentaYSaldo = request.getParameter("cuenta");

		     String[] partes = cuentaYSaldo.split("-");
		     
		     int nCuenta = Integer.parseInt(partes[0]);
		     System.out.println("CUENTA id: " + nCuenta);
		     
		     float saldo = Float.parseFloat(partes[1]);
		     System.out.println("SALDO CUENTA: " + saldo);
		     
		     // OBTENGO PRESTAMO E IMPORTE CUOTA  JSP Y LO SEPARO
			 String prestamoIdEimporteCuota = request.getParameter("prestamo");
			 
			 String[] partes2 = prestamoIdEimporteCuota.split("-");
			 
			 int idPrestamo = Integer.parseInt(partes2[0]);
			 System.out.println("PRESTAMO ID: " + idPrestamo);

			 
			 float importeCuota = Float.parseFloat(partes2[1]);
			 System.out.println("IMPORTE CUOTA: " + importeCuota);
		     
			 
			 // CAPTURO NUMERO DE CU0TA
		     int cuota = Integer.parseInt( request.getParameter("cuota"));
			 System.out.println("NUMERO CUOTA: " + cuota);
			
		     
		     if(saldo >= importeCuota) {     
			     int estadoModificarSaldo = 0;
			     int estadoMovimiento = 0;
			     int estadoActualizarCuota = 0;
			     int estadoActualizarPrestamo = 0;
			     
			     String DetalleMovimiento = "Pago prestamo - cuota " + cuota;
			     
			     estadoModificarSaldo= cuentaNeg.modificarSaldo(nCuenta, (importeCuota * -1));
			     estadoMovimiento = movimientoNeg.CrearMovimiento(nCuenta, DetalleMovimiento, (importeCuota * -1), 3);
			     estadoActualizarCuota = prestamoNeg.actualizarCuota(idPrestamo, cuota, 2);
			     estadoActualizarPrestamo = prestamoNeg.actualizarCuotaPrestamo(idPrestamo, cuota );
			     
			     if(estadoModificarSaldo != 0 && estadoMovimiento != 0 && estadoActualizarCuota != 0 && estadoActualizarPrestamo!= 0 ) {
			    	 
			    	   request.setAttribute("Mensaje","El pago ha sido realizado con éxito");
					   RequestDispatcher dispatcher = request.getRequestDispatcher("/ClientePrestamo.jsp");
			           dispatcher.forward(request, response);
			    	 
			    	 
			     }else {
			    	 
				  	   request.setAttribute("Mensaje","No se ha podido realizar el pago");
					   RequestDispatcher dispatcher = request.getRequestDispatcher("/ClientePrestamo.jsp");
			           dispatcher.forward(request, response);
				    	 
			     }
		     	    	 
		     }else {
		    	 	request.setAttribute("Mensaje","Saldo insuficiente en cuenta");
				   RequestDispatcher dispatcher = request.getRequestDispatcher("/ClientePrestamo.jsp");
		           dispatcher.forward(request, response);
		     }
		    	 
		    	 
		     
			
			
	
				
			    
				
				 
				
				 
				 
				
				
				
				
				
			}
	
    }

}

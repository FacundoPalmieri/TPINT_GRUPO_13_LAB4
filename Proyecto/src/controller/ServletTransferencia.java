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
import entidad.Persona;
import negocio.CuentaNeg;
import negocio.MovimientoNeg;
import negocio.UsuarioNeg;
import negocioimpl.CuentaNegImpl;
import negocioimpl.MovimientoNegImpl;
import negocioimpl.UsuarioNegImpl;

/**
 * Servlet implementation class ServletTransferencia
 */
@WebServlet("/ServletTransferencia")
public class ServletTransferencia extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CuentaNeg cuentaNeg;
	private MovimientoNeg movimientoNeg;
	private UsuarioNeg usuarioNeg;
	
   
	public void init() throws ServletException {
		super.init();
		cuentaNeg = new CuentaNegImpl();
		movimientoNeg = new MovimientoNegImpl();
		usuarioNeg = new UsuarioNegImpl();
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(request.getParameter("btnBuscarCBU")!= null) {
			
			// GENERO VARIABLES
			Cuenta cuenta = new Cuenta();
			Persona persona = new Persona();
			String dniClienteOrigen = null;
			ArrayList <Cuenta> listaCuentas = new ArrayList <Cuenta>();
			
			// OBTENGO CBU DEL JSP 
			String cbu = request.getParameter("cbuCliente");
			
			// OBTENGO CUENTA POR CEBU
			cuenta = cuentaNeg.obtenerCuentaporCBU(cbu);		
			
			// OBTENGO CLIENTE POR DNI CUENTA
			persona = usuarioNeg.obtenerClientePorDNI(cuenta.getClienteDni());
			
			System.out.println("DNI CUENTA " + cuenta.getClienteDni());
			System.out.println("DNI PERSONA " + persona.getDni());
		
			if(cuenta.getClienteDni() != 0 && persona.getNombre() != null) {
				
				//Obtengo el DNI del cliente que quiere hacer la transferencia por session
				HttpSession session = request.getSession();
				dniClienteOrigen = (String) session.getAttribute("dni");
				listaCuentas = cuentaNeg.obtenerCuentasPorDNI(dniClienteOrigen);
				
				//Guardo en session para usarlo en el post 
			    session.setAttribute("cuenta", cuenta);
				
				request.setAttribute("cuenta", cuenta);
				request.setAttribute("persona", persona);
				request.setAttribute("listaCuentas", listaCuentas);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/ClienteTransferencia.jsp");
				dispatcher.forward(request, response);	
	
			}else {
			request.setAttribute("Mensaje", "No existe la cuenta");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/ClienteTransferencia.jsp");
			dispatcher.forward(request, response);	
			}
		}
		
}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(request.getParameter("confirmacionTransferencia")!= null) {
		
		//Obtengo los valores de cuenta Origen 
		String cuentaYsaldo = request.getParameter("cuentaOrigen");
		String[] partes = cuentaYsaldo.split("-");
		int nCuenta = Integer.parseInt(partes[0]);
		float saldo = Float.parseFloat(partes[1]);


		//Obtengo la cuenta destino del doGET
	     HttpSession session = request.getSession();
		 Cuenta cuenta = (Cuenta) session.getAttribute("cuenta");
		 int nCuentaDestino = 0;
		
		 // Asigno el string al int para comparar
	    nCuentaDestino = cuenta.getNumeroCuenta();
		
		if(nCuenta != nCuentaDestino) {
			float monto = (Float.parseFloat(request.getParameter("monto")));
			 if(monto <= saldo) {
				 
			   if(monto > 0) {
				   
				     int estadoModificarSaldoOrigen = 0;
					 int estadoModificarSaldoDestino = 0;
					 int estadoMovimientoOrigen = 0;
					 int estadoMovimientoDestino = 0;
					 String detalleOrigen = "Transferencia realizada a cuenta N° " + nCuenta;
					 String detalleDestino = "Transferencia recibida de cuenta N° " + nCuentaDestino;
					 
					 estadoModificarSaldoOrigen = cuentaNeg.modificarSaldo(nCuenta, (monto *-1));
					 estadoModificarSaldoDestino = cuentaNeg.modificarSaldo(nCuentaDestino, monto);
					 estadoMovimientoOrigen = movimientoNeg.CrearMovimiento(nCuenta, detalleOrigen, (monto*-1), 4);
					 estadoMovimientoDestino = movimientoNeg.CrearMovimiento(nCuentaDestino, detalleDestino, monto, 4);
					 
					 
					 if(estadoModificarSaldoOrigen != 0 && estadoMovimientoOrigen != 0 && estadoMovimientoDestino != 0 && estadoModificarSaldoDestino != 0 ) {
						   request.setAttribute("Mensaje","Transferencia realizada ");
						   RequestDispatcher dispatcher = request.getRequestDispatcher("/ClienteTransferencia.jsp");
				           dispatcher.forward(request, response); 
						 
					 }else {
						   request.setAttribute("Mensaje","Ups! ha ocurrido un error inesperado, comuniquese con soporte técnico. ");
						   RequestDispatcher dispatcher = request.getRequestDispatcher("/ClienteTransferencia.jsp");
				           dispatcher.forward(request, response);
					 }
					 

				   
			   }else {
				   request.setAttribute("Mensaje","El monto debe ser positivo");
				   RequestDispatcher dispatcher = request.getRequestDispatcher("/ClienteTransferencia.jsp");
		           dispatcher.forward(request, response);		 
			 }
				
				 	
		}else {
			   request.setAttribute("Mensaje","No tiene suficiente saldo en cuenta");
			   RequestDispatcher dispatcher = request.getRequestDispatcher("/ClienteTransferencia.jsp");
	           dispatcher.forward(request, response); 
			   
		   }
		
	
			
			
			
		}else {
			   request.setAttribute("Mensaje","No puede realizar una transferencia a su misma cuenta");
			   RequestDispatcher dispatcher = request.getRequestDispatcher("/ClienteTransferencia.jsp");
	           dispatcher.forward(request, response);
			
		}
		
	  }
	}
}



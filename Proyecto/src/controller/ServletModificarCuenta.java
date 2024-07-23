package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidad.Cuenta;
import entidad.Persona;
import negocio.CuentaNeg;
import negocio.UsuarioNeg;
import negocioimpl.CuentaNegImpl;
import negocioimpl.UsuarioNegImpl;


@WebServlet("/ServletModificarCuenta")
public class ServletModificarCuenta extends HttpServlet {
	private static final long serialVersionUID = 1L;
	CuentaNeg cuentaNeg = new CuentaNegImpl();
       
 
    public ServletModificarCuenta() {
        super();

    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		 if (request.getParameter("btnBuscarC") != null) {
			 System.out.println("Botón actualizar presionado.");
	        	String DNI = null;	        	
	        	ArrayList <Cuenta> listaCuentas = new   ArrayList <Cuenta>();
	        	
	        	DNI = (request.getParameter("dniCliente"));
	        	listaCuentas = cuentaNeg.obtenerCuentasPorDNI(DNI);
	        	
	  		  if(listaCuentas != null && !listaCuentas.isEmpty()) {				  
				  
				  request.setAttribute("listaCuentas", listaCuentas);
	  		  }
	  		 else {
		  			request.setAttribute("Mensaje", "El DNI ingresado no posee cuentas");
		  		  } 
				  RequestDispatcher dispatcher = request.getRequestDispatcher("/ModificarCuenta.jsp");
		          dispatcher.forward(request, response); 
				    
				  
			  }
	        	
	            
	        
		 if (request.getParameter("btnActualizarCuenta") != null) {
			 
			 System.out.println("Botón actualizar cuenta presionado.");

		        String dniCliente = request.getParameter("dniCliente");
		        int numeroCuenta = Integer.parseInt(request.getParameter("numeroCuenta"));
		        int nuevoTipoCuenta = Integer.parseInt(request.getParameter("tipoCuenta_" + numeroCuenta));

		        System.out.println("DNI del cliente: " + dniCliente);
		        System.out.println("Número de cuenta: " + numeroCuenta + " - Nuevo tipo de cuenta: " + nuevoTipoCuenta);

		        boolean resultado = cuentaNeg.modificarCuenta(numeroCuenta, dniCliente, nuevoTipoCuenta);
		        System.out.println("Resultado de la actualización: " + resultado);

		        if (resultado) {
		            request.setAttribute("Mensaje", "Cuenta actualizada correctamente.");
		        } else {
		            request.setAttribute("Mensaje", "Error al actualizar la cuenta.");
		        }

		        // Volver a cargar la lista de cuentas actualizada
		        ArrayList<Cuenta> listaCuentas = cuentaNeg.obtenerCuentasPorDNI(dniCliente);
		        request.setAttribute("listaCuentas", listaCuentas);

		        RequestDispatcher dispatcher = request.getRequestDispatcher("/ModificarCuenta.jsp");
		        dispatcher.forward(request, response);
		    } 
		 }
		 
	}



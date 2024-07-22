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
		HttpSession session = request.getSession();

		 if (request.getParameter("btnBuscarC") != null) {
			 System.out.println("Botón actualizar presionado.");
	        	String DNI = null;	        	
	        	ArrayList <Cuenta> listaCuentas = new   ArrayList <Cuenta>();
	        	
	        	DNI = (request.getParameter("dniCliente"));
	        	listaCuentas = cuentaNeg.obtenerCuentasPorDNI(DNI);
	        	
	  		  if(listaCuentas != null) {				  
	  			session.setAttribute("listaCuentas", listaCuentas);
				  request.setAttribute("listaCuentas", listaCuentas);
				 
				  RequestDispatcher dispatcher = request.getRequestDispatcher("/ModificarCuenta.jsp");
		          dispatcher.forward(request, response); 
				    
				  
			  }
	        	
	            
	        }
		 if (request.getParameter("btnActualizarC") != null) {
			 
			 System.out.println("Botón actualizar presionado.");

		        String dniCliente = request.getParameter("dniCliente");
		        System.out.println("DNI del cliente: " + dniCliente);

		        ArrayList<Cuenta> listaCuentas = (ArrayList<Cuenta>) session.getAttribute("listaCuentas"); // Obtener de la sesión
		        System.out.println("Lista de cuentas obtenida de la sesión: " + listaCuentas);

		        boolean actualizado = true;

		        if (listaCuentas != null) {
		            for (Cuenta cuenta : listaCuentas) {
		                int nuevoTipoCuenta = Integer.parseInt(request.getParameter("tipoCuenta_" + cuenta.getNumeroCuenta()));
		                System.out.println("Número de cuenta: " + cuenta.getNumeroCuenta() + " - Nuevo tipo de cuenta: " + nuevoTipoCuenta);

		                boolean resultado = cuentaNeg.modificarCuenta(cuenta.getNumeroCuenta(), dniCliente, nuevoTipoCuenta);
		                System.out.println("Resultado de la actualización: " + resultado);

		                if (!resultado) {
		                    actualizado = false;
		                    break;
		                }
		            }
		        } else {
		            System.out.println("No se encontraron cuentas para actualizar.");
		            actualizado = false;
		        }

		        if (actualizado) {
		            request.setAttribute("mensaje", "Cuentas actualizadas correctamente.");
		        } else {
		            request.setAttribute("mensaje", "Error al actualizar las cuentas.");
		        }

		        // Volver a cargar la lista de cuentas actualizada
		        listaCuentas = cuentaNeg.obtenerCuentasPorDNI(dniCliente);
		        session.setAttribute("listaCuentas", listaCuentas); // Actualizar la sesión con la nueva lista
		        request.setAttribute("listaCuentas", listaCuentas);

		        RequestDispatcher dispatcher = request.getRequestDispatcher("/ModificarCuenta.jsp");
		        dispatcher.forward(request, response);
		    } 
		 }
		 
	}



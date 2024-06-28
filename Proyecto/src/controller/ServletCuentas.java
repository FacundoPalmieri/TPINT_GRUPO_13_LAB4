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
import entidad.Usuario;
import negocio.CuentaNeg;
import negocio.UsuarioNeg;
import negocioimpl.CuentaNegImpl;
import negocioimpl.UsuarioNegImpl;

/**
 * Servlet implementation class ServletCuentas
 */
@WebServlet("/ServletCuentas")
public class ServletCuentas extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	CuentaNeg cuentaNeg = new CuentaNegImpl();
	UsuarioNeg usuarioNeg = new UsuarioNegImpl();
	
       
   
    public ServletCuentas() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 HttpSession session = request.getSession();
		    String dniUsuario = (String) session.getAttribute("dniCliente");

		    if (dniUsuario != null) {
		        ArrayList<Cuenta> cuentas = cuentaNeg.obtenerCuentasPorDNI(dniUsuario);
		        request.setAttribute("obtenerCuentasPorDNI", cuentas);
		        RequestDispatcher dispatcher = request.getRequestDispatcher("/CuentasCliente.jsp");
		        dispatcher.forward(request, response);
		    } else {
		        response.sendRedirect("Login.jsp");
		    }
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// BUSCAR CLIENTE
		
		if (request.getParameter("btnBuscarClienteCrearCuenta") != null) {
			
        	Usuario usuario = new Usuario();
        	String DNI = new String();
        	
        	DNI = request.getParameter("dniCliente"); 
        	usuario = usuarioNeg.obtenerClientePorDNI(DNI);
        	
        	request.setAttribute("usuario", usuario.getUsuario());
            request.setAttribute("nombre", usuario.getNombre());
            request.setAttribute("apellido", usuario.getApellido());
            
            System.out.println("Usuario btnBuscarEliminar: " + usuario.getUsuario());
            System.out.println("Nombre btnBuscarEliminar: " + usuario.getNombre());
            System.out.println("Apellido : " + usuario.getApellido());
           
            RequestDispatcher dispatcher = request.getRequestDispatcher("/CrearCuenta.jsp");
            dispatcher.forward(request, response); 
            
        } 
		
		
		//VALIDAR CLIENTE Y CREAR CUENTA
		if(request.getParameter("btnCrearCuenta") != null){
			String DNI = new String();
	
			DNI = request.getParameter("dniCliente");
	
			int CantidadCuenta = cuentaNeg.ValidarCantidad(DNI);
			
			
			if (CantidadCuenta < 3) {
				int tipoCuenta = 0;
				int estado = 0;
				
				String tipoCuentaStr = request.getParameter("tipoCuenta");
				tipoCuenta = Integer.parseInt(tipoCuentaStr);
				
				estado = cuentaNeg.CrearCuenta(DNI, tipoCuenta);
				
				if(estado == 1) {
					 request.setAttribute("Mensaje", "La cuenta ha sido creada");
				     RequestDispatcher dispatcher = request.getRequestDispatcher("/CrearCuenta.jsp");
					 dispatcher.forward(request, response);
					
				}else {
					 request.setAttribute("Mensaje", "La cuenta NO puedo ser creada");
				     RequestDispatcher dispatcher = request.getRequestDispatcher("/CrearCuenta.jsp");
					 dispatcher.forward(request, response);
				}
			
	   
			} else { // No permitir agregar más cuentas
				request.setAttribute("Mensaje", "El cliente ha alcanzado el limite de cuentas.");
			    RequestDispatcher dispatcher = request.getRequestDispatcher("/CrearCuenta.jsp");
			    dispatcher.forward(request, response);
			}
			
		}
		
	}

}

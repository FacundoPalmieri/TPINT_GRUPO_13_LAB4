package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
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
		
		
		//CREAR CUENTA
		if(request.getParameter("btnCrearCuenta") != null){
			String DNI = new String();
		
			
			DNI = request.getParameter("dniCliente");
			
			int CantidadCuenta = cuentaNeg.ValidarCantidad(DNI);
			if (CantidadCuenta < 3) {
				
				
			    // No permitir agregar m�s cuentas
			} else {
				request.setAttribute("Mensaje", "El cliente ha alcanzado el limite de cuentas.");
			    RequestDispatcher dispatcher = request.getRequestDispatcher("/CrearCuenta.jsp");
			    dispatcher.forward(request, response);
			}
			
		}
		
	}

}
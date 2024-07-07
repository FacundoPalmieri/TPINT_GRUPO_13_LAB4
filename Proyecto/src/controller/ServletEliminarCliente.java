package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidad.Persona;
import entidad.Usuario;
import negocio.UsuarioNeg;
import negocioimpl.UsuarioNegImpl;

/**
 * Servlet implementation class ServletEliminarCliente
 */
@WebServlet("/ServletEliminarCliente")
public class ServletEliminarCliente extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UsuarioNeg usuarioNeg = new UsuarioNegImpl();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletEliminarCliente() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        
		if (request.getParameter("btnBuscarEliminar") != null) {
		    String DNI = new String();
		    Usuario usuario; 
		    Persona persona;
		        	
		    DNI = (request.getParameter("dniCliente"));
		    usuario = usuarioNeg.obtenerUsuarioPorDNI(DNI);
		    persona = usuarioNeg.ObtenerCliente(usuario.getUsuario());
		    
		    if(usuario.getPersona_dni() != null && persona.getNombre() != null && persona.getApellido() != null) {
		       request.setAttribute("Usuario", usuario.getUsuario());
			   request.setAttribute("Nombre", persona.getNombre());
			   request.setAttribute("Apellido", persona.getApellido());
			   
			           
			    RequestDispatcher dispatcher = request.getRequestDispatcher("/EliminarCliente.jsp");
			    dispatcher.forward(request, response); 	    	
		    }else {
	 	        request.setAttribute("Mensaje", "Cliente no encontrado");
	 	        System.out.println("INGRESE AL ELSE PORQUE ES NULO ");
		        RequestDispatcher dispatcher = request.getRequestDispatcher("/EliminarCliente.jsp");
			    dispatcher.forward(request, response); 	    	
		        
		    	
		    }
		    
		
		 } 
		
		
		if (request.getParameter("btnConfirmacion") != null) {
			 Usuario usuarioEditado = new Usuario();
		     usuarioEditado.setPersona_dni(request.getParameter("dniCliente"));
		     usuarioEditado.setHabilitado(0);
		          
		     boolean filas = usuarioNeg.eliminarUsuario(usuarioEditado);
		                
		     if(filas == true) {
		    	 request.setAttribute("filas", filas);
		         RequestDispatcher dispatcher = request.getRequestDispatcher("/EliminarCliente.jsp");
		         dispatcher.forward(request, response); 
		      }
		                
		 }
        
	}

}

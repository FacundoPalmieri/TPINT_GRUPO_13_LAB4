package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entidad.Usuario;
import entidad.Persona;
import entidad.Direccion;
import negocio.UsuarioNeg;
import negocioimpl.UsuarioNegImpl;

/**
 * Servlet implementation class EditarCliente
 */
@WebServlet("/EditarCliente")
public class ServletEditarCliente extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UsuarioNeg usuarioNeg = new UsuarioNegImpl();
	
       
   
    public ServletEditarCliente() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false); // No crear nueva sesi�n si no existe
        if (session != null) {
            Usuario usuario = (Usuario) session.getAttribute("usuarioEnSesion");
            if (usuario != null) {
                String nombreUsuario = usuario.getUsuario(); // Obtener el nombre de usuario desde el objeto Usuario
                System.out.println("Nombre Usuario Session: " + nombreUsuario);

                if (request.getParameter("btnEditar") != null) {
                    // Llama al m�todo para obtener los datos completos del usuario
                    Usuario usuarioCompleto = usuarioNeg.obtenerUsuario(nombreUsuario); 

                    // Establece los datos del usuario en el request para que el JSP pueda mostrarlos
                    
                    request.setAttribute("usuario", usuarioCompleto.getUsuario());
                    request.setAttribute("contrasena", usuarioCompleto.getPass());

                    RequestDispatcher dispatcher = request.getRequestDispatcher("/DatosCliente.jsp");
               
                    dispatcher.forward(request, response);
              
                } 
            }
        }              
        
	}

	
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
   
    	
    	// MODIFICAR USUARIO.JSP
        if (request.getParameter("btnBuscar") != null) {
        	
        	String DNI = new String();
        	Usuario usuario = new Usuario();
        	
        	DNI = (request.getParameter("dniCliente"));
        	usuario = usuarioNeg.obtenerClientePorDNI(DNI);
        	request.setAttribute("usuario", usuario.getUsuario());
            request.setAttribute("contrasena", usuario.getPass());

            RequestDispatcher dispatcher = request.getRequestDispatcher("/ModificarUsuario.jsp");
            dispatcher.forward(request, response); 
            
        } 
            else if (request.getParameter("btnActualizar") != null) {
                Usuario usuarioEditado = new Usuario();
                
                usuarioEditado.setPersona_dni(request.getParameter("dniCliente"));
                usuarioEditado.setPass(request.getParameter("contrasena"));
                
                boolean filas = usuarioNeg.editarContrasena(usuarioEditado);
                
               if(filas == true) {
            	   request.setAttribute("filas", filas);
            	   RequestDispatcher dispatcher = request.getRequestDispatcher("/ModificarUsuario.jsp");
                   dispatcher.forward(request, response); 
            	   
            	   
               }
                
            }
       
        
        // Eliminar USUARIO.JSP
        
		if (request.getParameter("btnBuscarEliminar") != null) {
		        	String DNI = new String();
		        	Usuario usuario = new Usuario();
		        	
		        	DNI = (request.getParameter("dniCliente"));
		        	usuario = usuarioNeg.obtenerClientePorDNI(DNI);
		        	
		        	request.setAttribute("usuario", usuario.getUsuario());
		            
		            System.out.println("Usuario btnBuscarEliminar: " + usuario.getUsuario());
		           
		            RequestDispatcher dispatcher = request.getRequestDispatcher("/EliminarUsuario.jsp");
		            dispatcher.forward(request, response); 
		            
		        } 
		 else if (request.getParameter("btnEliminar") != null) {
			 Usuario usuarioEditado = new Usuario();
		     usuarioEditado.setPersona_dni(request.getParameter("dniCliente"));
		     usuarioEditado.setHabilitado(0);
		          
		     boolean filas = usuarioNeg.eliminarUsuario(usuarioEditado);
		                
		     if(filas == true) {
		    	 request.setAttribute("filas", filas);
		         RequestDispatcher dispatcher = request.getRequestDispatcher("/EliminarUsuario.jsp");
		         dispatcher.forward(request, response); 
		      }
		                
		 }
        
    }

}
	



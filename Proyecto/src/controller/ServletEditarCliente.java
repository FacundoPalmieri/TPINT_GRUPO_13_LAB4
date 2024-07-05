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
		
		//VISUALIZAR DATOS SIENDO CLIENTE
		
        HttpSession session = request.getSession(false); // No crear nueva sesi�n si no existe
        if (session != null) {
            Usuario usuario = (Usuario) session.getAttribute("usuario");
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
   
    	
    	// MODIFICAR CLIENTE.JSP
        if (request.getParameter("btnBuscar") != null) {
        	
        	String DNI = new String();
        	Usuario usuario = new Usuario();
        	
        	DNI = (request.getParameter("dniCliente"));
        	usuario = usuarioNeg.obtenerUsuarioPorDNI(DNI);
        	request.setAttribute("usuario", usuario.getUsuario());
         

            RequestDispatcher dispatcher = request.getRequestDispatcher("/ModificarCliente.jsp");
            dispatcher.forward(request, response); 
            
        } 
        
        if (request.getParameter("btnActualizar") != null) {
            	
               //Validaci�n de contrase�a
    			String Contrasenia1 = request.getParameter("contrasena");
    			String Contrasenia2 = request.getParameter("contrasena2");
    			System.out.println("PASS1 : "+ Contrasenia1);
    			System.out.println("PASS2 : "+ Contrasenia2);
    			
    			if (Contrasenia1.equals(Contrasenia2)) {
            	
    				Usuario usuarioEditado = new Usuario();
                
	                usuarioEditado.setUsuario(request.getParameter("usuario"));
	                usuarioEditado.setPass(request.getParameter("contrasena"));
	                
	                boolean filas = usuarioNeg.editarContrasena(usuarioEditado);
	                
	        	     if(filas == true) {
	        	    	 request.setAttribute("filas", filas);
		            	 RequestDispatcher dispatcher = request.getRequestDispatcher("/ModificarCliente.jsp");
		                 dispatcher.forward(request, response);    
	        	     }        
    		   }		
         } 
    }
}
	



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
import entidad.Provincia;
import entidad.Direccion;
import entidad.Localidad;
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
		
        HttpSession session = request.getSession(false); 
        if (session != null) {
        	String nombreUsuario = (String)  session.getAttribute("usuario");
        	int IdDireccion = (int) session.getAttribute("Direccion_id");
            if (nombreUsuario != null) {
                if (request.getParameter("Param") != null) {
    
                	Persona persona = new Persona();
                	Direccion direccion = new Direccion();
                	Provincia provincia = new Provincia();
                	Localidad localidad = new Localidad();
                	
                	
		            persona = usuarioNeg.ObtenerCliente(nombreUsuario);
		            direccion = usuarioNeg.ObtenerDireccionCliente(IdDireccion);
		            provincia = usuarioNeg.ObtenerProvinciaCliente(1);
		            localidad = usuarioNeg.ObtenerLocalidadCliente(direccion.getLocalidad_id());
		            
		            System.out.println("DNI" + persona.getDni());
	
		            request.setAttribute("persona", persona);
		            request.setAttribute("direccion",direccion);
		            request.setAttribute("provincia",provincia);
		            request.setAttribute("localidad",localidad);
		            
                    RequestDispatcher dispatcher = request.getRequestDispatcher("/DatosCliente.jsp");              
                    dispatcher.forward(request, response);
              
                } 
            }
        }              
        
	}

	
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	//Si este if es distinto de null, el servlet fue llamado desde ListarClientes.jsp
    	if(request.getParameter("usuario")!=null) {
        	Persona p = new Persona();
        	String usuario = (String)request.getParameter("usuario");
        	p = usuarioNeg.ObtenerPersonaCompleta(usuario);
        	request.setAttribute("persona", p);
            request.setAttribute("direccion",p.getDireccion());
            request.setAttribute("provincia",p.getDireccion().getLocalidad().getProvincia());
            request.setAttribute("localidad",p.getDireccion().getLocalidad());
        	RequestDispatcher dispatcher = request.getRequestDispatcher("/DatosCliente.jsp");
            dispatcher.forward(request, response); 
        }
    	
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
            	
               //Validación de contraseña
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
	



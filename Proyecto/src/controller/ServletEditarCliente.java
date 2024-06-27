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
                    Usuario usuarioCompleto = usuarioNeg.obtenerCliente(nombreUsuario); 

                    // Establece los datos del usuario en el request para que el JSP pueda mostrarlos
                    request.setAttribute("dni", usuarioCompleto.getDni());
                    request.setAttribute("cuil", usuarioCompleto.getCuil());
                    request.setAttribute("nombre", usuarioCompleto.getNombre());
                    request.setAttribute("apellido", usuarioCompleto.getApellido());
                    request.setAttribute("sexo", usuarioCompleto.getSexo());  
                    request.setAttribute("fechaNacimiento", usuarioCompleto.getFechaNacimiento());
                    request.setAttribute("nacionalidad", usuarioCompleto.getNacionalidad());
                    request.setAttribute("localidad", usuarioCompleto.getLocalidad());
                    request.setAttribute("provincia", usuarioCompleto.getProvincia());
                    request.setAttribute("direccion", usuarioCompleto.getDireccion());
                    request.setAttribute("celular", usuarioCompleto.getCelular());
                    request.setAttribute("telefonos", usuarioCompleto.getTelefono());
                    request.setAttribute("correoElectronico", usuarioCompleto.getEmail());
                    request.setAttribute("usuario", usuarioCompleto.getUsuario());
                    request.setAttribute("contrasena", usuarioCompleto.getContrasena());

                    RequestDispatcher dispatcher = request.getRequestDispatcher("/DatosCliente.jsp");
               
                    dispatcher.forward(request, response);
              
                } 
            }
        }              
        
	}

	
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
   
        if (request.getParameter("btnBuscar") != null) {
        	
        	String DNI = new String();
        	Usuario usuario = new Usuario();
        	
        	DNI = (request.getParameter("dniCliente"));
        	usuario = usuarioNeg.obtenerClientePorDNI(DNI);
        	request.setAttribute("usuario", usuario.getUsuario());
            request.setAttribute("contrasena", usuario.getContrasena());

            RequestDispatcher dispatcher = request.getRequestDispatcher("/ModificarUsuario.jsp");
            dispatcher.forward(request, response); 
            
        } 
            else if (request.getParameter("btnActualizar") != null) {
                Usuario usuarioEditado = new Usuario();
                
                usuarioEditado.setDni(request.getParameter("dniCliente"));
                usuarioEditado.setContrasena(request.getParameter("contrasena"));
                
                boolean filas = usuarioNeg.editarContrasena(usuarioEditado);
                
               if(filas == true) {
            	   request.setAttribute("filas", filas);
            	   RequestDispatcher dispatcher = request.getRequestDispatcher("/ModificarUsuario.jsp");
                   dispatcher.forward(request, response); 
            	   
            	   
               }
                
            }
        
        
        
        
        
        
		if (request.getParameter("btnBuscarEliminar") != null) {
		        	String DNI = new String();
		        	Usuario usuario = new Usuario();
		        	
		        	DNI = (request.getParameter("dniCliente"));
		        	usuario = usuarioNeg.obtenerClientePorDNI(DNI);
		        	
		        	request.setAttribute("usuario", usuario.getUsuario());
		            request.setAttribute("nombre", usuario.getNombre());
		            request.setAttribute("apellido", usuario.getApellido());
		            
		            System.out.println("Usuario btnBuscarEliminar: " + usuario.getUsuario());
		            System.out.println("Nombre btnBuscarEliminar: " + usuario.getNombre());
		            System.out.println("Apellido : " + usuario.getApellido());
		           
		            RequestDispatcher dispatcher = request.getRequestDispatcher("/EliminarUsuario.jsp");
		            dispatcher.forward(request, response); 
		            
		        } 
		 else if (request.getParameter("btnEliminar") != null) {
			 Usuario usuarioEditado = new Usuario();
		     usuarioEditado.setDni(request.getParameter("dniCliente"));
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
	



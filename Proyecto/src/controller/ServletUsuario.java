
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

import negocio.UsuarioNeg;
import negocioimpl.UsuarioNegImpl;
import entidad.Persona;
import entidad.Usuario;

@WebServlet("/ServletUsuario")
public class ServletUsuario extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private UsuarioNeg usuarioNegocio;

    
    public void init() throws ServletException {
        super.init();
        usuarioNegocio = new UsuarioNegImpl();
        
    }

    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String nombreUsuario = request.getParameter("txtUsuario");
        String contrasenia = request.getParameter("txtContrasenia");
        

        int esCliente = 0;
        esCliente = usuarioNegocio.validarLogin(nombreUsuario, contrasenia);
        
        if(esCliente == 2) {
            HttpSession session = request.getSession();
            Usuario u = new Usuario();
            Persona p = new Persona();
            u = usuarioNegocio.obtenerUsuario(nombreUsuario);
            p = usuarioNegocio.ObtenerCliente(nombreUsuario);
            
            session.setAttribute("usuario", u.getUsuario());
            session.setAttribute("tipoUsuario", u.getTipoUsuarioId());
            session.setAttribute("Nombre",p.getNombre());
            session.setAttribute("Apellido",p.getApellido());
            session.setAttribute("dni",p.getDni());
            session.setAttribute("cuil",p.getCuil());
            session.setAttribute("Celular",p.getCelular());
            session.setAttribute("Telefono",p.getTelefono());
            session.setAttribute("Direccion_id",p.getDireccion_id());
            session.setAttribute("Nacionalidad",p.getNacionalidad());
            
            
            
            request.setAttribute("validacionCliente", esCliente);
            
            RequestDispatcher dispatcher = request.getRequestDispatcher("/InicioCliente.jsp");
            dispatcher.forward(request, response); 
        }
        else if(esCliente==1) {
        	HttpSession session = request.getSession();
        	session.setAttribute("tipoUsuario",1);
        	RequestDispatcher dispatcher = request.getRequestDispatcher("/UsuarioAdministrador.jsp");
        	dispatcher.forward(request, response);	
        }
        else {
	        request.setAttribute("validacionCliente", esCliente);
	    	RequestDispatcher dispatcher = request.getRequestDispatcher("/Login.jsp");
			dispatcher.forward(request, response);	
        	
        	
        }

      
    }

    
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    
	    	  if (request.getParameter("btnListarCliente") != null) {
	    		UsuarioNeg un = new UsuarioNegImpl();
	    		ArrayList<Usuario> listaUsuarios = new ArrayList<Usuario>();
	    		listaUsuarios = un.listaUsuarios();
	    		request.setAttribute("listaUsuarios", listaUsuarios);
	    		RequestDispatcher dispatcher = request.getRequestDispatcher("/ListarClientes.jsp");
				dispatcher.forward(request, response);	
	    	  }
    
    
    
    		if (request.getParameter("btnModificarUsuario") != null) {
    			  response.sendRedirect("ModificarUsuario.jsp");
    		}
    		
    		if (request.getParameter("btnEliminarUsuario") != null) {
  			  response.sendRedirect("EliminarUsuario.jsp");
    		}
    		
    }
}

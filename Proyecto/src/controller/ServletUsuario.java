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
        
        /*boolean estado = true;
        estado = usuarioNegocio.validarLogin(nombreUsuario, contrasenia);
        
        if(estado == true) {
        	
        	 System.out.println("Estado SERVLET : "+ estado);
			 
				request.setAttribute("validacionCliente", estado);
		    	RequestDispatcher dispatcher = request.getRequestDispatcher("/InicioCliente.jsp");
				dispatcher.forward(request, response);	
        	
        }*/
        int estado = 0;
        estado = usuarioNegocio.validarLogin(nombreUsuario, contrasenia);
        
        if(estado == 2) {
        	System.out.println("Estado SERVLET : "+ estado);
			request.setAttribute("validacionCliente", estado);
		    RequestDispatcher dispatcher = request.getRequestDispatcher("/InicioCliente.jsp");
			dispatcher.forward(request, response);	
        }
        else if(estado==1) {
        	HttpSession session = request.getSession();
        	session.setAttribute("tipoUsuario",1);
        	RequestDispatcher dispatcher = request.getRequestDispatcher("/UsuarioAdministrador.jsp");
        	dispatcher.forward(request, response);	
        }
        else {
	        request.setAttribute("validacionCliente", estado);
	    	RequestDispatcher dispatcher = request.getRequestDispatcher("/Login.jsp");
			dispatcher.forward(request, response);	
        	
        	
        }
     // Supongamos que tienes un objeto Usuario y has validado las credenciales del usuario
        Usuario usuario = new Usuario(); // Objeto Usuario con la información del usuario
        usuario.setUsuario(request.getParameter("txtUsuario"));
        usuario.setContrasena(request.getParameter("txtContrasenia"));

        {
        // Obtener la sesión y almacenar el objeto Usuario en ella
        HttpSession session = request.getSession();
        session.setAttribute("usuarioEnSesion", usuario);
       

        // Establece los datos del usuario en el request para que el JSP pueda mostrarlos
      
        //     session.setAttribute("nombre", usuario.getNombre());
       // 	request.setAttribute("nombre", "txtUsuario");
  //      request.getSession().setAttribute("nombre", usuario.getUsuario());
  //      request.getSession().setAttribute("nombre", "txtUsuario");
        request.getSession().setAttribute("nombre", nombreUsuario);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/Encabezado.jsp");
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

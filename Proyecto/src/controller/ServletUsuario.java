package controller;

import java.io.IOException;

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
        boolean estado = true;
        estado = usuarioNegocio.validarLogin(nombreUsuario, contrasenia);
        
        if(estado == true) {
        	
        	 System.out.println("Estado SERVLET : "+ estado);
			 
				request.setAttribute("validacionCliente", estado);
		    	RequestDispatcher dispatcher = request.getRequestDispatcher("/InicioCliente.jsp");
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


        // Obtener la sesión y almacenar el objeto Usuario en ella
        HttpSession session = request.getSession();
        session.setAttribute("usuarioEnSesion", usuario);

      
    }

    
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Por seguridad, si alguien intenta acceder por GET a este servlet
        response.sendRedirect("Login.jsp");
    }
}
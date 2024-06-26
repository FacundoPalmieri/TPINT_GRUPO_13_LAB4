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
 * Servlet implementation class AltaCliente
 */
@WebServlet("/ServletCerrarSesion")
public class ServletCerrarSesion extends HttpServlet {
	private static final long serialVersionUID = 1L;

    
    public ServletCerrarSesion() {
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub	
		
		
		   HttpSession session = request.getSession(false);
	        if (session != null) {
	            session.invalidate();
	        }
	        response.sendRedirect("Login.jsp");
	    }
	}


		
	

}

package controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidad.Usuario;
import negocio.UsuarioNeg;
import negocioimpl.UsuarioNegImpl;

/**
 * Servlet implementation class AltaCliente
 */
@WebServlet("/AltaCliente")
public class ServletAltaCliente extends HttpServlet {
	private static final long serialVersionUID = 1L;

	UsuarioNeg usuarioNeg = new UsuarioNegImpl();
	
    
    public ServletAltaCliente() {
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("btnAceptar")!=null)
	    {
			Usuario usuario = new Usuario();
			
			usuario.setDni(request.getParameter("dni"));
			usuario.setCuil(request.getParameter("cuil"));
			usuario.setNombre(request.getParameter("nombre"));
			usuario.setApellido(request.getParameter("apellido"));
			usuario.setSexo(request.getParameter("sexo"));
			usuario.setCelular(request.getParameter("celular"));
			usuario.setTelefono(request.getParameter("telefonos"));
			usuario.setFechaNacimiento(request.getParameter("fechaNacimiento"));
			usuario.setNacionalidad(request.getParameter("nacionalidad"));
			usuario.setLocalidad(request.getParameter("localidad"));
			usuario.setProvincia(request.getParameter("provincia"));
			usuario.setDireccion(request.getParameter("direccion"));
			usuario.setEmail(request.getParameter("correoElectronico"));
			usuario.setUsuario(request.getParameter("usuario"));
			usuario.setContrasena(request.getParameter("contrasena"));
			usuario.setTipoUsuarioId(0);
			boolean estado = true;
			boolean validacion = true;
			
			validacion = usuarioNeg.validarUsuario(usuario.getDni(), usuario.getUsuario());
			if (validacion == false){
				 System.out.println("Estado de validacion : "+ validacion);
				 
				request.setAttribute("validacionCliente", validacion);
		    	RequestDispatcher dispatcher = request.getRequestDispatcher("/AgregarCliente.jsp");
				dispatcher.forward(request, response);	
			}
			else {
				estado = usuarioNeg.agregarCliente(usuario);
		    	System.out.println("Estado inserción: "+estado);
		        
		    	request.setAttribute("validacionCliente", validacion);
		        request.setAttribute("estadoCliente", estado);
		    	RequestDispatcher dispatcher = request.getRequestDispatcher("/AgregarCliente.jsp");
				dispatcher.forward(request, response);	
			}
	        
	    }
		
	}

}

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
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    if (request.getParameter("btnEditar") != null) {
	        // Obtener la sesión actual
	        HttpSession session = request.getSession(false); // No crear nueva sesión si no existe
	        if (session != null) {
	            Usuario usuario = (Usuario) session.getAttribute("usuarioEnSesion");
	            if (usuario != null) {
	                String nombreUsuario = usuario.getUsuario(); // Obtener el nombre de usuario desde el objeto Usuario
	                System.out.println("Nombre Usuario Session: " + nombreUsuario);

	                // Llama al método para obtener los datos completos del usuario
	                Usuario usuarioCompleto = usuarioNeg.obtenerCliente(nombreUsuario); // Usa nombreUsuario en vez de "once"

	                // Establece los datos del usuario en el request para que el JSP pueda acceder a ellos
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
	               

	            
			    	RequestDispatcher dispatcher = request.getRequestDispatcher("/EditarCliente.jsp");
					dispatcher.forward(request, response);	
	            } else {
	                response.sendRedirect("Login.jsp");
	            }
	        } else {
	            response.sendRedirect("Login.jsp");
	        }
	    }
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
			estado = usuarioNeg.editarUsuario(usuario);	
		    request.setAttribute("estadoCliente", estado);
		    RequestDispatcher dispatcher = request.getRequestDispatcher("/EditarCliente.jsp");
			dispatcher.forward(request, response);	
	    }		
	}
}
	



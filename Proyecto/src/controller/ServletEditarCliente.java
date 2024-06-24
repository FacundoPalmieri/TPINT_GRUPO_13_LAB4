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

                    RequestDispatcher dispatcher = request.getRequestDispatcher("/EditarCliente.jsp");
                    dispatcher.forward(request, response); 
                } 
                else if (request.getParameter("btnAceptar") != null) {
                    Usuario usuarioEditado = new Usuario();
                    
                    usuarioEditado.setDni(request.getParameter("dni"));
                    usuarioEditado.setCuil(request.getParameter("cuil"));
                    usuarioEditado.setNombre(request.getParameter("nombre"));
                    usuarioEditado.setApellido(request.getParameter("apellido"));
                    usuarioEditado.setSexo(request.getParameter("sexo"));
                    usuarioEditado.setCelular(request.getParameter("celular"));
                    usuarioEditado.setTelefono(request.getParameter("telefonos"));
                    usuarioEditado.setFechaNacimiento(request.getParameter("fechaNacimiento"));
                    usuarioEditado.setNacionalidad(request.getParameter("nacionalidad"));
                    usuarioEditado.setLocalidad(request.getParameter("localidad"));
                    usuarioEditado.setProvincia(request.getParameter("provincia"));
                    usuarioEditado.setDireccion(request.getParameter("direccion"));
                    usuarioEditado.setEmail(request.getParameter("correoElectronico"));
                    usuarioEditado.setUsuario(request.getParameter("usuario"));
                    usuarioEditado.setContrasena(request.getParameter("contrasena"));
                    usuarioEditado.setTipoUsuarioId(0);
                    
                    boolean estado = usuarioNeg.editarUsuario(usuarioEditado);
                    
                    // traigo el usuario actualizado
                    Usuario usuarioActualizado = usuarioNeg.obtenerCliente(nombreUsuario);
                    
                    // actualizo los campos del jsp
                    request.setAttribute("dni", usuarioActualizado.getDni());
                    request.setAttribute("cuil", usuarioActualizado.getCuil());
                    request.setAttribute("nombre", usuarioActualizado.getNombre());
                    request.setAttribute("apellido", usuarioActualizado.getApellido());
                    request.setAttribute("sexo", usuarioActualizado.getSexo());
                    request.setAttribute("fechaNacimiento", usuarioActualizado.getFechaNacimiento());
                    request.setAttribute("nacionalidad", usuarioActualizado.getNacionalidad());
                    request.setAttribute("localidad", usuarioActualizado.getLocalidad());
                    request.setAttribute("provincia", usuarioActualizado.getProvincia());
                    request.setAttribute("direccion", usuarioActualizado.getDireccion());
                    request.setAttribute("celular", usuarioActualizado.getCelular());
                    request.setAttribute("telefonos", usuarioActualizado.getTelefono());
                    request.setAttribute("correoElectronico", usuarioActualizado.getEmail());
                    request.setAttribute("usuario", usuarioActualizado.getUsuario());
                    request.setAttribute("contrasena", usuarioActualizado.getContrasena());
                    request.setAttribute("estado", estado);

                    RequestDispatcher dispatcher = request.getRequestDispatcher("/EditarCliente.jsp");
                    dispatcher.forward(request, response); 
                }
            } else {
                response.sendRedirect("InicioCliente.jsp");
            }
        } else {
            response.sendRedirect("InicioCliente.jsp");
        }
        
        
        
        if (request.getParameter("btnBuscar") != null) {
        	
        	String DNI = new String();
        	Usuario usuario = new Usuario();
        	
        	DNI = (request.getParameter("dniCliente"));
        	usuario = usuarioNeg.obtenerClientePorDNI(DNI);
        	 request.setAttribute("usuario", usuario.getUsuario());
             request.setAttribute("contrasena", usuario.getContrasena());

            RequestDispatcher dispatcher = request.getRequestDispatcher("/BuscarCliente.jsp");
            dispatcher.forward(request, response); 
            
        } 
            else if (request.getParameter("btnActualizar") != null) {
                Usuario usuarioEditado = new Usuario();
                
                usuarioEditado.setDni(request.getParameter("dniCliente"));
                usuarioEditado.setContrasena(request.getParameter("contrasena"));
                
                boolean filas = usuarioNeg.editarContraseña(usuarioEditado);
                
               if(filas == true) {
            	   request.setAttribute("filas", filas);
            	   RequestDispatcher dispatcher = request.getRequestDispatcher("/BuscarCliente.jsp");
                   dispatcher.forward(request, response); 
            	   
            	   
               }
                
            }
     
        
    }

}
	



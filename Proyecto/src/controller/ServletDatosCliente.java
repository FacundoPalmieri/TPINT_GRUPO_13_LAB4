package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entidad.Direccion;
import entidad.Localidad;
import entidad.Persona;
import entidad.Provincia;
import negocio.UsuarioNeg;
import negocioimpl.UsuarioNegImpl;

/**
 * Servlet implementation class ServletDatosCliente
 */
@WebServlet("/ServletDatosCliente")
public class ServletDatosCliente extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UsuarioNeg usuarioNeg = new UsuarioNegImpl();
	
    public ServletDatosCliente() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//VISUALIZAR DATOS SIENDO CLIENTE
        HttpSession session = request.getSession(false);
        Integer tipoUsuario = (Integer) session.getAttribute("tipoUsuario");
    	if (tipoUsuario != null && tipoUsuario == 2) {
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
	}

}

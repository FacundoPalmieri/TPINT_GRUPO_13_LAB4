package controller;

import java.io.IOException;
import java.time.LocalDate;

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
import entidad.Pais;
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
    	
    	// MODIFICAR CLIENTE.JSP
        if (request.getParameter("btnBuscar") != null) {
        	
        	String DNI = new String();
        	Persona persona = new Persona();

        	
        	DNI = (request.getParameter("dniCliente"));
        	persona = usuarioNeg.GuardarPersonaCompleta(DNI);
        	
        	if (persona != null) {
        	
        	request.setAttribute("usuario", persona.getUsuario());
        	request.setAttribute("pass", persona.getUsuario().getPass());
            request.setAttribute("persona", persona);
            request.setAttribute("direccion", persona.getDireccion());
            request.setAttribute("provincia", persona.getDireccion().getLocalidad().getProvincia() );
            request.setAttribute("localidad", persona.getDireccion().getLocalidad() );
  
            
            // Redirige al JSP de edición
            RequestDispatcher dispatcher = request.getRequestDispatcher("/ModificarCliente.jsp");
            dispatcher.forward(request, response);
        	}else {
                request.setAttribute("error", "No se encontró ningún cliente con ese DNI.");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/ModificarCliente.jsp");
                dispatcher.forward(request, response);
            }
        	
            
        } 
        
        if (request.getParameter("btnActualizar") != null) {
            	
        	String dni = request.getParameter("dni");
            
            Persona persona = new Persona();
            persona.setDni(dni);
            persona.setCuil(request.getParameter("cuil"));
            persona.setNombre(request.getParameter("nombre"));
            persona.setApellido(request.getParameter("apellido"));
            persona.setSexo(request.getParameter("sexo"));
            persona.setCelular(request.getParameter("celular"));
            persona.setTelefono(request.getParameter("telefonos"));
            persona.setNacionalidad(request.getParameter("nacionalidad"));
            persona.setFechaNacimiento(LocalDate.parse(request.getParameter("fechaNacimiento")));
            persona.setEmail(request.getParameter("correoElectronico"));

            Usuario usuario = new Usuario();
            usuario.setUsuario(request.getParameter("usuario"));
            persona.setUsuario(usuario);
            
            Pais pais = new Pais();
            pais.setNombre(request.getParameter("pais"));
            
            Provincia provincia = new Provincia();
            provincia.setId(Integer.parseInt(request.getParameter("provincia_id")));
            provincia.setNombre(request.getParameter("provincia"));
            
            Localidad localidad = new Localidad();
            localidad.setId(Integer.parseInt(request.getParameter("localidad_id")));
            localidad.setNombre(request.getParameter("localidad"));

            Direccion direccion = new Direccion();
            direccion.setCalle(request.getParameter("calle"));
            direccion.setAltura(Integer.parseInt(request.getParameter("numero")));
            direccion.setPiso(request.getParameter("piso"));
            direccion.setDepartamento(request.getParameter("departamento"));
            direccion.setLocalidad_id(Integer.parseInt(request.getParameter("localidad_id")));
            persona.setDireccion(direccion);

            boolean actualizado = usuarioNeg.actualizarPersonaCompleta(persona);

            if (actualizado) {
                request.setAttribute("mensaje", "Cliente actualizado correctamente.");
            } else {
                request.setAttribute("error", "Error al actualizar el cliente.");
            }

            RequestDispatcher dispatcher = request.getRequestDispatcher("/ModificarCliente.jsp");
            dispatcher.forward(request, response);
        }
    }
}
	
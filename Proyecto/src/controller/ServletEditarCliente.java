package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;

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
import negocio.DatosGeograficosNeg;
import negocio.UsuarioNeg;
import negocioimpl.DatosGeograficosNegImpl;
import negocioimpl.UsuarioNegImpl;

/**
 * Servlet implementation class EditarCliente
 */
@WebServlet("/EditarCliente")
public class ServletEditarCliente extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UsuarioNeg usuarioNeg = new UsuarioNegImpl();
	DatosGeograficosNeg datosGeoNeg = new DatosGeograficosNegImpl();
       
   
    public ServletEditarCliente() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Iniciando metodo doGet de ServletEditarCliente...");
	
		
	    // Obtener las listas de paises y provincias
	    ArrayList<Pais> listaPaises = datosGeoNeg.ObtenerPais();
	    ArrayList<Provincia> listaProvincias = datosGeoNeg.ObtenerProvincia();
	    request.setAttribute("paises", listaPaises);
	    request.setAttribute("provincias", listaProvincias);

	    // Verificar si el parametro "provincia" esta presente para cargar localidades
	    String provinciaId = request.getParameter("provincia");
	    if (provinciaId != null && !provinciaId.isEmpty()) {
	        int idProvincia = Integer.parseInt(provinciaId);
	        ArrayList<Localidad> listaLocalidades = datosGeoNeg.ObtenerLocalidad(idProvincia);

	        // Constrviseuir el JSON manualmente
	        StringBuilder jsonLocalidades = new StringBuilder();
	        jsonLocalidades.append("[");
	        for (int i = 0; i < listaLocalidades.size(); i++) {
	            Localidad localidad = listaLocalidades.get(i);
	            jsonLocalidades.append("{");
	            jsonLocalidades.append("\"id\":").append(localidad.getId()).append(",");
	            jsonLocalidades.append("\"nombre\":\"").append(localidad.getNombre()).append("\"");
	            jsonLocalidades.append("}");
	            if (i < listaLocalidades.size() - 1) {
	                jsonLocalidades.append(",");
	            }
	        }
	        jsonLocalidades.append("]");

	        // Configurar la respuesta HTTP
	        response.setContentType("application/json");
	        response.setCharacterEncoding("UTF-8");

	        // Enviar la respuesta JSON al cliente
	        PrintWriter out = response.getWriter();
	        out.print(jsonLocalidades.toString());
	        out.flush();

	        return;
	    }

	    // Verificar el tipo de usuario y obtener datos del cliente para edición
	    HttpSession session = request.getSession(false);
	    Integer tipoUsuario = (Integer) session.getAttribute("tipoUsuario");
	    if (tipoUsuario != null && tipoUsuario == 2) {
	        String nombreUsuario = (String) session.getAttribute("usuario");

	        if (nombreUsuario != null) {
	            // Obtener datos del cliente
	            Persona persona = usuarioNeg.ObtenerCliente(nombreUsuario);

	            if (persona != null) {
	                Direccion direccion = persona.getDireccion();
	                Provincia provincia = direccion.getLocalidad().getProvincia();
	                Localidad localidad = direccion.getLocalidad();

	                // Establecer atributos para el JSP
	                request.setAttribute("persona", persona);
	                request.setAttribute("direccion", direccion);
	                request.setAttribute("provincia", provincia);
	                request.setAttribute("localidad", localidad);

	                // Redirigir al JSP
	                RequestDispatcher dispatcher = request.getRequestDispatcher("/DatosCliente.jsp");
	                dispatcher.forward(request, response);
	            } else {
	                // Manejar el caso donde no se encuentra la persona
	                response.sendRedirect("Login.jsp");
	            }
	        } else {
	            // Manejar el caso donde no se encuentra el nombre de usuario en la sesión
	            response.sendRedirect("Login.jsp");
	        }
	    } else {
	        // Manejar el caso donde el tipo de usuario no es 2
	        response.sendRedirect("Login.jsp");
	    }
	}

	
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    	
    	// MODIFICAR CLIENTE.JSP
        if (request.getParameter("btnBuscar") != null) {
        	
        	String DNI = new String();
        	Persona persona = new Persona();

        	
        	DNI = (request.getParameter("dniCliente"));
        	persona = usuarioNeg.GuardarPersonaCompleta(DNI);
        	
        
        	
        	if (persona!= null && persona.getDni() != null) {
        		ArrayList<Pais> listaPaises = datosGeoNeg.ObtenerPais();
    		    ArrayList<Provincia> listaProvincias = datosGeoNeg.ObtenerProvincia();
    		    
    		    request.setAttribute("paises", listaPaises);
    		    request.setAttribute("provincias", listaProvincias);	
        	
	        	request.setAttribute("usuario", persona.getUsuario());
	        	request.setAttribute("pass", persona.getUsuario().getPass());
	            request.setAttribute("persona", persona);
	            ArrayList<Localidad> listaLocalidades = datosGeoNeg.ObtenerLocalidad(persona.getDireccion().getLocalidad().getProvincia().getId());
	            request.setAttribute("localidades", listaLocalidades);
	            request.setAttribute("direccion", persona.getDireccion());
	            request.setAttribute("provincia", persona.getDireccion().getLocalidad().getProvincia() );
	            request.setAttribute("localidad", persona.getDireccion().getLocalidad() );
	            request.setAttribute("pais", persona.getDireccion().getLocalidad().getProvincia().getPais());
	            
	            request.setAttribute("persona", persona);
        		RequestDispatcher dispatcher = request.getRequestDispatcher("/ModificarCliente.jsp");
                dispatcher.forward(request, response);
                
        	}else {
        		request.setAttribute("Mensaje", "No se encontró ningún cliente con ese DNI.");
        		RequestDispatcher dispatcher = request.getRequestDispatcher("/ModificarCliente.jsp");
                dispatcher.forward(request, response);
            }
        	
            
       }
        	
            
         
        
        if (request.getParameter("btnActualizar") != null) {
        	//Validación de contraseña
			String pass = request.getParameter("pass");
			String pass2 = request.getParameter("pass2");
			System.out.println("PASS1 : "+ pass);
			System.out.println("PASS2 : "+ pass2);
			
			if (pass.equals(pass2)) {
        	
        	
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
	            usuario.setPass(request.getParameter("pass"));
	            persona.setUsuario(usuario);
	           
	            int paisId = Integer.parseInt(request.getParameter("pais"));
	            int provinciaId = Integer.parseInt(request.getParameter("provincia"));
	            int localidadId = Integer.parseInt(request.getParameter("localidad"));
	            
	            Pais pais = new Pais();
	            pais.setId(paisId);
	            
	            Provincia provincia = new Provincia();
	            provincia.setId(provinciaId);
	            provincia.setPais(pais);
	
	            
	            Localidad localidad = new Localidad();
	            localidad.setId(localidadId);
	            localidad.setProvincia(provincia);
	
	            Direccion direccion = new Direccion();
	            direccion.setId(Integer.parseInt(request.getParameter("idDireccion")));
	            direccion.setCalle(request.getParameter("calle"));
	            direccion.setAltura(Integer.parseInt(request.getParameter("numero")));
	            direccion.setPiso(request.getParameter("piso"));
	            direccion.setDepartamento(request.getParameter("departamento"));
	            direccion.setLocalidad(localidad);
	            persona.setDireccion(direccion);
	
	            boolean actualizado = usuarioNeg.actualizarPersonaCompleta(persona);

	            if (actualizado) {
	                request.setAttribute("Mensaje", "Cliente actualizado correctamente.");               
	  			
	            } else {
	                request.setAttribute("Mensaje", "Error al actualizar el cliente.");
	            		  
	            }
            
		  }else {
			  request.setAttribute("Mensaje", "Las contraeñas no coinciden.");
			  
		  }
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/ModificarCliente.jsp");
			dispatcher.forward(request, response);
        }
    }
}
	
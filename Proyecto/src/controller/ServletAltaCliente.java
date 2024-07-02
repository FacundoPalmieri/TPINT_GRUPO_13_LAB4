package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidad.Direccion;
import entidad.Localidad;
import entidad.Pais;
import entidad.Persona;
import entidad.Provincia;
import entidad.Usuario;
import negocio.DatosGeograficosNeg;
import negocio.UsuarioNeg;
import negocioimpl.DatosGeograficosNegImpl;
import negocioimpl.UsuarioNegImpl;

/**
 * Servlet implementation class AltaCliente
 */
@WebServlet("/AltaCliente")
public class ServletAltaCliente extends HttpServlet {
	private static final long serialVersionUID = 1L;

	UsuarioNeg usuarioNeg = new UsuarioNegImpl();
	DatosGeograficosNeg datosGeoNeg = new DatosGeograficosNegImpl();
	
    
    public ServletAltaCliente() {
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			System.out.println("Iniciando método doGet de ServletAltaCliente...");
		  	ArrayList<Pais> listaPaises = datosGeoNeg.ObtenerPais();
	        ArrayList<Provincia> listaProvincias = datosGeoNeg.ObtenerProvincia();
	     

	        request.setAttribute("paises", listaPaises);
	        request.setAttribute("provincias", listaProvincias);
	        
	        
	        String provinciaId = request.getParameter("provincia");
	        System.out.println("Provincia" + provinciaId);

	        if (provinciaId != null && !provinciaId.isEmpty()) {
	            int idProvincia = Integer.parseInt(provinciaId);
	            ArrayList<Localidad> listaLocalidades = datosGeoNeg.ObtenerLocalidad(idProvincia);
	            
	            // Construir el JSON manualmente
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
	    
	        System.out.println("Pais" + listaPaises);
	        RequestDispatcher dispatcher = request.getRequestDispatcher("/AgregarCliente.jsp");
	        dispatcher.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("btnAceptar")!=null)
	    {
	      
			Persona persona = new Persona();
			Usuario usuario = new Usuario();
			Direccion direccion = new Direccion();
			
			persona.setDni(request.getParameter("dni"));
			persona.setCuil(request.getParameter("cuil"));
			persona.setNombre(request.getParameter("nombre"));
			persona.setApellido(request.getParameter("apellido"));
			persona.setSexo(request.getParameter("sexo"));
			persona.setCelular(request.getParameter("celular"));
			persona.setTelefono(request.getParameter("telefonos"));
			persona.setFechaNacimiento(request.getParameter("fechaNacimiento"));
			persona.setNacionalidad(request.getParameter("nacionalidad"));
			persona.setEmail(request.getParameter("correoElectronico"));
			
		
			direccion.setCalle(request.getParameter("calle"));
	        direccion.setAltura(Integer.parseInt(request.getParameter("numero")));
	        direccion.setPiso(request.getParameter("piso"));
	        direccion.setDepartamento(request.getParameter("depto")); 
            direccion.setLocalidad_id(Integer.parseInt(request.getParameter("localidad")));
			
			
			usuario.setUsuario(request.getParameter("usuario"));
			usuario.setPass(request.getParameter("contrasena"));
			usuario.setTipoUsuarioId(2);
			usuario.setPersona_dni(request.getParameter("dni"));
			
			boolean estado = true;
			boolean validacion = true;
			
			//Validación de contraseña
			String Contrasenia1 = request.getParameter("contrasena");
			String Contrasenia2 = request.getParameter("contrasena2");
			System.out.println("PASS1 : "+ Contrasenia1);
			System.out.println("PASS2 : "+ Contrasenia2);
			
			if (Contrasenia1.equals(Contrasenia2)) {
				
		   //Valida que el cliente no exista antes de agregarlo.
			validacion = usuarioNeg.validarUsuario(persona.getDni(), usuario.getUsuario());
			if (validacion == false){
				 System.out.println("Estado de validacion : "+ validacion);
				 
				request.setAttribute("validacionCliente", validacion);
		    	RequestDispatcher dispatcher = request.getRequestDispatcher("/AgregarCliente.jsp");
				dispatcher.forward(request, response);	
			}
			else {
				estado = usuarioNeg.agregarCliente(usuario, persona, direccion);
		    
		    	request.setAttribute("validacionCliente", validacion);
		        request.setAttribute("estadoCliente", estado);
		    	RequestDispatcher dispatcher = request.getRequestDispatcher("/AgregarCliente.jsp");
				dispatcher.forward(request, response);	
			}
		  }
			else {
			    request.setAttribute("errorMensaje", "Las contraseñas no coinciden.");
			    RequestDispatcher dispatcher = request.getRequestDispatcher("/AgregarCliente.jsp");
			    dispatcher.forward(request, response);
			}
			 
	        
	    }
	
	}
	
}

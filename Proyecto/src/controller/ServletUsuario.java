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
import entidad.Direccion;
import entidad.Persona;
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
        if (request.getParameter("btnIngresar") != null) {
            String nombreUsuario = request.getParameter("txtUsuario");
            String contrasenia = request.getParameter("txtContrasenia");

            Usuario u = usuarioNegocio.obtenerUsuario(nombreUsuario);
            Persona p = usuarioNegocio.ObtenerCliente(nombreUsuario);

            HttpSession session = request.getSession();
            int esCliente = 0;
            esCliente = usuarioNegocio.validarLogin(nombreUsuario, contrasenia);

            if (esCliente == 2) {
                session.setAttribute("usuario", u.getUsuario());
                session.setAttribute("tipoUsuario", u.getTipoUsuarioId());
                session.setAttribute("Nombre", p.getNombre());
                session.setAttribute("Apellido", p.getApellido());
                session.setAttribute("dni", p.getDni());
                session.setAttribute("cuil", p.getCuil());
                session.setAttribute("Celular", p.getCelular());
                session.setAttribute("Telefono", p.getTelefono());
                session.setAttribute("Direccion_id", p.getDireccion_id());
                session.setAttribute("Nacionalidad", p.getNacionalidad());
                session.setAttribute("tipoUsuario", 2);
                request.setAttribute("validacionCliente", true);

                RequestDispatcher dispatcher = request.getRequestDispatcher("/InicioCliente.jsp");
                dispatcher.forward(request, response);
            } else if (esCliente == 1) {
                session.setAttribute("usuario", u.getUsuario());
                System.out.println("USUARIO EN SESION" + u.getUsuario());
                session.setAttribute("tipoUsuario", 1);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/UsuarioAdministrador.jsp");
                dispatcher.forward(request, response);
            } else {
                request.setAttribute("validacionCliente", false);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/Login.jsp");
                dispatcher.forward(request, response);
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	// LISTAR CLIENTES
    	if (request.getParameter("btnListarCliente") != null) {
            UsuarioNeg usuarioNeg = new UsuarioNegImpl();
            //ArrayList<Usuario> listaUsuarios = usuarioNeg.listaUsuarios();
            //ArrayList<Persona> listaPersonas = usuarioNeg.listarPersonas();
            //ArrayList<Direccion> listaDirecciones = usuarioNeg.listarDirecciones();
            ArrayList<Persona> listaPersonas = usuarioNeg.listarPersonasComposicion();
            //Se aguarda la lista en la session para actualizarla al realizar una modificacion
            HttpSession session = request.getSession();
            session.setAttribute("listaPersonas", listaPersonas);
            //request.setAttribute("listaUsuarios", listaUsuarios);
            //request.setAttribute("listaDirecciones", listaDirecciones);
            //request.setAttribute("listaPersonas", listaPersonas);
            RequestDispatcher dispatcher = request.getRequestDispatcher("ListarClientes.jsp");
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
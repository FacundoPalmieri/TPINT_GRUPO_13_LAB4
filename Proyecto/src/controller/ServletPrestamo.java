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

import entidad.Cuenta;
import entidad.Prestamo;
import negocio.CuentaNeg;
import negocio.UsuarioNeg;
import negocioimpl.CuentaNegImpl;
import negocioimpl.UsuarioNegImpl;

@WebServlet("/ServletPrestamo")
public class ServletPrestamo extends HttpServlet {
	private static final long serialVersionUID = 1L;
	CuentaNeg cuentaNeg = new CuentaNegImpl();
	UsuarioNeg usuarioNeg = new UsuarioNegImpl();
       
    public ServletPrestamo() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		String DNI = (String) session.getAttribute("dni");
		System.out.println("dni del cliente en servlet prestamos: " + session.getAttribute("dni"));
				   
		ArrayList<Cuenta> listaCuentas = cuentaNeg.obtenerCuentasPorDNI(DNI);
		System.out.println("listaCuentas en servlet: " + listaCuentas);
				    
		request.setAttribute("cuentas", listaCuentas);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/ClientePrestamo.jsp");
		dispatcher.forward(request, response);	
	
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Prestamo prestamo = new Prestamo();
		
		/*HttpSession session = request.getSession();
	    String Usuario = (String) session.getAttribute("usuario");
	    Usuario usuario = new Usuario();
	    usuario = usuarioNeg.obtenerUsuario(Usuario);
	    

        prestamo.setClienteDni(usuario.getPersona_dni());
        prestamo.setFecha(LocalDate.now()); // Fecha actual
        prestamo.setImporteSolicitado(Float.parseFloat(request.getParameter("importe")));
        prestamo.setCuotas(Integer.parseInt(request.getParameter("cuotas")));

        prestamo.setImporteAPagar(importeTotal);
        prestamo.setImporteCuota(importeCuota);
        prestamo.setEstado(1); // Estado inicial del préstamo, quizas debamos meter algunos estados en la base

        // Guardar el préstamo en la base de datos
        PrestamoNeg prestamoNeg = new PrestamoNegImpl();
        boolean guardado = prestamoNeg.solicitarPrestamo(prestamo);

        if (guardado) {
            
            response.sendRedirect("InicioCliente.jsp");
        } else {
            
            response.sendRedirect("InicioCliente.jsp");
        }*/
    }

}

package controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entidad.Cuenta;
import entidad.Prestamo;
import entidad.Usuario;
import negocio.CuentaNeg;
import negocio.PrestamoNeg;
import negocio.UsuarioNeg;
import negocioimpl.CuentaNegImpl;
import negocioimpl.PrestamoNegImpl;
import negocioimpl.UsuarioNegImpl;

/**
 * Servlet implementation class ServletPrestamo
 */
@WebServlet("/ServletPrestamo")
public class ServletPrestamo extends HttpServlet {
	private static final long serialVersionUID = 1L;
	CuentaNeg cuentaNeg = new CuentaNegImpl();
	UsuarioNeg usuarioNeg = new UsuarioNegImpl();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletPrestamo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getParameter("Param") != null) {
		    HttpSession session = request.getSession();
		    String Usuario = (String) session.getAttribute("usuario");
		    System.out.println("SESION USUARIO: " + Usuario);
		    String DNI = new String();
		    Usuario usuario = new Usuario();
		    
		    usuario = usuarioNeg.obtenerCliente(Usuario);
		    DNI = usuario.getDni();
		    System.out.println("DNI  " + DNI);
		    ArrayList<Cuenta> listaCuentas = cuentaNeg.obtenerCuentasPorDNI(DNI);
		    System.out.println();
		    
		    
		    request.setAttribute("cuentas", listaCuentas);
    		RequestDispatcher dispatcher = request.getRequestDispatcher("/ClientePrestamo.jsp");
			dispatcher.forward(request, response);	
		   
		 }

		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Prestamo prestamo = new Prestamo();
        prestamo.setClienteId((int) request.getSession().getAttribute("clienteId")); // Obtener el ID del cliente de la sesión
        prestamo.setFecha(LocalDate.now()); // Fecha actual
        prestamo.setImporteSolicitado(Float.parseFloat(request.getParameter("importe")));
        prestamo.setCuotas(Integer.parseInt(request.getParameter("cuotas")));

        // Calcular importes adicionales (importes a pagar, cuota, etc.)
        float importeTotal = calcularImporteTotal(prestamo.getImporteSolicitado(), prestamo.getCuotas());
        float importeCuota = importeTotal / prestamo.getCuotas();

        prestamo.setImporteAPagar(importeTotal);
        prestamo.setImporteCuota(importeCuota);
        prestamo.setEstado(1); // Estado inicial del préstamo

        // Guardar el préstamo en la base de datos
        PrestamoNeg prestamoNeg = new PrestamoNegImpl();
        boolean guardado = prestamoNeg.solicitarPrestamo(prestamo);

        if (guardado) {
            // Redirigir a una página de éxito o mostrar un mensaje de éxito
            response.sendRedirect("Exito.jsp");
        } else {
            // Redirigir a una página de error o mostrar un mensaje de error
            response.sendRedirect("Error.jsp");
        }
    }

    // Método ficticio para obtener las cuentas del cliente
    private List<Cuenta> obtenerCuentasDelCliente() {
        // Implementación para obtener las cuentas del cliente desde la capa de datos o negocio
        return null; // Debes implementar la lógica real para obtener las cuentas del cliente
    }

    // Método ficticio para calcular el importe total del préstamo
    private float calcularImporteTotal(float importeSolicitado, int cuotas) {
        // Implementación para calcular el importe total a pagar
        return importeSolicitado * (1 + (0.05f * cuotas)); // Supongamos una tasa de interés del 5% por cuota
    }

}

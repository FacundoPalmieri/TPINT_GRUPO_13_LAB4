package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import negocio.CuentaNeg;
import negocio.MovimientoNeg;
import negocioimpl.CuentaNegImpl;
import negocioimpl.MovimientoNegImpl;

/**
 * Servlet implementation class ServletTransferencia
 */
@WebServlet("/ServletTransferencia")
public class ServletTransferencia extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CuentaNeg cuentaNeg;
	private MovimientoNeg movimientoNeg;
	
   
	public void init() throws ServletException {
		super.init();
		cuentaNeg = new CuentaNegImpl();
		movimientoNeg = new MovimientoNegImpl();		
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(request.getParameter("cbuCliente")!= null) {
			
			
			
			
		}
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}

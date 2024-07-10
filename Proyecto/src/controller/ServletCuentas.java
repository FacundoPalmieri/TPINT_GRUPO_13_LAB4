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
import entidad.Persona;
import entidad.Usuario;
import negocio.CuentaNeg;
import negocio.MovimientoNeg;
import negocio.UsuarioNeg;
import negocioimpl.CuentaNegImpl;
import negocioimpl.MovimientoNegImpl;
import negocioimpl.UsuarioNegImpl;


@WebServlet("/ServletCuentas")
public class ServletCuentas extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	CuentaNeg cuentaNeg = new CuentaNegImpl();
	UsuarioNeg usuarioNeg = new UsuarioNegImpl();
	
	MovimientoNeg movimientoNeg = new MovimientoNegImpl();
	
       
   
    public ServletCuentas() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		 //Listar cuentas
		 if (request.getParameter("Param") != null) {
		    HttpSession session = request.getSession();
		    String Usuario = (String) session.getAttribute("usuario");
		    System.out.println("SESION USUARIO: " + Usuario);
		    String DNI = new String();
		    Usuario usuario = new Usuario();
		    
		    usuario = usuarioNeg.obtenerUsuario(Usuario);
		    DNI = usuario.getPersona_dni();
		    System.out.println("DNI  " + DNI);
		    ArrayList<Cuenta> listaCuentas = cuentaNeg.obtenerCuentasPorDNI(DNI);
		    System.out.println();
		    
		    
		    request.setAttribute("listaCuentas", listaCuentas);
		    
    		RequestDispatcher dispatcher = request.getRequestDispatcher("/CuentasCliente.jsp");
    		dispatcher.forward(request, response);
    
		   
		 }

	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// BUSCAR CLIENTE
		
		if (request.getParameter("btnBuscarClienteCrearCuenta") != null) {
			
        	Usuario usuario = new Usuario();
        	Persona persona = new Persona();
        	
        	String DNI = new String();
        	
        	DNI = request.getParameter("dniCliente"); 
        	usuario = usuarioNeg.obtenerUsuarioPorDNI(DNI);
        	System.out.println("USUARIO HABILITADO" + usuario.getHabilitado());
        	persona = usuarioNeg.ObtenerCliente(usuario.getUsuario());
        	
        	request.setAttribute("usuario", usuario.getUsuario());
            request.setAttribute("nombre", persona.getNombre());
            request.setAttribute("apellido", persona.getApellido());
           
            RequestDispatcher dispatcher = request.getRequestDispatcher("/CrearCuenta.jsp");
            dispatcher.forward(request, response); 
 
        } 
		
		
		//VALIDAR CLIENTE Y CREAR CUENTA
		if(request.getParameter("btnCrearCuenta") != null){
			String DNI = new String();
	
			DNI = request.getParameter("dniCliente");
	
			int CantidadCuenta = cuentaNeg.ValidarCantidad(DNI);
			
			
			if (CantidadCuenta < 3) {
				int tipoCuenta = 0;
				int nCuenta = 0;
				int estadoCrearCuenta = 0;
				int estadoCrearMovimiento = 0;
				
				String tipoCuentaStr = request.getParameter("tipoCuenta");
				tipoCuenta = Integer.parseInt(tipoCuentaStr);
				
				estadoCrearCuenta = cuentaNeg.CrearCuenta(DNI, tipoCuenta);
				System.out.println("Estado Crear Cuenta " + estadoCrearCuenta);
				
				nCuenta = cuentaNeg.buscarNCuenta(DNI);
				System.out.println("Numero cuenta" + nCuenta);
				estadoCrearMovimiento = movimientoNeg.CrearMovimiento(1,"Saldo Inicial",10000.00,nCuenta,1);
				
						
				if(estadoCrearCuenta == 1) {
					 request.setAttribute("Mensaje", "La cuenta ha sido creada");
				     RequestDispatcher dispatcher = request.getRequestDispatcher("/CrearCuenta.jsp");
					 dispatcher.forward(request, response);
					
				}else {
					 request.setAttribute("Mensaje", "La cuenta NO puedo ser creada");
				     RequestDispatcher dispatcher = request.getRequestDispatcher("/CrearCuenta.jsp");
					 dispatcher.forward(request, response);
				}
			
	   
			} else { // No permitir agregar más cuentas
				request.setAttribute("Mensaje", "El cliente ha alcanzado el limite de cuentas.");
			    RequestDispatcher dispatcher = request.getRequestDispatcher("/CrearCuenta.jsp");
			    dispatcher.forward(request, response);
			}
			
		}
	
	}

}
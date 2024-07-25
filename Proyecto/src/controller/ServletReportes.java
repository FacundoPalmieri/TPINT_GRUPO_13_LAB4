package controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import entidad.Movimientos;
import entidad.Persona;
import entidad.Prestamo;
import excepcion.DniInvalido;
import excepcion.fechaInvalida;
import excepcion.validacion;
import negocio.CuentaNeg;
import negocio.MovimientoNeg;
import negocio.PrestamoNeg;
import negocio.ReporteNeg;
import negocio.UsuarioNeg;
import negocioimpl.CuentaNegImpl;
import negocioimpl.MovimientoNegImpl;
import negocioimpl.PrestamoNegImpl;
import negocioimpl.ReporteNegImpl;
import negocioimpl.UsuarioNegImpl;


/**
 * Servlet implementation class ServletReportes
 */
@WebServlet("/ServletReportes")
public class ServletReportes extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ReporteNeg reporteNeg; 
	private PrestamoNeg prestamoNeg;
	private CuentaNeg cuentaNeg;
	private MovimientoNeg movimientoNeg;
	private UsuarioNeg usuarioNeg;
	private Persona persona;
       
	 public void init() throws ServletException {
	        super.init();
	        reporteNeg = new ReporteNegImpl(); //ver
	        prestamoNeg = new PrestamoNegImpl();
	        cuentaNeg = new CuentaNegImpl();
	        movimientoNeg = new MovimientoNegImpl();
	        usuarioNeg = new UsuarioNegImpl();
	        
	    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getParameter("btnBuscar") != null) {
			System.out.println("ENTRA A btnBuscar");
			
			//Obtengo parametros del JSP
			String fechaInicioStr = (String)request.getParameter("fechaInicio");
			String fechaFinStr = (String)request.getParameter("fechaFin");
			
			//Convierto las fechas de string a LocalDate para luego comparar.
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate fechaInicio = LocalDate.parse(fechaInicioStr, formatter);
	        LocalDate fechaFin = LocalDate.parse(fechaFinStr, formatter);
	        
	        //Verificación si la fecha de inicio es anterior a la fecha fin.
			if(fechaInicio.isBefore(fechaFin) || fechaInicio.isEqual(fechaFin)) {
				// Obtengo la fecha actual
	            LocalDate hoy = LocalDate.now();
	            
	            if(fechaFin.isBefore(hoy)|| fechaFin.isEqual(hoy)) {
	            	
	            	
	            	ArrayList<Movimientos> listaMovimientos = new ArrayList<Movimientos>();
					
					listaMovimientos = reporteNeg.PromedioIngresosMensuales(fechaInicio, fechaFin);
					System.out.println("LISTA MOVIMIENTOS FILTRADA"+listaMovimientos);
					
					request.setAttribute("listaMovimientos", listaMovimientos);	
					RequestDispatcher dispatcher = request.getRequestDispatcher("/ReportePromedioIngresosMensuales.jsp");
			        dispatcher.forward(request, response); 
	            }else {
	            	request.setAttribute("Mensaje", "La fecha fin no puede ser posterior al día actual.");	
					RequestDispatcher dispatcher = request.getRequestDispatcher("/ReportePromedioIngresosMensuales.jsp");
			        dispatcher.forward(request, response); 
	            }
	            
			
				
			}else {
				System.out.println("ENTRA AL ELSE");
				
				request.setAttribute("Mensaje", "La fecha de inicio debe ser anterior a la fecha fin.");	
				RequestDispatcher dispatcher = request.getRequestDispatcher("/ReportePromedioIngresosMensuales.jsp");
		        dispatcher.forward(request, response); 
			}
		
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if(request.getParameter("btnReporte")!=null) {
			System.out.println("reporte");
			String DNI = (String) request.getParameter("dniCliente");
			String ma = (String)request.getParameter("fecha1");
			String ma2 = (String)request.getParameter("fecha2");
			LocalDate fecha1;
			LocalDate fecha2;
			
			//Se valida que el dni sea numero y no mayor a 20 caracteres
			//Se valida que la fecha2 no sea mayor a fecha1 y que las fechas no excedan al dia de hoy
			try {
				validacion.validarFormatoDNI(DNI);	
				if(!reporteNeg.busquedaDNI(DNI)) {
					request.setAttribute("Mensaje","El dni no existe");
					RequestDispatcher dispatcher = request.getRequestDispatcher("/InicioReporte.jsp");
					dispatcher.forward(request, response);	
					return;
				}
				fecha1 = LocalDate.parse(ma, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
				fecha2 = LocalDate.parse(ma2, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
				System.out.println("DESDE: "+fecha1+" HASTA: "+fecha2);
				validacion.verificarFechas(fecha1, fecha2);
			}
			catch(DniInvalido dniI) {
				System.out.println("Error: "+dniI.getMessage());
				request.setAttribute("Mensaje",dniI.getMessage());
				RequestDispatcher dispatcher = request.getRequestDispatcher("/InicioReporte.jsp");
				dispatcher.forward(request, response);	
				return;
			}
			catch(fechaInvalida fi) {
				System.out.println("Error: "+fi.getMessage());
				System.out.println("Error: "+fi.getMessage());
				request.setAttribute("errorFecha",fi.getMessage());
				RequestDispatcher dispatcher = request.getRequestDispatcher("/InicioReporte.jsp");
				dispatcher.forward(request, response);	
				return;
			}
			catch(Exception e) {
				System.out.println("Error: "+e.getMessage());
				return;
			}
			ArrayList<Prestamo> listaPrestamos = new ArrayList<Prestamo>();
			ArrayList<Integer> estados = null;
			
			String[] checkBox = request.getParameterValues("opciones");
			if(checkBox!=null) {
				estados = new ArrayList<Integer>();
				for (String opcion : checkBox) {
					estados.add(Integer.parseInt(opcion));
				}
			}
			
			listaPrestamos = reporteNeg.prestamos(DNI,estados,fecha1,fecha2);
			
			if(listaPrestamos.size()==0) {
				request.setAttribute("Mensaje","El cliente no tiene prestamos en esas fechas y/o en el estado seleccionado");
				RequestDispatcher dispatcher = request.getRequestDispatcher("/InicioReporte.jsp");
				dispatcher.forward(request, response);	
			}
			else {
				System.out.println("reporte prestamos");
				
				//Se envia la lista de prestamos para buscar en la db el estado de sus pagos
				reporteNeg.verificarPagos(listaPrestamos);
				
				// Suponiendo que listaPrestamos es una lista de objetos Prestamo
				StringBuilder jsonBuilder = new StringBuilder();
				jsonBuilder.append("[");

				for (int i = 0; i < listaPrestamos.size(); i++) {
				    Prestamo prestamo = listaPrestamos.get(i);
				    jsonBuilder.append("{")
				                .append("\"id\":").append(prestamo.getId()).append(",")
				                .append("\"estado\":").append(prestamo.getEstado().getId()) // Asegúrate de ajustar según tu estructura
				                .append("}");

				    if (i < listaPrestamos.size() - 1) {
				        jsonBuilder.append(",");
				    }
				}
				
				//Envio al jsp los datos de la persona
				persona = usuarioNeg.obtenerClientePorDNI(Integer.parseInt(DNI));
				request.setAttribute("persona", persona);
				
				//Envío la lista de préstamos con formato json para los cálculos
				jsonBuilder.append("]");
				String prestamosJson = jsonBuilder.toString();
				request.setAttribute("prestamosJson", prestamosJson);

				//envío listado préstamos 
				request.setAttribute("listaPrestamos", listaPrestamos);	
				RequestDispatcher dispatcher = request.getRequestDispatcher("/Reporte.jsp");
				dispatcher.forward(request, response);	
			}
		}		
	}

}

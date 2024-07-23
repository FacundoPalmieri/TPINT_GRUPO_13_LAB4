package datosimpl;

import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;

import java.sql.PreparedStatement;

import datos.ReporteDao;
import entidad.Cuenta;
import entidad.EstadoPrestamo;
import entidad.Movimientos;
import entidad.PagosPrestamos;
import entidad.Persona;
import entidad.Prestamo;

public class ReporteDaoImpl implements ReporteDao {
	private Conexion cn;
	
	
	public boolean busquedaDNI(String dni) {
		cn = new Conexion();
		boolean resultado = false;
		String query = "select dni from personas where dni='"+dni+"'";
		ResultSet rs = null;
		System.out.println("QUERY OBTENER DNI: " + query);
		try {
			cn.Open();
			rs = cn.query(query);
			if(rs.next()) {
				resultado = true;
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			cn.close();
		}
		return resultado;
	}
	
	
	public boolean busquedaUsuario(String nombreUsuario) {
		cn = new Conexion();
		String query = "";
		
		System.out.println("QUERY OBTENER USUARIO: " + query);
		try {
			cn.Open();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			cn.close();
		}
		
		return false;
	}
	
	
	
	public ArrayList<Prestamo> prestamos(String dni, ArrayList<Integer> estado, LocalDate fecha1, LocalDate fecha2){
			Conexion cn = new Conexion();
		    ResultSet rs = null;
		    ArrayList<Prestamo> listaPrestamos = new ArrayList<>();
		    String query="SELECT p.*,ep.descripcion,ep.id 'idEstado',pe.dni,pe.nombre,pe.apellido FROM prestamos p "
		    		+"INNER JOIN personas pe ON p.cliente_dni=pe.dni "
		    		+"INNER JOIN EstadosPrestamos ep ON p.estado = ep.id " 
		    		+"WHERE (p.fecha>='"+fecha1+"' AND p.fecha<='"+fecha2+"') AND "
		    		+"p.cliente_dni='"+dni+"'";
		    
		    if(estado!=null) {
		    	System.out.println("ESTADO NO ES NULL");
		    	query+=" AND p.estado in(";
		    		for(int i=1;i<=5;i++) {
		    			if(estado.contains(i)) {
		    				query+=""+i+",";
		    			}
		    		}
		    		query+="0)";
		    	}
		    
		    
		    
		    System.out.println("QUERY: "+query);
		    
		    try {
		        cn.Open();
		        System.out.println("Conexion abierta obtenerPrestamos" + dni);
		        rs = cn.query(query);
		        while (rs.next()) {
		            Prestamo prestamo = new Prestamo();
		            Persona persona = new Persona();
		            //Cuenta cuenta = new Cuenta();
		            EstadoPrestamo estadoPrestamo = new EstadoPrestamo();
		            
		            persona.setDni(rs.getString("pe.dni"));
		            persona.setApellido(rs.getString("pe.apellido"));
		            persona.setNombre(rs.getString("pe.nombre"));
		            //persona.setEmail(rs.getString("personas.email"));
		            
		            prestamo.setId(rs.getInt("id"));
		            prestamo.setFecha(rs.getString("fecha"));
		            prestamo.setImporteSolicitado(rs.getFloat("importe_solicitado"));
		            prestamo.setImporteAPagar(rs.getFloat("importe_a_pagar"));
		            prestamo.setImporteCuota(rs.getFloat("importe_cuota"));
		            prestamo.setCuotas(rs.getInt("cuotas"));
		            prestamo.setCuotasAbonadas(rs.getInt("cuotas_abonadas"));
		            prestamo.setSaldoRestante(rs.getFloat("saldo_restante"));
		            
		            //cuenta.setNumeroCuenta(rs.getInt("cuentas.numero_cuenta"));
		            //cuenta.setCbu(rs.getString("cuentas.cbu"));
		            //cuenta.setSaldo(rs.getFloat("cuentas.saldo"));
		            
		            estadoPrestamo.setId(rs.getInt("idEstado"));
		            estadoPrestamo.setDescripcion(rs.getString("descripcion"));
		            
		            prestamo.setClienteDni(persona);
		            prestamo.setEstado(estadoPrestamo);
		            //prestamo.setCuentaDestino(cuenta);
		            
		            listaPrestamos.add(prestamo);
		        }
		        
		    } catch (Exception e) {
		        System.out.println("ERROR obtenerPrestamos DAO");
		        e.printStackTrace();
		    } finally {
		        try {
		            if (rs != null) {
		                rs.close();
		            }
		            cn.close();
		        } catch (Exception e2) {
		            System.out.println("ERROR CERRAR CONEXION obtenerPrestamos DAO");
		            e2.printStackTrace();
		        }
		    }
		    System.out.println("CANTIDAD DE PRESTAMOS EN ARRAYLIST: "+listaPrestamos.size());
		    return listaPrestamos;
		


	}
	


	@Override
	public ArrayList<Movimientos> PromedioIngresosMensuales(LocalDate fechaInicio, LocalDate fechaFin) {
		
		System.out.println("entra a método PromedioIngresosMensuales ");
		Conexion cn = new Conexion();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		ArrayList<Movimientos> listaMovimientos = new ArrayList<Movimientos>();
		
		String query = "SELECT  movimientos.numero_cuenta, movimientos.fecha, movimientos.detalle, ROUND(AVG(movimientos.importe))AS PromedioIngresos, tipomovimiento.descripcion,personas.dni, personas.nombre, personas.apellido "
			    	 + "FROM  Movimientos "
			    	 + "INNER JOIN tipomovimiento ON tipomovimiento.id = movimientos.tipo_movimiento_id "
			    	 + "INNER JOIN cuentas ON cuentas.numero_cuenta = movimientos.numero_cuenta "
			    	 + "INNER JOIN personas ON personas.dni = cuentas.cliente_dni "
			    	 + "WHERE tipomovimiento.id IN (1,2,4) AND movimientos.importe > 0 "
			    	 + "AND movimientos.fecha between ? AND ? "
			    	 + "AND cuentas.habilitado = 1 "
			    	 + "group by movimientos.numero_cuenta "
			    	 + "order by PromedioIngresos Desc "
			    	 + "LIMIT 3";
		System.out.println("query: "+query);
		try {
			cn.Open();
			  System.out.println("Conexion abierta PromedioIngresosMensuales");
			  
			  ps = cn.prepareStatement(query);
			  // Convertir LocalDate a java.sql.Date
		      java.sql.Date sqlFechaInicio = java.sql.Date.valueOf(fechaInicio);
		      java.sql.Date sqlFechaFin = java.sql.Date.valueOf(fechaFin);
		        
		      // Establecer los parámetros de la consulta
		      ps.setDate(1, sqlFechaInicio);
		      ps.setDate(2, sqlFechaFin);
		      
		      rs = ps.executeQuery();
		      while(rs.next()) {
		    	Movimientos movimientos = new Movimientos();
		    	Cuenta cuenta = new Cuenta ();
		    	Persona persona = new Persona();
		    	
		    	persona.setDni(rs.getString("personas.dni"));
		    	persona.setApellido(rs.getString("personas.apellido"));
		     	persona.setNombre(rs.getString("personas.nombre"));
		    	
		    	cuenta.setNumeroCuenta(rs.getInt("movimientos.numero_cuenta"));
		    	cuenta.setCliente_Dni(persona);
		    	
		    	movimientos.setImporte(rs.getDouble("PromedioIngresos"));
		    	
		    	movimientos.setCuenta_origen(cuenta);
		    	
		    	listaMovimientos.add(movimientos);
    	  
		      }
			
			
		} catch (Exception e) {
			 System.out.println("Conexion abierta PromedioIngresosMensuales");
			 e.printStackTrace();
		}
		finally {
			try {
				cn.close();
			} catch (Exception e2) {
				System.out.println("ERROR AL CERRAR CONEXION PromedioIngresosMensuales");
				 e2.printStackTrace();
			}
			
			try {
				ps.close();
			} catch (Exception e2) {
				System.out.println("ERROR AL CERRAR PS PromedioIngresosMensuales");
				 e2.printStackTrace();
			}
			try {
				rs.close();
			} catch (Exception e2) {
				System.out.println("ERROR AL CERRAR RS PromedioIngresosMensuales");
				 e2.printStackTrace();
			}
		}
		return listaMovimientos;
	}		
		
	
	public ArrayList<PagosPrestamos> pagosPrestamos(int idPrestamo){
		Conexion cn = new Conexion();
	    ResultSet rs = null;
		String query = "select * from pagosprestamo where prestamo_id='"+idPrestamo+"'";
		System.out.println("QUERY: "+query);
		ArrayList<PagosPrestamos> pagosPrestamos = new ArrayList<PagosPrestamos>();
		
		try {
			cn.Open();
	        System.out.println("Conexion abierta obtenerPagosPrestamos" + idPrestamo);
	        rs = cn.query(query);
	        while (rs.next()) {
	        	PagosPrestamos pago = new PagosPrestamos();
	        	pago.setImportePago(rs.getFloat("importe_pago"));
	        	pago.setEstado(rs.getInt("estado"));
	        	pagosPrestamos.add(pago);
	        }
		}
		catch(Exception e) {
			 System.out.println("ERROR obtenerPrestamos DAO");
		     e.printStackTrace();
		}
		finally {
			cn.close();
		}
		
		return pagosPrestamos;
	}
	
	
}

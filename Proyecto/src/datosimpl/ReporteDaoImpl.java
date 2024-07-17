package datosimpl;

import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;

import com.mysql.jdbc.PreparedStatement;

import datos.ReporteDao;
import entidad.Cuenta;
import entidad.EstadoPrestamo;
import entidad.Movimientos;
import entidad.Persona;
import entidad.Prestamo;

public class ReporteDaoImpl implements ReporteDao {
	private Conexion cn;
	
	
	public boolean busquedaDNI(String dni) {
		cn = new Conexion();
		String query = "";
		
		System.out.println("QUERY OBTENER DNI: " + query);
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
		    //PreparedStatement preparedStatement = null;
		    ArrayList<Prestamo> listaPrestamos = new ArrayList<>();
		    String query="SELECT p.*,ep.descripcion FROM prestamos p "
		    		+"INNER JOIN EstadosPrestamos ep ON p.estado = ep.id " 
		    		+"WHERE (p.fecha>=2024-06-12 AND p.fecha<=2025-06-12) AND p.cliente_dni='"+dni+"'";
		    
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
		        //preparedStatement = (PreparedStatement) cn.prepareStatement(query);
		        //preparedStatement.setString(1,dni);
		        //preparedStatement.setString(2,Integer.toString(estado));
		        //System.out.println("QUERY OBTENER PRESTAMOS: " + preparedStatement);
		        //rs = preparedStatement.executeQuery();
		        
		        while (rs.next()) {
		            Prestamo prestamo = new Prestamo();
		            //Persona persona = new Persona();
		            //Cuenta cuenta = new Cuenta();
		            //EstadoPrestamo estadoPrestamo = new EstadoPrestamo();
		            
		            //persona.setDni(rs.getString("personas.dni"));
		            //persona.setApellido(rs.getString("personas.apellido"));
		            //persona.setNombre(rs.getString("personas.nombre"));
		            //persona.setEmail(rs.getString("personas.email"));
		            
		            prestamo.setId(rs.getInt("prestamos.id"));
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
		            
		            //estadoPrestamo.setId(rs.getInt("estadosprestamos.id"));
		            //estadoPrestamo.setDescripcion(rs.getString("estadosprestamos.descripcion"));
		            
		            //prestamo.setClienteDni(persona);
		            //prestamo.setEstado(estadoPrestamo);
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
		    
		    return listaPrestamos;
		


	}
	
	
	
	public ArrayList<Movimientos> movimientos(){
		return new ArrayList<Movimientos>();
	}

}

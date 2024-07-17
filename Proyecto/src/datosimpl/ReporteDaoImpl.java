package datosimpl;

import java.util.ArrayList;

import datos.ReporteDao;
import entidad.Movimientos;
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
	
	
	
	public ArrayList<Prestamo> prestamos(String dni, int estado){
		
			Conexion cn = new Conexion();
		    ResultSet rs = null;
		    
		    ArrayList<Prestamo> listaPrestamos = new ArrayList<>();
		    
		    String query = "SELECT personas.dni, personas.apellido, personas.nombre, personas.email, " +
		                   "prestamos.id, prestamos.fecha, prestamos.importe_solicitado, prestamos.importe_a_pagar, " +
		                   "prestamos.importe_cuota, prestamos.cuotas, prestamos.cuotas_abonadas, " +
		                   "prestamos.saldo_restante, estadosprestamos.id, estadosprestamos.descripcion, "+ 
		                   "cuentas.numero_cuenta, cuentas.cbu, cuentas.saldo " +
		                   "FROM prestamos " +
		                   "INNER JOIN personas ON prestamos.cliente_dni = personas.dni " +
		                   "INNER JOIN estadosprestamos ON prestamos.estado = estadosprestamos.id " +
		                   "INNER JOIN cuentas ON cuentas.numero_cuenta = prestamos.cuenta_destino " +
		                   "WHERE personas.dni = ? and  estadoprestamos.id = ?";
		    
		    try {
		        cn.Open();
		        System.out.println("Conexion abierta obtenerPrestamos" + dni);
		        
		        PreparedStatement preparedStatement = cn.prepareStatement(query);
		        preparedStatement.setString(dni,estado);
		        rs = preparedStatement.executeQuery();
		        
		        while (rs.next()) {
		            Prestamo prestamo = new Prestamo();
		            Persona persona = new Persona();
		            Cuenta cuenta = new Cuenta();
		            EstadoPrestamo estadoPrestamo = new EstadoPrestamo();
		            
		            persona.setDni(rs.getString("personas.dni"));
		            persona.setApellido(rs.getString("personas.apellido"));
		            persona.setNombre(rs.getString("personas.nombre"));
		            persona.setEmail(rs.getString("personas.email"));
		            
		            prestamo.setId(rs.getInt("prestamos.id"));
		            prestamo.setFecha(rs.getString("fecha"));
		            prestamo.setImporteSolicitado(rs.getFloat("importe_solicitado"));
		            prestamo.setImporteAPagar(rs.getFloat("importe_a_pagar"));
		            prestamo.setImporteCuota(rs.getFloat("importe_cuota"));
		            prestamo.setCuotas(rs.getInt("cuotas"));
		            prestamo.setCuotasAbonadas(rs.getInt("cuotas_abonadas"));
		            prestamo.setSaldoRestante(rs.getFloat("saldo_restante"));
		            
		            cuenta.setNumeroCuenta(rs.getInt("cuentas.numero_cuenta"));
		            cuenta.setCbu(rs.getString("cuentas.cbu"));
		            cuenta.setSaldo(rs.getFloat("cuentas.saldo"));
		            
		            estadoPrestamo.setId(rs.getInt("estadosprestamos.id"));
		            estadoPrestamo.setDescripcion(rs.getString("estadosprestamos.descripcion"));
		            
		            prestamo.setClienteDni(persona);
		            prestamo.setEstado(estadoPrestamo);
		            prestamo.setCuentaDestino(cuenta);
		            
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

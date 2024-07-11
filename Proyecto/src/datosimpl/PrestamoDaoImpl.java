package datosimpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;


import datos.PrestamoDao;
import entidad.EstadoPrestamo;
import entidad.Persona;
import entidad.Prestamo;


public class PrestamoDaoImpl implements PrestamoDao{
	private Conexion cn;
	
	public Conexion getCn() {
		return cn;
	}

	public void setCn(Conexion cn) {
		this.cn = cn;
	}


	@Override
	public boolean guardarPrestamo(Prestamo prestamo, String clienteDni, int estadoPrestamo) {
		  Conexion cn = new Conexion();
		  PreparedStatement preparedStatement = null;
		  String query = "INSERT INTO prestamos (cliente_dni, fecha, importe_solicitado, importe_a_pagar, importe_cuota, cuotas, estado, cuotas_abonadas, saldo_restante) " +
						"VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
   try {
	   cn.Open();
	   System.out.println("Conexion abierta guardarPrestamo ");
	   preparedStatement = cn.prepareStatement(query);
       
	   preparedStatement.setString(1,clienteDni);
	   preparedStatement.setDate(2, java.sql.Date.valueOf(prestamo.getFecha()));
	   preparedStatement.setFloat(3, prestamo.getImporteSolicitado());
       preparedStatement.setFloat(4, prestamo.getImporteAPagar());
       preparedStatement.setFloat(5, prestamo.getImporteCuota());
       preparedStatement.setInt(6, prestamo.getCuotas());
       preparedStatement.setInt(7, estadoPrestamo);
       preparedStatement.setInt(8, prestamo.getCuotasAbonadas());
       preparedStatement.setFloat(9, prestamo.getSaldoRestante());

       int filasInsertadas = preparedStatement.executeUpdate();
       return filasInsertadas > 0;

   } catch (Exception e) {
	   System.out.println("ERROR guardarPrestamo DAO ");
       e.printStackTrace();
       return false;
   } finally {
	   try {
	       preparedStatement.close();
		   cn.close();
	} catch (Exception e2) {
		  System.out.println("ERROR CERRAR CONEXION guardarPrestamo DAO ");
	}
      
   }
 }
	

	@Override
	public ArrayList<Prestamo> obtenerPrestamos() {
		Conexion cn = new Conexion ();
		ResultSet rs = null;
		
		ArrayList<Prestamo> listaPrestamos = new ArrayList<Prestamo>();

		
		String query = "select personas.dni, personas.apellido,personas.nombre, personas.email,prestamos.id, prestamos.fecha, prestamos.importe_solicitado, prestamos.importe_a_pagar, prestamos.importe_cuota, prestamos.cuotas, prestamos.cuotas_abonadas, prestamos.saldo_restante, estadosprestamos.id, estadosprestamos.descripcion "
				     + "FROM prestamos "
				     + "INNER JOIN personas ON prestamos.cliente_dni = personas.dni "
				     + "INNER JOIN estadosprestamos ON prestamos.estado = estadosprestamos.id";			   
		
		try {
			cn.Open();
			System.out.println("Conexion abierta obtenerPrestamos");
			
			 rs = cn.query(query);
			 
			 while(rs.next()) {
				 Prestamo prestamo = new Prestamo();
		         Persona persona = new Persona();
		         EstadoPrestamo estadoPrestamo = new EstadoPrestamo();
				 persona.setDni(rs.getString("personas.dni"));
				 persona.setApellido(rs.getString("personas.apellido"));
				 persona.setNombre(rs.getString("personas.nombre"));
				 persona.setEmail(rs.getString("personas.email"));	
				 prestamo.setId(rs.getInt("prestamos.id"));
				 prestamo.setFecha(rs.getString("prestamos.fecha"));
				 prestamo.setImporteSolicitado(rs.getFloat("prestamos.importe_solicitado"));
				 prestamo.setImporteAPagar(rs.getFloat("prestamos.importe_a_pagar"));
				 prestamo.setImporteCuota(rs.getFloat("prestamos.importe_cuota"));
				 prestamo.setCuotas(rs.getInt("prestamos.cuotas"));
				 prestamo.setCuotasAbonadas(rs.getInt("prestamos.cuotas_abonadas"));
				 prestamo.setSaldoRestante(rs.getFloat("prestamos.saldo_restante"));
				 estadoPrestamo.setId(rs.getInt("estadosprestamos.id"));
				 estadoPrestamo.setDescripcion(rs.getString("estadosprestamos.descripcion"));
				 
				 prestamo.setClienteDni(persona);
				 prestamo.setEstado(estadoPrestamo);
				 
				 listaPrestamos.add(prestamo);
				 
			 }		
		} catch (Exception e) {
			System.out.println("ERROR obtenerPrestamos DAO");
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				cn.close();
			} catch (Exception e2) {
				System.out.println("ERROR CERRAR CONEXION obtenerPrestamos DAO");
				e2.printStackTrace();
			}
			
		}
		return listaPrestamos;		
	}

	
	@Override
	public ArrayList<Prestamo> obtenerPrestamosPorCliente(String DNI) {
		Conexion cn = new Conexion();
	    ResultSet rs = null;
	    
	    ArrayList<Prestamo> listaPrestamos = new ArrayList<>();
	    
	    String query = "SELECT personas.dni, personas.apellido, personas.nombre, personas.email, " +
	                   "prestamos.fecha, prestamos.importe_solicitado, prestamos.importe_a_pagar, " +
	                   "prestamos.importe_cuota, prestamos.cuotas, prestamos.cuotas_abonadas, " +
	                   "prestamos.saldo_restante, estadosprestamos.id, estadosprestamos.descripcion " +
	                   "FROM prestamos " +
	                   "INNER JOIN personas ON prestamos.cliente_dni = personas.dni " +
	                   "INNER JOIN estadosprestamos ON prestamos.estado = estadosprestamos.id " +
	                   "WHERE personas.dni = ?";
	    
	    try {
	        cn.Open();
	        System.out.println("Conexion abierta obtenerPrestamosPorCliente" + DNI);
	        
	        PreparedStatement preparedStatement = cn.prepareStatement(query);
	        preparedStatement.setString(1, DNI);
	        rs = preparedStatement.executeQuery();
	        
	        while (rs.next()) {
	            Prestamo prestamo = new Prestamo();
	            Persona persona = new Persona();
	            EstadoPrestamo estadoPrestamo = new EstadoPrestamo();
	            
	            persona.setDni(rs.getString("personas.dni"));
	            persona.setApellido(rs.getString("personas.apellido"));
	            persona.setNombre(rs.getString("personas.nombre"));
	            persona.setEmail(rs.getString("personas.email"));
	            
	            prestamo.setFecha(rs.getString("fecha"));
	            prestamo.setImporteSolicitado(rs.getFloat("importe_solicitado"));
	            prestamo.setImporteAPagar(rs.getFloat("importe_a_pagar"));
	            prestamo.setImporteCuota(rs.getFloat("importe_cuota"));
	            prestamo.setCuotas(rs.getInt("cuotas"));
	            prestamo.setCuotasAbonadas(rs.getInt("cuotas_abonadas"));
	            prestamo.setSaldoRestante(rs.getFloat("saldo_restante"));
	            
	            estadoPrestamo.setId(rs.getInt("estadosprestamos.id"));
	            estadoPrestamo.setDescripcion(rs.getString("estadosprestamos.descripcion"));
	            
	            prestamo.setClienteDni(persona);
	            prestamo.setEstado(estadoPrestamo);
	            
	            listaPrestamos.add(prestamo);
	        }
	        
	    } catch (Exception e) {
	        System.out.println("ERROR obtenerPrestamosPorCliente DAO");
	        e.printStackTrace();
	    } finally {
	        try {
	            if (rs != null) {
	                rs.close();
	            }
	            cn.close();
	        } catch (Exception e2) {
	            System.out.println("ERROR CERRAR CONEXION obtenerPrestamosPorCliente DAO");
	            e2.printStackTrace();
	        }
	    }
	    
	    return listaPrestamos;
	}

	@Override
	public int actualizarEstadoPrestamo(int idPrestamo, int estadoPrestamo) {
		Conexion cn = new Conexion();
		PreparedStatement ps = null;

		
		int estado = 0;
		
		String query = "UPDATE prestamos "
					 + "SET prestamos.estado = ? "
					 + "WHERE prestamos.id = ? ";
		
		try {
			cn.Open();
			System.out.println("CONEXION ABIERTA actualizarEstadoPrestamo DAO ");
			
			ps = cn.prepareStatement(query);
			ps.setInt(1, estadoPrestamo);
			ps.setInt(2, idPrestamo);
			
			estado = ps.executeUpdate();
	
		} catch (Exception e) {		
			System.out.println("ERROR actualizarEstadoPrestamo DAO ");
			e.printStackTrace();
		}finally {
			 try {
		            if (ps != null) {
		                ps.close();
		            }
		          
		            if (cn != null) {
		                cn.close();
		            }
		        } catch (Exception e2) {
				System.out.println("ERROR AL CERRAR CONEXION actualizarEstadoPrestamo DAO ");
				e2.printStackTrace();
			}
		
		}

		return estado; 
	
	}

	@Override
	public ArrayList<EstadoPrestamo> obtenerListadeEstado() {
		Conexion cn = new Conexion();
	    ResultSet rs = null;
	    PreparedStatement ps = null;
	    
	    ArrayList<EstadoPrestamo> listaEstados = new ArrayList<EstadoPrestamo>();
	    
	    
	    String query ="SELECT id,descripcion FROM estadosprestamos WHERE id <> 5 ";
	    
	    try {
	    	cn.Open();
	    	System.out.println("CONEXION ABIERTA obtenerListadeEstado ");
	    	
	    	ps = cn.prepareStatement(query);
	    	rs = ps.executeQuery();
	    	
	    	while(rs.next()) {
	    		EstadoPrestamo ep = new EstadoPrestamo();
	    		
	    		ep.setId(rs.getInt("id"));
	    		ep.setDescripcion(rs.getString("descripcion"));
	    		
	    		listaEstados.add(ep);
	    		
	    	}
	    } catch (Exception e) {
	        System.out.println("ERROR obtenerListadeEstado DAO");
	        e.printStackTrace();
	    } finally {
	        try {
	            if (rs != null) {
	                rs.close();
	            }
	            if (cn != null) {
	                cn.close();
	            }
	            if (ps != null) {
	                ps.close();
	            }
	         
	        } catch (Exception e2) {
	            System.out.println("ERROR CERRAR CONEXION obtenerListadeEstado DAO");
	            e2.printStackTrace();
	        }
	    }
	    
	    return listaEstados;
	}
	   

	/*

	@Override
	public Prestamo obtenerPrestamoPorId(int prestamoId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean actualizarPrestamo(Prestamo prestamo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean eliminarPrestamo(int prestamoId) {
		// TODO Auto-generated method stub
		return false;
	}
	*/

}

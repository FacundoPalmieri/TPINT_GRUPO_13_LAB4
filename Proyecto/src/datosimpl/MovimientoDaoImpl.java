package datosimpl;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;

import datos.MovimientoDao;
import entidad.Cuenta;
import entidad.Movimientos;
import entidad.TipoMovimiento;

public class MovimientoDaoImpl implements MovimientoDao{

	@Override
	public int CrearMovimiento(int cuentaOrigen,String detalle, double importe,  int tipoMovimiento) {
	int estado = 0;
			
			Conexion cn = new Conexion();
			PreparedStatement preparedStatement = null;
			
			String query ="INSERT INTO movimientos (numero_cuenta, fecha, detalle, importe, tipo_movimiento_id) VALUES (?,?,?,?,?)";
			System.out.println("query crear movimiento: " + query);
			
			try {
				cn.Open();
				preparedStatement = cn.prepareStatement(query);
				
				
			    preparedStatement.setInt(1, cuentaOrigen);
			    
			    Date fechaActual = Date.valueOf(LocalDate.now());
			    preparedStatement.setDate(2,fechaActual );
			    preparedStatement.setString(3, detalle);
			    preparedStatement.setDouble(4, importe);
			    preparedStatement.setInt(5, tipoMovimiento);
			    
			    estado = preparedStatement.executeUpdate();
				
			  
			    return estado;
				
				
			} catch (Exception e) {
				 e.printStackTrace();
			} finally {
		        try {
		            if (preparedStatement != null) {
		                preparedStatement.close();
		            }
		            if (cn != null) {
		                cn.close();
		            }
		        } catch (Exception e) {
		        	System.out.println("ERROR AL CERRAR CONEXION CrearMovimiento ") ;
		            e.printStackTrace();
		        }
		    }
			
			return estado;
		}

	@Override
	public ArrayList<Movimientos> ObtenerMovimientosPorCliente(int nCuenta) {
		Conexion cn = new Conexion();
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		
		ArrayList<Movimientos> listaMovimientos = new ArrayList<Movimientos>();
	

		
		String query ="SELECT movimientos.numero_cuenta,movimientos.fecha, movimientos.detalle,movimientos.importe, tipomovimiento.id, tipomovimiento.descripcion "
				     + "FROM Movimientos "
				     + "INNER JOIN tipomovimiento ON tipomovimiento.id = movimientos.tipo_movimiento_id "
				     + "WHERE  movimientos.numero_cuenta = ? " ;
		
		try {
			cn.Open();
			System.out.println("CONEXION ABIERTA ObtenerMovimientosPorCliente ");
			
			preparedStatement = cn.prepareStatement(query);
			preparedStatement.setInt(1, nCuenta);
		
			
			
			rs = preparedStatement.executeQuery();
			
		    while(rs.next()){
		    	Movimientos m = new Movimientos();	
				TipoMovimiento tm = new TipoMovimiento();
		
				
			    Date sqlDate = rs.getDate("movimientos.fecha");
		            if (sqlDate != null) {
		                LocalDate localDate = sqlDate.toLocalDate();
		                m.setFecha(localDate);
		            }
		            
		        m.setDetalle(rs.getString("movimientos.detalle"));
		        m.setImporte(rs.getDouble("movimientos.importe"));
		        tm.setId(rs.getInt("tipomovimiento.id"));
		        tm.setDescripcion(rs.getString("descripcion"));
		        
		        //Asigno a movimiento los objetos cuenta y TipoMovimiento
		        m.setTipo_Movimiento_id(tm);
		        
		        //Agrego movimiento a lista
		        listaMovimientos.add(m);
	       
			}

		} catch (Exception e) {
			 System.out.println("ERROR ObtenerMovimientosPorCliente DAO");
		}  finally {
	        try {
	            if (preparedStatement != null) {
	                preparedStatement.close();
	            }
	            if (rs != null) {
	                rs.close();
	            }
	            if (cn != null) {
	                cn.close();
	            }
	        } catch (Exception e) {
	        	System.out.println("ERROR AL CERRAR CONEXION ObtenerMovimientosPorCliente ") ;
	            e.printStackTrace();
	        }
	    }
	    
	   
	    return listaMovimientos;
   }
		
		
}



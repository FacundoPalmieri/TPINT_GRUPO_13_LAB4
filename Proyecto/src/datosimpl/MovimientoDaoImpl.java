package datosimpl;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.time.LocalDate;

import datos.MovimientoDao;

public class MovimientoDaoImpl implements MovimientoDao{

	@Override
	public int CrearMovimiento(int cuentaOrigen,String detalle, double importe, int CuentaDestino, int tipoMovimiento) {
	int estado = 0;
			
			Conexion cn = new Conexion();
			
			String query ="INSERT INTO movimientos (cuenta_origen, fecha, detalle, importe, cuenta_destino, tipo_movimiento_id) VALUES (?,?,?,?,?,?)";
			System.out.println("query crear movimiento: " + query);
			
			try {
				cn.Open();
				PreparedStatement preparedStatement = cn.prepareStatement(query);
				
				
			    preparedStatement.setInt(1, cuentaOrigen);
			    
			    Date fechaActual = Date.valueOf(LocalDate.now());
			    preparedStatement.setDate(2,fechaActual );
			    preparedStatement.setString(3, detalle);
			    preparedStatement.setDouble(4, importe);
			    preparedStatement.setInt(5, CuentaDestino);
			    preparedStatement.setInt(6, tipoMovimiento);
			    
			    estado = preparedStatement.executeUpdate();
				
			  
			    return estado;
				
				
			} catch (Exception e) {
				 e.printStackTrace();
			}
			finally
			{
				cn.close();
			}
			
			return estado;
		}

}

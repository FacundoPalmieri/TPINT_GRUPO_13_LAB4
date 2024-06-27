package datosimpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import datos.CuentaDao;

public class CuentaDaoImpl implements CuentaDao {
	
	private Conexion cn;

	@Override
	public int ValidarCantidad(String DNI) {
		int cantidadCuentas = 0;
		Conexion cn = new Conexion();
		
		String query = "SELECT COUNT(*) AS Cantidadcuentas FROM CUENTAS INNER JOIN usuarios on usuarios.id = cuentas.cliente_id where usuarios.dni = ?";
		try {
			cn.Open();
			 PreparedStatement preparedStatement = cn.prepareStatement(query);
		     preparedStatement.setString(1, DNI);
		     
		     ResultSet rs = preparedStatement.executeQuery();
		     
		     
		     if (rs.next()) {
		            cantidadCuentas = rs.getInt("Cantidadcuentas");
		      }


		        rs.close();
		        preparedStatement.close();
			
			
			
		} catch (Exception e) {
			 e.printStackTrace();
		}
		
		 System.out.println("CUENTA DAO CANTIDAD CUENTAS : " + cantidadCuentas);
		return cantidadCuentas;
		
		
	}

}

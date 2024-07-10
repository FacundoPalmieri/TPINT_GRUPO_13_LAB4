package datosimpl;

import java.sql.PreparedStatement;
import java.util.List;

import datos.PrestamoDao;
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
	public List<Prestamo> obtenerPrestamosPorCliente(int clienteId) {
		// TODO Auto-generated method stub
		return null;
	}

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

}

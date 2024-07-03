package datosimpl;

import java.sql.PreparedStatement;
import java.util.List;

import datos.PrestamoDao;
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
	public boolean guardarPrestamo(Prestamo prestamo) {
		Conexion cn = new Conexion();
		String query = "INSERT INTO prestamos (cliente_id, fecha, importe_solicitado, importe_a_pagar, importe_cuota, cuotas, estado, cuotas_abonadas, saldo_restante) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
   try {
	   cn.Open();
	   PreparedStatement ps = cn.prepareStatement(query);
       
       ps.setString(1, prestamo.getClienteDni());
       ps.setDate(2, java.sql.Date.valueOf(prestamo.getFecha()));
       ps.setFloat(3, prestamo.getImporteSolicitado());
       ps.setFloat(4, prestamo.getImporteAPagar());
       ps.setFloat(5, prestamo.getImporteCuota());
       ps.setInt(6, prestamo.getCuotas());
       ps.setInt(7, prestamo.getEstado());
       ps.setInt(8, prestamo.getCuotasAbonadas());
       ps.setFloat(9, prestamo.getSaldoRestante());

       int filasInsertadas = ps.executeUpdate();
       return filasInsertadas > 0;

   } catch (Exception e) {
       e.printStackTrace();
       return false;
   } finally {
       cn.close();
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

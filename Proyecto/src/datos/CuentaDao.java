package datos;
import java.util.ArrayList;

import entidad.Cuenta;

public interface CuentaDao {
	
	public int ValidarCantidad(String DNI);
	
	public int CrearCuenta (String DNI, int TipoCuenta);

	public int ultimaCuentaCreada(String DNI);

	public ArrayList<Cuenta> obtenerCuentasPorDNI(String DNI);
	
	public int modificarSaldo(int nCuenta, float monto);
	
	public Cuenta obtenerSaldo(int nCuenta);
	


}

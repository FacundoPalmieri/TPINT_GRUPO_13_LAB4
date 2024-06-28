package datos;
import java.util.ArrayList;

import entidad.Cuenta;

public interface CuentaDao {
	
	public int ValidarCantidad(String DNI);
	
	public int CrearCuenta (String DNI, int TipoCuenta);

	public int buscarNCuenta(String DNI);

	public ArrayList<Cuenta> obtenerCuentasPorDNI(String DNI);


}

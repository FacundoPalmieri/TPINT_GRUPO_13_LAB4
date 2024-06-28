package datos;
import java.util.ArrayList;
import java.util.List;

import entidad.Cuenta;

public interface CuentaDao {
	
	public int ValidarCantidad(String DNI);
	
	public int CrearCuenta (String DNI, int TipoCuenta);
	
	public ArrayList<Cuenta> obtenerCuentasPorDNI(String DNI);

}

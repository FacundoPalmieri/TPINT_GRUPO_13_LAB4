package datos;
import java.util.ArrayList;

import entidad.Cuenta;
import entidad.Usuario;

public interface CuentaDao {
	
	public int ValidarCantidad(String DNI);
	
	public int CrearCuenta (String DNI, int TipoCuenta);

	public int ultimaCuentaCreada(String DNI);

	public ArrayList<Cuenta> obtenerCuentasPorDNI(String DNI);
	
	public int modificarSaldo(int nCuenta, float monto);
	
	public Cuenta obtenerSaldo(int nCuenta);
	
	public Cuenta obtenerCuentaporCBU(String cbu);
	
	public int setearEstadoCuenta(String dni, int estado);
	
	
	public int setearEstadoPorCuenta(String dni, int estado, int nCuenta);

	public ArrayList<Cuenta> listarTodasLAsCuentas();
}

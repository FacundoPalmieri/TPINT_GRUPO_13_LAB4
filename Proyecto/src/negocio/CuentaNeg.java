package negocio;
import java.util.ArrayList;


import entidad.Cuenta;


public interface CuentaNeg {
	
	public int ValidarCantidad(String DNI);
	
	public int CrearCuenta (String DNI, int TipoCuenta);

	public int buscarNCuenta(String DNI);
	
	public ArrayList<Cuenta> obtenerCuentasPorDNI(String DNI);
	
	public int modificarSaldo(int nCuenta, float monto);
	
	public Cuenta obtenerSaldo(int nCuenta);
	
	public Cuenta obtenerCuentaporCBU(String cbu);
	
	public int setearEstadoCuenta(String dni, int estado);
	
	public int setearEstadoPorCuenta(String dni, int estado, int nCuenta);


}

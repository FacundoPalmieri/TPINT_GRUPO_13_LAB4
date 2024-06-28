package datos;

public interface CuentaDao {
	
	public int ValidarCantidad(String DNI);
	
	public int CrearCuenta (String DNI, int TipoCuenta);

}

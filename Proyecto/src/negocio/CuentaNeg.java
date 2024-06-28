package negocio;


public interface CuentaNeg {
	
	public int ValidarCantidad(String DNI);
	
	public int CrearCuenta (String DNI, int TipoCuenta);
	
	public int buscarNCuenta(String DNI);
	

}

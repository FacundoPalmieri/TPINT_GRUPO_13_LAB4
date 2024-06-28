package negocioimpl;

import datos.CuentaDao;

import datosimpl.CuentaDaoImpl;

import negocio.CuentaNeg;

public class CuentaNegImpl implements CuentaNeg {
	
	 private CuentaDao cuentaDao;
	
	 public CuentaNegImpl() {
	        cuentaDao = new CuentaDaoImpl();
	    }

	@Override
	public int ValidarCantidad(String DNI) {
		return  cuentaDao.ValidarCantidad(DNI);
	}

	@Override
	public int CrearCuenta(String DNI, int TipoCuenta) {
		return cuentaDao.CrearCuenta(DNI, TipoCuenta);
		
	}
	
}

			
			

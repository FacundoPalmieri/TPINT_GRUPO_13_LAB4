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
	
}

			
			

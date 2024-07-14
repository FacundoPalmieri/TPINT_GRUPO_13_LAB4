package negocioimpl;

import java.util.ArrayList;
import datos.CuentaDao;
import datosimpl.CuentaDaoImpl;
import entidad.Cuenta;
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


	public int buscarNCuenta(String DNI) {
		return cuentaDao.ultimaCuentaCreada(DNI);
	};

	@Override
	public ArrayList<Cuenta> obtenerCuentasPorDNI(String DNI) {
	
		return cuentaDao.obtenerCuentasPorDNI(DNI);
	}

	@Override
	public int modificarSaldo(int nCuenta, float monto) {
		return cuentaDao.modificarSaldo(nCuenta, monto);
	}

	@Override
	public Cuenta obtenerSaldo(int nCuenta) {
		return cuentaDao.obtenerSaldo(nCuenta);
	}
	
}

			
			

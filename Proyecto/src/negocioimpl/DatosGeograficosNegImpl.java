package negocioimpl;

import java.util.ArrayList;

import datos.DatosGeograficosDao;
import datosimpl.DatosGeograficosDaoImpl;
import entidad.Localidad;
import entidad.Pais;
import entidad.Provincia;
import negocio.DatosGeograficosNeg;

public class DatosGeograficosNegImpl implements DatosGeograficosNeg {
	
	private DatosGeograficosDao datosGeoDao;
	
	public DatosGeograficosNegImpl() {
		datosGeoDao = new DatosGeograficosDaoImpl();
    }

	@Override
	public ArrayList<Pais> ObtenerPais() {
		return (ArrayList<Pais>) datosGeoDao.ObtenerPais();
	}

	@Override
	public ArrayList<Provincia> ObtenerProvincia() {
		return  (ArrayList<Provincia>) datosGeoDao.ObtenerProvincia();
	}

	@Override
	public ArrayList<Localidad> ObtenerLocalidad(int id) {
		return  (ArrayList<Localidad>) datosGeoDao.ObtenerLocalidad(id);
	}

}

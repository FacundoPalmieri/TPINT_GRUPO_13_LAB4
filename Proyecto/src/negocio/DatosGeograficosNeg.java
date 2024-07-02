package negocio;

import java.util.ArrayList;

import entidad.Localidad;
import entidad.Pais;
import entidad.Provincia;

public interface DatosGeograficosNeg {
	
	public ArrayList<Pais>ObtenerPais();
	public ArrayList<Provincia> ObtenerProvincia();
	public ArrayList<Localidad> ObtenerLocalidad(int id);

}

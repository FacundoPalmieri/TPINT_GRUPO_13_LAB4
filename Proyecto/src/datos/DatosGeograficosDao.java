package datos;
import java.util.ArrayList;
import entidad.Pais;
import entidad.Provincia;
import entidad.Localidad;

public interface DatosGeograficosDao {
	
	public ArrayList<Pais>ObtenerPais();
	public ArrayList<Provincia> ObtenerProvincia();
	public ArrayList<Localidad> ObtenerLocalidad(int id);
	

}

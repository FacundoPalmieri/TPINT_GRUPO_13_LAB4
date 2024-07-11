package datosimpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import datos.DatosGeograficosDao;
import entidad.Localidad;
import entidad.Pais;
import entidad.Provincia;


public class DatosGeograficosDaoImpl implements DatosGeograficosDao {
	private Conexion cn;

	@Override
	public ArrayList<Pais> ObtenerPais() {
		
		 cn = new Conexion();
		    cn.Open();
		    System.out.println("CONEXION ABIERTA OBTENER PAISES");
		    
		   
		    ArrayList<Pais> listaPaises = new ArrayList<>();
		    
		    try {
		       
		    	String query = "SELECT * FROM paises";
		    		        

		        ResultSet rs = cn.query(query);
		        System.out.println("QWERY" + rs);

		      
		        while (rs.next()) {
		        	Pais pais = new Pais();
		        	pais.setId(rs.getInt("id"));
		        	pais.setNombre(rs.getString("nombre"));
		        	
		        	listaPaises.add(pais);      
		        }
		    } catch (Exception e) {
		        e.printStackTrace();
		    } finally {
		        cn.close();
		    }
		    return listaPaises;
	}

	@Override
	public ArrayList<Provincia> ObtenerProvincia() {
		
		 cn = new Conexion();
		 cn.Open();
		 System.out.println("CONEXION ABIERTA OBTENER PROVINCIAS");
		    
		   
	    ArrayList<Provincia> listaProvincias = new ArrayList<>();
	    
	    try {
	       
	    	String query = "SELECT * FROM Provincias";
	    		        

	        ResultSet rs = cn.query(query);
	        System.out.println("QWERY" + rs);

	      
	        while (rs.next()) {
	        	Provincia provincia = new Provincia();
	        	provincia.setId(rs.getInt("id"));
	        	provincia.setNombre(rs.getString("nombre"));
	        	
	        	listaProvincias.add(provincia);      
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        cn.close();
	    }
	    return listaProvincias;
	}

	@Override
	public ArrayList<Localidad> ObtenerLocalidad(int id) {
		
		 cn = new Conexion();
		 cn.Open();
		 System.out.println("CONEXION ABIERTA OBTENER LOCALIDAD"
		 		+ "");
		    
		 ArrayList<Localidad> listaLocalidades = new ArrayList<>();
	    
	    try {
	       
	    	String query = "SELECT * FROM localidades l "
	    			     + "INNER JOIN  Provincias p on l.Provincia_id = p.id "
	    			     + "WHERE p.ID = ?";
	    			
	    
	    	PreparedStatement preparedStatement = cn.prepareStatement(query);
	    	preparedStatement.setInt(1, id);

	    	ResultSet rs = preparedStatement.executeQuery();
	        System.out.println("QWERY" + rs);

	      
	        while (rs.next()) {
	        	Localidad localidad = new Localidad();
	        	localidad.setId(rs.getInt("id"));
	        	localidad.setNombre(rs.getString("nombre"));
	        	
	        	listaLocalidades.add(localidad);      
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        cn.close();
	    }
	    return listaLocalidades;
		
	}

}

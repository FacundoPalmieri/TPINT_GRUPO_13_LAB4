 package datosimpl;
import datos.UsuarioDao;

import entidad.Usuario;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class UsuarioDaoImpl implements UsuarioDao{
	private Conexion cn;
	
	@Override
	public int validarLogin(String usuario, String contrasenia) {
	    int estado = 0;
	    Conexion cn = new Conexion();
	    String query = "SELECT usuario, contrasenia, tipo_usuario_id FROM Usuarios WHERE usuario=? and contrasenia=?";
	    try {
	    	cn.Open();
	        PreparedStatement preparedStatement = cn.prepareStatement(query);
	        preparedStatement.setString(1, usuario);
	        preparedStatement.setString(2, contrasenia);

	        // Usar executeQuery para obtener un ResultSet
	        ResultSet rs = preparedStatement.executeQuery();

	        // Si se encuentra al menos un registro, el usuario existe sin se devuelve un 0
	        if (rs.next()) {
	        	//Si el usuario es de tipo Cliente(2) se devuelve un 2 si es de tipo Admin sera un 1
	        	estado = 2;
	        	if(rs.getInt("tipo_usuario_id")==1) {
	            	estado=1;
	            }
	            System.out.println(" DAO IMPL - Usuario encontrado: " + rs.getString("usuario") + " " + rs.getString("contrasenia"));
	        } else {
	            System.out.println("DAO IMPL - No se encontro usuario: " );
	        }

	        rs.close();
	        preparedStatement.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        cn.close();
	    }

	    System.out.println("Estado retorno USUARIODAO: " + estado);
	    return estado;
	}

	
	
	@Override
	public boolean validarUsuario(String DNI, String usuario) {
	    Conexion cn = new Conexion();
	    cn.Open();

	    boolean estado = true;
	    String query = "SELECT nombre, apellido FROM Usuarios WHERE dni=? OR usuario=?";

	    try {
	        PreparedStatement preparedStatement = cn.prepareStatement(query);
	        preparedStatement.setString(1, DNI);
	        preparedStatement.setString(2, usuario);

	        // Usar executeQuery para obtener un ResultSet
	        ResultSet rs = preparedStatement.executeQuery();

	        // Si se encuentra al menos un registro, el usuario existe
	        if (rs.next()) {
	            estado = false;
	            System.out.println("Usuario encontrado: " + rs.getString("nombre") + " " + rs.getString("apellido"));
	        } else {
	            System.out.println("No se encontro usuario con DNI: " + DNI + " o usuario: " + usuario);
	        }

	        rs.close();
	        preparedStatement.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        cn.close();
	    }

	    System.out.println("Estado retorno USUARIODAO: " + estado);
	    return estado;
	}


	@Override
	public boolean agregarCliente(Usuario usuario) {
		
		boolean estado=false;
		
		cn = new Conexion();
		cn.Open();	
		
		String query = "INSERT INTO usuarios (dni, cuil, nombre, apellido, sexo, celular, telefono, direccion, localidad, provincia, nacionalidad, fecha_nacimiento, email, usuario, contrasenia) " +
	               "VALUES ('" + usuario.getDni() + "','" + usuario.getCuil() + "','" + usuario.getNombre() + "','" + usuario.getApellido() + "','" +
	               usuario.getSexo() + "','" + usuario.getCelular() + "','" + usuario.getTelefono() + "','" + usuario.getDireccion() + "','" +
	               usuario.getLocalidad() + "','" + usuario.getProvincia() + "','" + usuario.getNacionalidad() + "','" +
	               usuario.getFechaNacimiento() + "','" + usuario.getEmail() + "','" + usuario.getUsuario() + "','" + usuario.getContrasena() + "')";
		System.out.println(query);
		try
		 {
			estado=cn.execute(query);
		 }
		catch(Exception e)
		{
			
			e.printStackTrace();
			
		}
		finally
		{
			cn.close();
		}
	
		return estado;
	}


	@Override
	public Usuario ObtenerUsuario(String usuario) {
	    cn = new Conexion();
	    cn.Open();
	    System.out.println("CONEXION ABIERTA OBTENER USUARIO");
	    Usuario u = new Usuario();
	    try {
	       
	    	String query = "SELECT usuarios.dni, usuarios.cuil, usuarios.nombre, usuarios.apellido, " +
	                "usuarios.sexo, usuarios.celular, usuarios.telefono, usuarios.direccion, " +
	                "usuarios.localidad, usuarios.provincia, usuarios.nacionalidad, " +
	                "usuarios.fecha_nacimiento, usuarios.email, usuarios.usuario, usuarios.contrasenia " +
	                "FROM usuarios WHERE usuarios.usuario = '" + usuario + "'";

	        ResultSet rs = cn.query(query);
	        System.out.println("QWERY" + rs);

	      
	        if (rs.next()) {
	            u.setDni(rs.getString("usuarios.dni"));
	            u.setCuil(rs.getString("usuarios.cuil"));
	            u.setNombre(rs.getString("usuarios.nombre"));
	            u.setApellido(rs.getString("usuarios.apellido"));
	            u.setSexo(rs.getString("usuarios.sexo"));
	            u.setCelular(rs.getString("usuarios.Celular"));
	            u.setTelefono(rs.getString("usuarios.Telefono"));
	            u.setDireccion(rs.getString("usuarios.direccion"));
	            u.setLocalidad(rs.getString("usuarios.localidad"));
	            u.setProvincia(rs.getString("usuarios.provincia"));
	            u.setNacionalidad(rs.getString("usuarios.nacionalidad"));
	            u.setFechaNacimiento(rs.getString("usuarios.fecha_nacimiento"));
	            u.setEmail(rs.getString("usuarios.email"));
	            u.setUsuario(rs.getString("usuarios.usuario"));
	            u.setContrasena(rs.getString("usuarios.contrasenia"));
	            System.out.println("QUERY RESULT: " + u.getDni());
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        cn.close();
	    }
	    return u;
	}
	
	
	
	
	@Override
	public Usuario ObtenerUsuarioPorDni(String DNI) {
	    cn = new Conexion();
	    cn.Open();
	    System.out.println("CONEXION ABIERTA OBTENER USUARIO X DNI");
	    Usuario u = new Usuario();
	    try {
	       
	    	String query = "SELECT usuarios.usuario, usuarios.contrasenia, usuarios.nombre, usuarios.apellido " +
	                "FROM usuarios WHERE usuarios.dni = '" + DNI + "'";

	        ResultSet rs = cn.query(query);
	        System.out.println("QWERY" + rs);

	      
	        if (rs.next()) {
	          
	            u.setUsuario(rs.getString("usuarios.usuario"));
	            u.setContrasena(rs.getString("usuarios.contrasenia"));
	            u.setNombre(rs.getString("usuarios.nombre"));
	            u.setApellido(rs.getString("usuarios.apellido"));
	            System.out.println("QUERY RESULT: " + u.getDni());
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        cn.close();
	    }
	    return u;
	}


	@Override
	public boolean editarUsuario(Usuario usuario) {
		boolean estado=true;

		cn = new Conexion();
		cn.Open();	

		String query = "UPDATE  usuarios SET dni='"+ usuario.getDni()+"',cuil='"+ usuario.getCuil()+"',nombre='"+ usuario.getNombre()+"',apellido='"+ usuario.getApellido()+"',sexo='"+ usuario.getSexo()+"',celular='"+ usuario.getCelular() +"',telefono='"+ usuario.getTelefono() +"',direccion='"+ usuario.getDireccion() + "',localidad='"+ usuario.getLocalidad() +"',provincia='"+ usuario.getProvincia() +"',nacionalidad='"+ usuario.getNacionalidad() +"', fecha_nacimiento='"+ usuario.getFechaNacimiento() +"', email='"+ usuario.getEmail()+"' WHERE dni='"+usuario.getDni()+"'";
		try
		 {
			estado=cn.execute(query);
		 }
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			cn.close();
		}
		
		return estado;
	}
	
	
	@Override
	public boolean editarContrasena(Usuario usuario) {
		boolean estado=true;

		cn = new Conexion();
		cn.Open();	

		String query = "UPDATE  usuarios SET contrasenia ='"+ usuario.getContrasena() +"' WHERE dni='"+ usuario.getDni()+"'";
		 System.out.println("query" + usuario.getContrasena());
		try
		 {
			estado=cn.execute(query);
		 }
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			cn.close();
		}
		
		 System.out.println("estado" + estado);
		return estado;
		
	}
	

	public ArrayList<Usuario> listarUsuarios(){
		ArrayList<Usuario> listaUsuarios = new ArrayList<Usuario>();
		String query="select * from usuarios where tipo_usuario_id=2"; 
		
		try {
			cn = new Conexion();
		    cn.Open();
		    System.out.println("CONEXION ABIERTA LISTAR USUARIO");
		    ResultSet rs = cn.query(query);
		    
		    while(rs.next()){
		    	Usuario u = new Usuario(); 
		    	u.setId(rs.getInt("usuarios.id"));
		    	u.setDni(rs.getString("usuarios.dni"));
		    	u.setCuil(rs.getString("usuarios.cuil"));
		    	u.setNombre(rs.getString("usuarios.nombre"));
		    	u.setApellido(rs.getString("usuarios.apellido"));
		    	u.setSexo(rs.getString("usuarios.sexo"));
		    	u.setCelular(rs.getString("usuarios.Celular"));
		    	u.setTelefono(rs.getString("usuarios.Telefono"));
		    	u.setDireccion(rs.getString("usuarios.direccion"));
		    	u.setLocalidad(rs.getString("usuarios.localidad"));
		    	u.setProvincia(rs.getString("usuarios.provincia"));
		    	u.setNacionalidad(rs.getString("usuarios.nacionalidad"));
		    	u.setFechaNacimiento(rs.getString("usuarios.fecha_nacimiento"));
		    	u.setEmail(rs.getString("usuarios.email"));
		    	u.setUsuario(rs.getString("usuarios.usuario"));
		    	u.setContrasena(rs.getString("usuarios.contrasenia"));
		    	u.setHabilitado(rs.getInt("usuarios.habilitado"));
		    	listaUsuarios.add(u);
		    }
		
		}catch (Exception e){
			e.printStackTrace();
        
		}finally{
			cn.close();
		}
		return listaUsuarios;
	}



	@Override
	public boolean eliminarUsuario(Usuario usuario) {
		boolean estado=true;

		cn = new Conexion();
		cn.Open();	

		String query = "UPDATE  usuarios SET habilitado ='"+ usuario.getHabilitado() +"' WHERE dni='"+ usuario.getDni()+"'";
		System.out.println("query" + usuario.getContrasena());
		try
		 {
			estado=cn.execute(query);
		 }
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			cn.close();
		}
		
		return estado;
		
	}

}
	



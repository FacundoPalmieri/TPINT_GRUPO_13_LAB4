package datosimpl;
import datos.UsuarioDao;
import entidad.Direccion;
import entidad.Persona;
import entidad.Usuario;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

public class UsuarioDaoImpl implements UsuarioDao{
	private Conexion cn;
	
	@Override
	public int validarLogin(String usuario, String contrasenia) {
	    int esCliente = 0;
	    Conexion cn = new Conexion();
	    String query = "SELECT usuario, pass, tipo_usuario_id FROM Usuarios WHERE usuario=? and pass=?";
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
	        	esCliente = 2;
	        	if(rs.getInt("tipo_usuario_id")==1) {
	            	esCliente=1;
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

	    System.out.println("Estado retorno ValidadLogin DAO: " + esCliente);
	    return esCliente;
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
	public boolean agregarCliente(Usuario usuario, Persona persona, Direccion direccion) {
	    boolean estado = false;
	    Conexion cn = null;
	    String query1 = "INSERT INTO direcciones (calle,numero,piso, departamento, localidad_id) " +
	                    " VALUES (?,?,?,?,?)";

	    String query2 = "INSERT INTO personas (dni, cuil, nombre, apellido, sexo, celular, telefono, Direccion_id, nacionalidad, fecha_nacimiento, email) " +
	                    "VALUES (?, ?, ?, ?, ?, ?, ?, ? ,?, ?, ?)";

	    String query3 = "INSERT INTO usuarios (usuario,pass,persona_dni) " +
	                    " VALUES (?,?,?)";

	    try {
	        cn = new Conexion();
	        cn.Open();
	        cn.setAutoCommit(false);
	        System.out.println("Auto-commit seteado: " + cn.getConexion().getAutoCommit());

	        // Query 1: direcci�n
	        PreparedStatement preparedStatement1 = cn.prepareStatement(query1, Statement.RETURN_GENERATED_KEYS);
	        preparedStatement1.setString(1, direccion.getCalle());
	        preparedStatement1.setInt(2, direccion.getAltura());
	        preparedStatement1.setString(3, direccion.getPiso());
	        preparedStatement1.setString(4, direccion.getDepartamento());
	        preparedStatement1.setInt(5, direccion.getLocalidad_id());

	        try {
	            preparedStatement1.executeUpdate();
	            ResultSet generatedKeys = preparedStatement1.getGeneratedKeys();
	            if (generatedKeys.next()) {
	                int direccionId = generatedKeys.getInt(1);
	                persona.setDireccion_id(direccionId);
	                System.out.println("ID de direcci�n generado: " + direccionId);
	            } else {
	                throw new Exception("No se gener� el ID de direcci�n.");
	            }
	        } catch (Exception e) {
	            System.out.println("Error en la consulta 1: " + e.getMessage());
	            e.printStackTrace();
	            cn.rollback();
	            System.out.println("Rollback realizado en consulta 1");
	            return false;
	        }

	        // Query 2: persona
	        PreparedStatement preparedStatement2 = cn.prepareStatement(query2);
	        preparedStatement2.setString(1, persona.getDni());
	        preparedStatement2.setString(2, persona.getCuil());
	        preparedStatement2.setString(3, persona.getNombre());
	        preparedStatement2.setString(4, persona.getApellido());
	        preparedStatement2.setString(5, persona.getSexo());
	        preparedStatement2.setString(6, persona.getCelular());
	        preparedStatement2.setString(7, persona.getTelefono());
	        preparedStatement2.setInt(8, persona.getDireccion_id());
	        preparedStatement2.setString(9, persona.getNacionalidad());
	        preparedStatement2.setDate(10, java.sql.Date.valueOf(persona.getFechaNacimiento()));
	        preparedStatement2.setString(11, persona.getEmail());

	        try {
	            preparedStatement2.executeUpdate();
	            System.out.println("Consulta 2 ejecutada correctamente");
	        } catch (Exception e) {
	            System.out.println("Error en la consulta 2: " + e.getMessage());
	            e.printStackTrace();
	            cn.rollback();
	            System.out.println("Rollback realizado en consulta 2");
	            return false;
	        }

	        // Query 3: usuario
	        PreparedStatement preparedStatement3 = cn.prepareStatement(query3);
	        preparedStatement3.setString(1, usuario.getUsuario());
	        preparedStatement3.setString(2, usuario.getPass());
	        preparedStatement3.setString(3, usuario.getPersona_dni());

	        try {
	            preparedStatement3.executeUpdate();
	            System.out.println("Consulta 3 ejecutada correctamente");
	        } catch (Exception e) {
	            System.out.println("Error en la consulta 3: " + e.getMessage());
	            e.printStackTrace();
	            cn.rollback();
	            System.out.println("Rollback realizado en consulta 3");
	            return false;
	        }

	        cn.commit();
	        System.out.println("Commit realizado");
	        estado = true;

	    } catch (Exception e) {
	        try {
	            if (cn != null) {
	                cn.rollback();
	                System.out.println("Rollback realizado en catch principal");
	            }
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }
	        e.printStackTrace();
	    } finally {
	        if (cn != null) {
	            try {
	                if (!cn.getConexion().getAutoCommit()) {
	                    cn.setAutoCommit(true); 
	                    System.out.println("Auto-commit restablecido a: " + cn.getConexion().getAutoCommit());
	                }
	                cn.close();
	                System.out.println("Conexi�n cerrada");
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	        }
	    }

	    System.out.println("ESTADO DAO AGREGAR USUARIO" + estado);
	    return estado;
	}




	@Override
	public Usuario ObtenerUsuario(String usuario) {
	    cn = new Conexion();
	    cn.Open();
	    System.out.println("CONEXION ABIERTA OBTENER USUARIO");
	    Usuario u = new Usuario();
	    try {
	       
	    	String query = "    SELECT u.usuario, u.tipo_usuario_id "
	    		        	+ "	FROM usuarios u "
	    		        	+ " INNER JOIN  Peronsas p ON p.dni = u.persona_dni"
	    		        	+ " WHERE u.usuario = '" + usuario + "'";

	        ResultSet rs = cn.query(query);
	        System.out.println("QWERY" + rs);

	      
	        if (rs.next()) {
	            u.setUsuario(rs.getString("u.usuario"));
	            u.setTipoUsuarioId(Integer.parseInt(rs.getString("u.tipo_usuario_id")));

	            
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        cn.close();
	    }
	    return u;
	}
	
	

	@Override
	public Persona ObtenerCliente(String usuario) {
	    cn = new Conexion();
	    cn.Open();
	    System.out.println("CONEXION ABIERTA OBTENER USUARIO");
	    Persona p = new Persona(); 
	    try {
	       
	    	String query = "SELECT p.nombre, p.apellido,p.dni,p.cuil,p.celular,p.telefono,.direccion_id, p.nacionalidad,p.fecha_nacimiento,p.email"
	    			    + " FROM  personas p "
	    			    + " INNER JOIN usuario u ON p.dni = u.persona_dni"
	    			    + " WHERE u.usuario = '" + usuario +"'";
	    			
	    		

	        ResultSet rs = cn.query(query);
	        System.out.println("QWERY" + rs);

	      
	        if (rs.next()) {      
	            p.setNombre(rs.getString("p.nombre"));
	            p.setApellido(rs.getString("p.apellido"));
	            p.setDni(rs.getString("p.dni"));
	            p.setCuil(rs.getString("p.cuil"));
	            p.setCelular(rs.getString("p.celular"));
	            p.setTelefono(rs.getString("p.telefono"));
	            p.setDireccion_id(rs.getInt("p.direccion_id"));
	            p.setNacionalidad(rs.getString("p.nacionalidad"));
	            
	            Date sqlDate = rs.getDate("p.fecha_nacimiento");
	            if (sqlDate != null) {
	                LocalDate localDate = sqlDate.toLocalDate();
	                p.setFechaNacimiento(localDate);
	            }
	            
	            p.setEmail(rs.getString("p.email"));

	            
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        cn.close();
	    }
	    return p;
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
	



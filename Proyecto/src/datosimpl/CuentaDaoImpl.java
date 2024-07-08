package datosimpl;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;

import datos.CuentaDao;
import entidad.Cuenta;
import entidad.TipoCuenta;

public class CuentaDaoImpl implements CuentaDao {
	
	private Conexion cn;
	
	
	
	public Conexion getCn() {
		return cn;
	}

	public void setCn(Conexion cn) {
		this.cn = cn;
	}



	@Override
	public int ValidarCantidad(String DNI) {
		int cantidadCuentas = 0;
		Conexion cn = new Conexion();
		
		String query = "SELECT COUNT(*) AS Cantidadcuentas FROM CUENTAS where cliente_dni = ?";
		try {
			cn.Open();
			 PreparedStatement preparedStatement = cn.prepareStatement(query);
		     preparedStatement.setString(1, DNI);
		     
		     ResultSet rs = preparedStatement.executeQuery();
		     
		     
		     if (rs.next()) {
		            cantidadCuentas = rs.getInt("Cantidadcuentas");
		      }


		        rs.close();
		        preparedStatement.close();
			
			
			
		} catch (Exception e) {
			 e.printStackTrace();
		}
		
		finally
		{
			cn.close();
		}
		
		 System.out.println("CUENTA DAO CANTIDAD CUENTAS : " + cantidadCuentas);
		return cantidadCuentas;
		
		
	}


	@Override
	public int CrearCuenta(String DNI, int TipoCuenta) {
		int estado = 0;
		
		Conexion cn = new Conexion();
		
		String query ="INSERT INTO cuentas (cliente_dni, fecha_creacion,tipo_cuenta_id) VALUES (?,?,?)";
		
		try {
			cn.Open();
			PreparedStatement preparedStatement = cn.prepareStatement(query);
			
			
		    preparedStatement.setString(1, DNI);
		    
		    Date fechaActual = Date.valueOf(LocalDate.now());
		    preparedStatement.setDate(2,fechaActual );
		    
		    
		    preparedStatement.setInt(3, TipoCuenta);
		    
		    estado = preparedStatement.executeUpdate();
			
		  
		    return estado;
			
			
		} catch (Exception e) {
			 e.printStackTrace();
		}
		finally
		{
			cn.close();
		}
		
		return estado;
	}

	@Override
	public int buscarNCuenta(String DNI) {
		
		int nCuenta=0;
		
		Conexion cn = new Conexion();
		
		System.out.println("DNI CLIENTE A BUSCAR: " + DNI);
		String query ="SELECT max(numero_cuenta) FROM cuentas WHERE cliente_dni = ?";
		System.out.println("query de buscar cliente: " + query);
		
		try {
			cn.Open();
			PreparedStatement preparedStatement = cn.prepareStatement(query);
			preparedStatement.setString(1, DNI);
			
			ResultSet rs = preparedStatement.executeQuery();
		     
		     
		     if (rs.next()) {
		    	 nCuenta = rs.getInt(1);
		      }
		     System.out.println("nCuenta Encontrada " + nCuenta);


		        rs.close();
		        preparedStatement.close();
	
			
		} catch (Exception e) {
			 e.printStackTrace();
		}
		finally
		{
			cn.close();
		}
		
		
		return nCuenta;
	}

	@Override
	public ArrayList<Cuenta> obtenerCuentasPorDNI(String DNI) {
		  ArrayList<Cuenta> cuentas = new ArrayList<>();
		    String query = "select cuentas.numero_cuenta, cuentas.tipo_cuenta_id, cuentas.saldo, tipocuenta.id, tipocuenta.descripcion "
		    			  +"FROM cuentas "
		    			  +"INNER JOIN tipocuenta ON cuentas.tipo_cuenta_id = tipocuenta.id "
		    			  +"WHERE cliente_dni = ?";

		    try {
		        cn = new Conexion();
		        cn.Open();
		        System.out.println("CONEXIÓN ABIERTA - OBTENER CUENTAS POR DNI");
		        PreparedStatement preparedStatement = cn.prepareStatement(query);
		        preparedStatement.setString(1, DNI);
		        ResultSet rs = preparedStatement.executeQuery();

		        while (rs.next()) {
		            Cuenta cuenta = new Cuenta();
		            TipoCuenta tipoCuenta = new TipoCuenta();
		            
		            cuenta.setNumeroCuenta(rs.getInt("cuentas.numero_cuenta"));            
		            tipoCuenta.setId(rs.getInt("tipocuenta.id"));
		            tipoCuenta.setDescripcion(rs.getString("tipocuenta.Descripcion"));
		            
		            //Seteo el ID de CUENTA con el objeto TipoCuenta
		            cuenta.setIdTipoCuenta(tipoCuenta);
		            cuenta.setSaldo(rs.getFloat("cuentas.saldo"));

		            cuentas.add(cuenta);
		        }

		    } catch (Exception e) {
		        e.printStackTrace();
		    } finally {
		        cn.close();
		    }
		    
		    return cuentas;
		}

	}
	




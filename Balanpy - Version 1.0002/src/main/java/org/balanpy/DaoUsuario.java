package org.balanpy;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
public class DaoUsuario {
				
    //Generar un atributo connection
    //Es común o única para todo tipo de objetos de la clase.
	private Connection con = null;
	public static DaoUsuario instance = null;
	
	public DaoUsuario() throws SQLException {
		con = ConexionBD.getConnection();
	}
	
	/**
	 * Método estático que crea la instancia
	 * Devuelve un objeto de tipo DaoVacio
	 * @return
	 * @throws SQLException
	 */
	public static DaoUsuario getInstance() throws SQLException {
		
		//Se llama asi mismo, comprueba si el atributo es nulo.
		if(instance == null) {
			
			instance = new DaoUsuario();
		}
		return instance;
	}
		

	/**
	 * Método que comprueba si existe la tabla usuario en caso de no existir la crea.
	 * @throws SQLException
	 */
    public void crearTabla() throws SQLException {
    	
    	try {
    		String table = "CREATE TABLE IF NOT EXISTS usuario (" + 
					   "email_usuario NVARCHAR(150) NOT NULL PRIMARY KEY," +
					   "dni NVARCHAR(50) NOT NULL," + 
					   "nombre NVARCHAR(150) NOT NULL," +
					   "edad INT NOT NULL ," +
					   "telefono NVARCHAR(50) NOT NULL," +
					   "sexo NVARCHAR(10) NOT NULL," +
					   "direccion NVARCHAR(150)NOT NULL" + ")";

		     Statement stmt = con.createStatement();
		     stmt.executeUpdate(table);
		     stmt.close();
    	
    	}catch (Exception e) {
			System.out.println("Error when executing query" + e.toString());
    	}
    }
	
	
	/**
	 * Método que se comunica con MySQL para insertar el objeto.
	 * @param s Objeto usuario a insertar
	 * @throws SQLException
	 */
	public void daoInsert(UsuarioImpl u) throws SQLException {		
		//Una interrogante por cada campo, estos deben coincidir con los nombres de cada atributo.
		PreparedStatement ps  = con.prepareStatement("INSERT INTO usuario (email_usuario, dni, nombre, edad, telefono, sexo, direccion) VALUES (?,?,?,?,?,?,?)");
	
		//Decir que son las ?
		ps.setString(1, u.getEmail_usuario());
		ps.setString(2, u.getDNI());
		ps.setString(3, u.getNombre());
		ps.setInt(4,    u.getEdad());
		ps.setString(5, u.getTelefono());
		ps.setString(6, u.getSexo());
		ps.setString(7, u.getDireccion());
		
		//2 método
		//Devuelve insert, update o delete, executeQuery devuelve el select 
		ps.executeUpdate();
		ps.close();
		System.out.println("email_usuario" + " " + u.getEmail_usuario()+ " " + "dni" + " " + u.getDNI() + " " + "nombre" + " " + u.getNombre()+
				" " + u.getNombre() + " " + "edad" + " " + u.getEdad() + " " + "telefono" + " " + u.getTelefono()+
				" " + "sexo" + " " + u.getSexo()+ " " + "direccion" + " " + u.getDireccion());
	}
	
	
	/**
	 * Método que se comunica con MySQL para actualizar el objeto.
	 * @param s
	 * @throws SQLException
	 */
	public void daoUpdate(UsuarioImpl u) throws SQLException {
		
			//Maneja la query de sql
			PreparedStatement ps = con.prepareStatement("UPDATE usuario SET dni = ?, nombre = ?, edad = ?, telefono = ?, sexo = ?, direccion = ? WHERE email_usuario = ?");
			
			ps.setString(1, u.getDNI());
			ps.setString(2, u.getNombre());
			ps.setInt(3,    u.getEdad());
			ps.setString(4, u.getTelefono());
			ps.setString(5, u.getSexo());
			ps.setString(6, u.getDireccion());
			ps.setString(7, u.getEmail_usuario());
			
			//2 método
			//Devuelve insert, update o delete, executeQuery devuelve el select 
			ps.executeUpdate();
			System.out.println("Usuario actualizado correctamente");
			ps.close();
		
	}
	
	/**
	 * Método que se comunica con MySQL para eliminar el objeto Usuario
	 * @param u
	 * @throws SQLException
	 */
	public void daoDelete(UsuarioImpl u) throws SQLException {
	
		PreparedStatement ps = con.prepareStatement("DELETE FROM usuario WHERE email_usuario=?");
		ps.setString(1, u.getEmail_usuario()); 
		ps.executeUpdate();
		System.out.println("Usuario eliminado correctamente");
		ps.close();
		
	}


}

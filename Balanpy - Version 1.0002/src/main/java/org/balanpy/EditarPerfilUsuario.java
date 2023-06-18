package org.balanpy;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class EditarPerfilUsuario {
	private static final String SCREEN = "/EditarPerfilUsuario.fxml";
	private Usuario usuario = UsuarioImpl.getInstance();
	
	//**** BD ****
	private Connection con = null;
	public static DaoUsuario instance = null;

	public EditarPerfilUsuario() throws SQLException {
		
		con = ConexionBD.getConnection();	
	}	
	//**** BD ****

	@FXML
	TextField nombre;

	@FXML
	TextField dni;

	@FXML
	TextField telefono;

	@FXML
	TextField edad;

	@FXML
	TextField email_usuario;

	@FXML
	ChoiceBox<String> sexo;

	@FXML
	TextField direccion;

	public void initialize() {
		sexo.getItems().add("Masculino");
		sexo.getItems().add("Femenino");

		if (!usuario.isValid()) {
			return;
		}
		nombre.setText(usuario.getNombre());
		dni.setText(usuario.getDNI());
		email_usuario.setText(usuario.getEmail_usuario());
		sexo.setValue(usuario.getSexo());
		edad.setText(usuario.getEdad().toString());
		telefono.setText(usuario.getTelefono());
		direccion.setText(usuario.getDireccion());
	}
	
	/**
	 * Boton para guardar el registro de un usuario.
	 * @param event
	 * @throws StreamReadException
	 * @throws DatabindException
	 * @throws IOException
	 */
	public void guardar(ActionEvent event) throws StreamReadException, DatabindException, IOException {
		PreparedStatement ps = null;
		
		try {
			usuario.setNombre(nombre.getText());
			usuario.setDNI(dni.getText());
			usuario.setEmail_usuario(email_usuario.getText());
			usuario.setSexo(sexo.getValue());
			usuario.setEdad(Integer.parseInt(edad.getText()));
			usuario.setTelefono(telefono.getText());
			usuario.setDireccion(direccion.getText());
			
			usuario.save();
			
			ps = con.prepareStatement("SELECT 1 FROM usuario WHERE email_usuario=?");
	        
			ps.setString(1, usuario.getEmail_usuario());
			ResultSet rs = ps.executeQuery();
	        
			//A través del método next() avanzamos al siguiente registro del objeto ResultSet, 
			//Si la consulta ha devuelto al menos una fila, el método next() devolverá true, por lo tanto se ejecutará el método update().
			 if (rs.next()) {
				
				 email_usuario.setEditable(false);
	             // Si el email ya existe, actualizamos el usuario.
	             usuario.update();
	         } else {
	             // Si el email no existe, agregamos el usuario.
	             usuario.insert();
	         }
			
			Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			BalanpyScreen.loadScene(stage, "/PortadaAplicacion.fxml", SCREEN);
		} catch (Exception e) {
			Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			BalanpyScreen.quickPopUp(stage, e.getMessage());
			usuario = usuario.reload();
		} finally {
	        if (ps != null) {
	            try {
	                ps.close();//Cerramos la conexion
	            } catch (SQLException e) {
	            	e.printStackTrace();
	            }
	        }
	    }
	}
		
	//********************** BD ***********************
	
	/**
	 * Método que se comunica con MySQL para agregar el objeto.
	 * @param s Objeto usuario a insertar
	 * @throws SQLException
	 */
	public void daoInsert(UsuarioImpl u) throws SQLException {
		PreparedStatement ps = null;
	    try {
				//Una interrogante por cada campo, estos deben coincidir con los nombres de cada atributo.
		        ps = con.prepareStatement("INSERT INTO usuario (email_usuario, dni, nombre, edad, telefono, sexo, direccion) VALUES (?,?,?,?,?,?,?)");
		      
		        //Decir que son las ?
		        ps.setString(1, u.getEmail_usuario());
		        ps.setString(2, u.getDNI());
		        ps.setString(3, u.getNombre());
		        ps.setInt(4, u.getEdad());
		        ps.setString(5, u.getTelefono());
		        ps.setString(6, u.getSexo());
		        ps.setString(7, u.getDireccion());

		        ps.executeUpdate();
		        System.out.println("Usuario insertado correctamente");
		        System.out.println("email_usuario" + " " + u.getEmail_usuario()+ " " + "dni" + " " + u.getDNI() + " " + "nombre" + " " + u.getNombre()+
						" " + u.getNombre() + " " + "edad" + " " + u.getEdad() + " " + "telefono" + " " + u.getTelefono()+
						" " + "sexo" + " " + u.getSexo()+ " " + "direccion" + " " + u.getDireccion());
				
		    } catch (SQLException e) {
		        e.printStackTrace();
		    } finally {
		        if (ps != null) {
		            try {
		                ps.close();//Cerramos la conexion 
		            } catch (SQLException e) {
		                e.printStackTrace();
		            }
		        }
		    }
	}
	
	
	/**
	 * Método que se comunica con MySQL para actualizar el objeto Usuario.
	 * @param s
	 * @throws SQLException
	 */
	public void daoUpdate(UsuarioImpl u) throws SQLException {
		PreparedStatement ps = null;
		    try {
		    	//Maneja la query de sql
				ps = con.prepareStatement("UPDATE usuario SET dni = ?, nombre = ?, edad = ?, telefono = ?, sexo = ?, direccion = ? WHERE email_usuario = ?");
						        
		        ps.setString(1, u.getDNI());
		        ps.setString(2, u.getNombre());
		        ps.setInt(3, u.getEdad());
		        ps.setString(4, u.getTelefono());
		        ps.setString(5, u.getSexo());
		        ps.setString(6, u.getDireccion());
		        ps.setString(7, u.getEmail_usuario());
		        
		        ps.executeUpdate();
		        System.out.println("Usuario actualizado correctamente");
		    } catch (SQLException e) {
		        e.printStackTrace();
		    } finally {
		        if (ps != null) {
		            try {
		                ps.close();
		            } catch (SQLException e) {
		                e.printStackTrace();
		            }
		        }
		    }
	}
	
	/**
	 * Método que se comunica con MySQL para eliminar el objeto Usuario.
	 * @param u
	 * @throws SQLException
	 */
	public void daoDelete(UsuarioImpl u) throws SQLException {
		
		PreparedStatement ps = null;

	    try {
	        ps = con.prepareStatement("DELETE FROM usuario WHERE email_usuario=?");
	        ps.setString(1, u.getEmail_usuario());
	        ps.executeUpdate();
	        System.out.println("Usuario eliminado correctamente");
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        if (ps != null) {
	            try {
	                ps.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	    }
	}
	
	//*********************** BD **********************
	
	/**
	 * Boton para eliminar el registro de un usuarioasi como todos los datos asociados a su perfil.
	 * @param event
	 * @throws Exception
	 */
	public void delete(ActionEvent event) throws Exception {
		//Mascotas.deleteAll();
		usuario.delete();//*** BD 	
		System.out.println("Usuario eliminado correctamente");
		
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		BalanpyScreen.loadScene(stage, "/PantallaInicio.fxml", SCREEN);
	}
	
	/**
	 * Bóton para regresar a la pantalla anterior
	 * @param event
	 * @throws IOException
	 */
	public void atras(ActionEvent event) throws IOException {
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		BalanpyScreen.back(stage, SCREEN);
	}
	
	public void HandleDone(MouseEvent event) throws Exception {
		System.out.println("Está funcionando crack");
	}

}

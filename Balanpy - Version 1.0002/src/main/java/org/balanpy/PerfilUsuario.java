package org.balanpy;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class PerfilUsuario {
	private static final String SCREEN = "/PerfilUsuario.fxml";

	@FXML
	private Label nombre;

	@FXML
	private Label edad;

	@FXML
	private Label telefono;

	@FXML
	private Label email_usuario;

	@FXML
	private Label sexo;

	@FXML
	private Label direccion;

	public void initialize() throws SQLException {

		Usuario usuario = UsuarioImpl.getInstance();

		nombre.setText(usuario.getNombre());
		edad.setText(usuario.getEdad().toString());
		telefono.setText(usuario.getTelefono());
		email_usuario.setText(usuario.getEmail_usuario());
		sexo.setText(usuario.getSexo());
		direccion.setText(usuario.getDireccion());

	}

	/**
	 * Boton que redirige al usaurio a la pantalla EditarPerfilUsuario.
	 * @param event
	 * @throws IOException
	 */
	public void editarPerfil(ActionEvent event) throws IOException {
		email_usuario.setDisable(false);
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		BalanpyScreen.loadScene(stage, "/EditarPerfilUsuario.fxml", SCREEN);
		
	}
	
	/**
	 * Boton que permite mostrar al usuario los terminos y condiciones de Balanpy.
	 * @param event
	 * @throws IOException
	 * @throws URISyntaxException
	 */
	public void terminosCondiciones(ActionEvent event) throws IOException, URISyntaxException {
		LecturaArchivosGen.openFile(Paths.get(getClass().getResource("/terminos-condiciones.txt").toURI()));
	}

	public void HandleDone(MouseEvent event) throws Exception {

		System.out.println("Está funcionando crack");

	}
}

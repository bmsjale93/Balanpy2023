package org.balanpy;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ConfiguracionHigiene {
	@FXML
	ImageView fotoMascota1;
	@FXML
	ImageView fotoMascota2;
	@FXML
	ImageView fotoMascota3;
	
	@FXML
	TextField prox_bano;
	@FXML
	TextField prox_cepillado;
	@FXML
	TextField prox_desp_int;
	@FXML
	TextField prox_desp_ext;
	@FXML
	TextField frecuencia_b;
	@FXML
	TextField frecuencia_c;
	@FXML
	TextField frecuencia_int;
	@FXML
	TextField frecuencia_ext;
	@FXML
	TextField producto_nr;
		

	public void HandleDone(MouseEvent event) throws Exception {

		System.out.println("Está funcionando amigo");

	}
}

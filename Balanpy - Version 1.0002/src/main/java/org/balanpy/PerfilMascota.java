package org.balanpy;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.chart.LineChart;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class PerfilMascota {
	private static final String SCREEN = "/PerfilMascota.fxml";
	@FXML
	private Text color;

	@FXML
	private Text edad;

	@FXML
	private Text f_nacimiento;

	@FXML
	private ImageView fotoMascota;

	@FXML
	private Text g_sanguineo;

	@FXML
	private Text nombre;

	@FXML
	private LineChart<?, ?> pulsaciones;

	@FXML
	private Text raza;

	@FXML
	private Text sexo;

	@FXML
	private Text esterilizado;

	@FXML
	private LineChart<String, Double> temperaturaDiaria;

	@FXML
	private LineChart<String, Integer> pulsacionesDiaria;

	private static Mascota mascota;
	public static void setMascota(Mascota m) {
		mascota = m;
	}

	public void initialize() {
		color.setText(mascota.getColor());
		edad.setText(mascota.getEdad().toString() + " años");
		f_nacimiento.setText(mascota.getF_nacimiento());
		g_sanguineo.setText(mascota.getG_sanguineo());
		nombre.setText(mascota.getNombre());
		raza.setText(mascota.getRaza());
		sexo.setText(mascota.getSexo());

		if (mascota.getEsterilizado()) {
			esterilizado.setText("Si");
		} else {
			esterilizado.setText("No");
		}

		try {
			Path imagePath = Paths.get(Configuracion.getRootDir(), mascota.getUUID().toString(), "profile.png");
			fotoMascota.setImage(new Image(imagePath.toUri().toURL().toExternalForm()));
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		DatoPulsaciones pulsaciones = new DatoPulsaciones(mascota);
		pulsaciones.fillGraph(pulsacionesDiaria);

		DatoTemperaturas temperaturas = new DatoTemperaturas(mascota);
		temperaturas.fillGraph(temperaturaDiaria);
	}

	public void configurar(ActionEvent event) throws IOException {
		ConfiguracionMascota.setMascota(mascota);
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		BalanpyScreen.loadScene(stage, "/ConfiguracionMascota.fxml", SCREEN);
	}

}

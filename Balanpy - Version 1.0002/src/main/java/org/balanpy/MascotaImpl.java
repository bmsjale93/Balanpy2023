package org.balanpy;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class MascotaImpl implements Mascota {

	private static final String MACHO = "macho";
	private static final String HEMBRA = "hembra";
	private static final String DEA11 = "DEA11";
	private static final String DEA12 = "DEA12";
	private static final String DEA3 = "DEA3";
	private static final String DEA4 = "DEA4";
	private static final String DEA5 = "DEA5";
	private static final String DEA6 = "DEA6";
	private static final String DEA7 = "DEA7";
	private static final String DEA8 = "DEA8";

	// ------------------------------ MASCOTA -----------------------------------
	@JsonProperty(value = "UUID")
	private UUID uuid;

	@JsonProperty(value = "f_nacimiento")
	private String f_nacimiento;

	@JsonProperty(value = "CHIP")
	private String n_chip = "";

	private String especie = "";
	
	private String raza = "";

	private String sexo = "";

	private int peso = 0;
	
	private double peso_kilos = 0;
	
	private String  peso_fecha = "01/01/2020"; 

	private String color = "";

	@JsonProperty(value = "g_sanguineo")
	private String g_sanguineo = "";

	private String nombre = "";

	private Boolean esterilizado = false;

	private ArrayList<String> alergias = new ArrayList<>();

	private ArrayList<String> enfermedades = new ArrayList<>();

	private Map<String, String> vacunas = new HashMap<>();

	// -----------------------------------------------------------------------------

	private HigieneImpl higiene = new HigieneImpl();

	@Override
	public HigieneImpl getHigiene() {
		return higiene;
	}

	// -----------------------------------------------------------------------------

	public MascotaImpl() {

	}

	// --------------- GETTERS MASCOTAS -------------------------------------------

	@Override
	public UUID getUUID() {
		return uuid;
	}

	@Override
	public String getF_nacimiento() {
		return f_nacimiento;
	}
	

	@Override
	public String getN_chip() {
		return n_chip;
	}
	

	public String getEspecie() {
		return especie;
	}

	@Override
	public String getRaza() {
		return raza;
	}

	@Override
	public String getSexo() {
		return sexo;
	}

	@Override
	public int getPeso() {
		return peso;
	}

	@Override
	public String getColor() {
		return color;
	}

	@Override
	public String getG_sanguineo() {
		return g_sanguineo;
	}

	@Override
	public String getNombre() {
		return nombre;
	}

	@Override
	public Boolean getEsterilizado() {
		return esterilizado;
	}

	@Override
	public ArrayList<String> getAlergias() {
		return alergias;
	}

	@Override
	public ArrayList<String> getEnfermedades() {
		return enfermedades;
	}

	@Override
	public Map<String, String> getVacuna() {
		return vacunas;
	}

	@Override
	@JsonIgnore
	public Integer getEdad() {
		DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate now = LocalDate.now();
		LocalDate nacimiento = LocalDate.parse(f_nacimiento, df);

		return Period.between(nacimiento, now).getYears();
	}
	
	
	//-----------------------------
	
    public double getPeso_kilos() {
		return peso_kilos;
	}

	public LocalDate getPeso_fecha() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate fecha = LocalDate.parse(peso_fecha, formatter);
		return fecha;
	}
	
	// --------------- SETTERS MASCOTAS -------------------------------------------

	@Override
	public void setUUID(UUID uuid) {
		this.uuid = uuid;
	}

	@Override
	public void setF_nacimiento(String f_nacimiento) {
		this.f_nacimiento = f_nacimiento;
	}

	@Override
	public void setN_chip(String n_chip) {
		this.n_chip = n_chip;
	}

	
	public void setEspecie(String especie) {
		this.especie = especie;
	}

	@Override
	public void setRaza(String raza) {
		this.raza = raza;
	}

	@Override
	public void setSexo(String sexo) {
		if (!isValidSexo(sexo)) {
			return;
		}
		this.sexo = sexo;
	}

	@Override
	public void setPeso(int peso) {
		this.peso = peso;
	}
	
	public void setPeso_kilos(double peso_kilos) {
		this.peso_kilos = peso_kilos;
	}
	
	public void setPeso_fecha(String peso_fecha) {
		this.peso_fecha = peso_fecha;
	}

	@Override
	public void setColor(String color) {
		this.color = color;
	}

	@Override
	public void setG_sanguineo(String g_sanguineo) {
		this.g_sanguineo = g_sanguineo;
	}

	@Override
	public void setNombre(String nombre) {
		if (nombre.isEmpty()) {
			return;
		}
		this.nombre = nombre;
	}
	

	@Override
	public void setEsterilizado(Boolean e) {
		esterilizado = e;
	}

	@Override
	public void setAlergias(ArrayList<String> alergias) {
		this.alergias = alergias;
	}

	@Override
	public void setEnfermedades(ArrayList<String> enfermedades) {
		this.enfermedades = enfermedades;
	}

	@Override
	public void setVacunas(Map<String, String> vacunas) {
		this.vacunas = vacunas;
	}

	// ----------------------------------------------------------------------------

	public static boolean isValidSexo(String sexo) {
		String sexoCaseInsensitive = sexo.toLowerCase();

		return sexoCaseInsensitive.equals(MACHO) || sexoCaseInsensitive.equals(HEMBRA);
	}

	public static boolean isValidGrupoSanguineo(String g_sanguineo) {

		return g_sanguineo.equals(DEA11) || g_sanguineo.equals(DEA12) || g_sanguineo.equals(DEA3)
				|| g_sanguineo.equals(DEA4) || g_sanguineo.equals(DEA5) || g_sanguineo.equals(DEA6)
				|| g_sanguineo.equals(DEA7) || g_sanguineo.equals(DEA8);
	}

}
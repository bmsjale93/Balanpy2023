package org.balanpy;

import java.util.ArrayList;
import java.util.Map;
import java.util.UUID;

public interface Mascota {

	// GETTERS

	public UUID getUUID();

	public String getF_nacimiento();

	public String getN_chip();

	public String getRaza();

	public String getSexo();

	public int getPeso();

	public String getColor();

	public String getG_sanguineo();

	public String getNombre();

	public Boolean getEsterilizado();

	public Integer getEdad();

	// SETTERS

	public void setUUID(UUID uuid);

	public void setF_nacimiento(String f_nacimiento);

	public void setN_chip(String n_chip);

	public void setRaza(String raza);

	public void setSexo(String sexo);

	public void setPeso(int peso);

	public void setColor(String color);

	public void setG_sanguineo(String g_sanguineo);

	public void setNombre(String nombre);

	public void setEsterilizado(Boolean esterilizado);

	// ********************** Alergias *****************************

	public ArrayList<String> getAlergias(); // GETTER

	public void setAlergias(ArrayList<String> alergias); // SETTER

	// ******************** Enfermedades ***************************

	public ArrayList<String> getEnfermedades(); // GETTER

	public void setEnfermedades(ArrayList<String> enfermedades); // SETTER

	// ********************** Vacunas ******************************

	public Map<String, String> getVacuna(); // GETTER

	public void setVacunas(Map<String, String> vacunas); // SETTER

	public HigieneImpl getHigiene();

}
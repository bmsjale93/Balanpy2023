package org.balanpy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

public class HigieneImpl implements Higiene {

	@JsonProperty(value = "ultimo_bano")
	private String ult_bano;

	@JsonProperty(value = "ultimo_cepillado")
	private String ult_cepillado;

	@JsonProperty(value = "ultimo_desparasitado")
	private Map<String, String> desparasitado = new HashMap<>();

	@JsonProperty(value = "producto_nr")
	private ArrayList<String> producto_nr = new ArrayList<>();

	public HigieneImpl() {

	}

	// --------------- GETTERS HIGIENE -------------------------------------------

	@Override
	public String getBano() {
		return ult_bano;
	}

	@Override
	public String getCepillado() {
		return ult_cepillado;
	}

	@Override
	public Map<String, String> getDesparasitado() {
		return desparasitado;
	}

	@Override
	public ArrayList<String> getProducto_nr() {
		return producto_nr;
	}

	// --------------- SETTERS HIGIENE -------------------------------------------

	@Override
	public void setBano(String bano) {
		this.ult_bano = bano;
	}

	@Override
	public void setCepillado(String cepillado) {
		this.ult_cepillado = cepillado;
	}

	@Override
	public void setDesparasitado(Map<String, String> desparasitado) {
		this.desparasitado = desparasitado;
	}

	@Override
	public void setProducto_nr(ArrayList<String> producto_nr) {
		this.producto_nr = producto_nr;
	}

}

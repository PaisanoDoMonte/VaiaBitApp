package com.vaiabitapp.objetos;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Domicilio {
	// Variables *********************************************************
	private int id;
	private int usuarioId;
	private String direccion1;
	private String direccion2;
	private String ciudad;
	private String provincia;
	private String pais;
	private int codigoPostal;

	// Constructores *********************************************************

	public Domicilio(int id, int usuarioId, String direccion1,
			String direccion2, String ciudad, String provincia, String pais,
			int codigoPostal) {
		this.id = id;
		this.usuarioId = usuarioId;
		this.direccion1 = direccion1;
		this.direccion2 = direccion2;
		this.ciudad = ciudad;
		this.provincia = provincia;
		this.pais = pais;
		this.codigoPostal = codigoPostal;
	}
	
	public Domicilio(int usuarioId, String direccion1,
			String direccion2, String ciudad, String provincia, String pais,
			int codigoPostal) {
		this.usuarioId = usuarioId;
		this.direccion1 = direccion1;
		this.direccion2 = direccion2;
		this.ciudad = ciudad;
		this.provincia = provincia;
		this.pais = pais;
		this.codigoPostal = codigoPostal;
	}

	public Domicilio() {

	}

	public Domicilio(JSONArray jsonArray) {
		try {
			JSONObject json = jsonArray.getJSONObject(0);
			this.setUsuarioId(json.getInt("id"));
			this.setDireccion1(json.getString("Direccion1"));
			this.setDireccion2(json.getString("Direccion2"));
			this.setCiudad(json.getString("Ciudad"));
			this.setPais(json.getString("Pais")); 
			this.setProvincia(json.getString("Provincia"));
			this.setCodigoPostal(json.getInt("CodigoPostal"));
		} catch (JSONException e) { 
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public Domicilio(JSONObject json) {
		try {
			this.setUsuarioId(json.getInt("id"));
			this.setDireccion1(json.getString("Direccion1"));
			this.setDireccion2(json.getString("Direccion2"));
			this.setCiudad(json.getString("Ciudad"));
			this.setPais(json.getString("Pais"));
			this.setProvincia(json.getString("Provincia"));
			this.setCodigoPostal(json.getInt("CodigoPostal"));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// Sobrescritos *********************************************************

	@Override
	public String toString() {
		return "Domicilio [id=" + id + ", usuarioId=" + usuarioId
				+ ", direccion1=" + direccion1 + ", direccion2=" + direccion2
				+ ", ciudad=" + ciudad + ", provincia=" + provincia + ", pais="
				+ pais + ", codigoPostal=" + codigoPostal + "]";
	}


	// METODOS *********************************************************


	// Gets and Sets *********************************************************
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(int usuarioId) {
		this.usuarioId = usuarioId;
	}

	public String getDireccion1() {
		return direccion1;
	}

	public void setDireccion1(String direccion1) {
		this.direccion1 = direccion1;
	}

	public String getDireccion2() {
		return direccion2;
	}

	public void setDireccion2(String direccion2) {
		this.direccion2 = direccion2;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public int getCodigoPostal() {
		return codigoPostal;
	}

	public void setCodigoPostal(int codigoPostal) {
		this.codigoPostal = codigoPostal;
	}
	
}

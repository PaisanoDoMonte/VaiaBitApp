package com.vaiabitapp.objetos;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.graphics.Bitmap;

import com.vaiabitapp.utils.Utils;

public class Producto {
	// Variables *********************************************************
	private int id;
	private int fabricanteId;
	private String codigo;
	private String partNumber;
	private String nombre;
	private String descripcion;
	private String descripcionCorta;
	private int unidades;
	private Bitmap imagen;
	private String imagenId;
	private String categoria;
	private boolean novedad;
	private ProductoPrecio precio;
	private String fabricante;

	// Constructores *********************************************************
	
	public Producto() {

	}

	public Producto(int id, int fabricanteId, String codigo, String partNumber,
			String nombre, String descripcion, String descripcionCorta,
			int unidades, String categoria, boolean novedad, String imagenId, ProductoPrecio precio, String fabricante) {
		this.setId(id);
		this.setFabricanteId(fabricanteId);
		this.setCodigo(codigo);
		this.setPartNumber(partNumber);
		this.setNombre(nombre);
		this.setDescripcion(descripcion);
		this.setDescripcionCorta(descripcionCorta);
		this.setUnidades(unidades);
		this.setCategoria(categoria);
		this.setNovedad(novedad);
		this.setImagenId(imagenId);
		this.setPrecio(precio);
		this.setFabricante(fabricante);
	}

	public Producto(JSONArray jsonArray){
		try {
			JSONObject json = jsonArray.getJSONObject(0);
			this.setId(json.getInt("id"));
			this.setFabricanteId(json.getInt("FabricanteId"));
			this.codigo=(json.getString("Codigo"));
			this.setPartNumber(json.getString("PartNumber"));
			this.setNombre(json.getString("Nombre"));
			this.setDescripcion(json.getString("Descripcion"));
			this.setDescripcionCorta(json.getString("DescripcionCorta"));
			this.setUnidades(json.getInt("Unidades"));
			this.setCategoria(json.getString("Categoria"));	
			this.setNovedad(Utils.toBoolean(json.getInt("Novedad")));
			this.setImagenId(json.getString("Imagen"));
			this.setFabricante(json.getString("Fabricante"));
			//Cargar el PRECIO
			precio = new ProductoPrecio(json);
						
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public Producto(JSONObject json){
		try {
			this.setId(json.getInt("id"));
			this.setFabricanteId(json.getInt("FabricanteId"));
			this.codigo=(json.getString("Codigo"));
			this.setPartNumber(json.getString("PartNumber"));
			this.setNombre(json.getString("Nombre"));
			this.setDescripcion(json.getString("Descripcion"));
			this.setDescripcionCorta(json.getString("DescripcionCorta"));
			this.setUnidades(json.getInt("Unidades"));
			this.setCategoria(json.getString("Categoria"));	
			this.setNovedad(Utils.toBoolean(json.getInt("Novedad")));
			this.setFabricante(json.getString("Fabricante"));
			this.setImagenId(json.getString("Imagen"));
			//Cargar el PRECIO
			precio = new ProductoPrecio(json);
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	
	//Sobrescritos *********************************************************
	
	@Override
	public String toString() {
		return "Producto [id=" + id + ", fabricanteId=" + fabricanteId
				+ ", codigo=" + codigo + ", partNumber=" + partNumber
				+ ", nombre=" + nombre + ", descripcion=" + descripcion
				+ ", descripcionCorta=" + descripcionCorta + ", unidades="
				+ unidades + ", categoria=" + categoria + ", novedad="
				+ novedad + "]";
	}
	
	
	//Gets and Sets *********************************************************
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getFabricanteId() {
		return fabricanteId;
	}

	public void setFabricanteId(int fabricanteId) {
		this.fabricanteId = fabricanteId;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPartNumber() {
		return partNumber;
	}

	public void setPartNumber(String partNumber) {
		this.partNumber = partNumber;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getDescripcionCorta() {
		return descripcionCorta;
	}

	public void setDescripcionCorta(String descripcionCorta) {
		this.descripcionCorta = descripcionCorta;
	}

	public int getUnidades() {
		return unidades;
	}

	public void setUnidades(int unidades) {
		this.unidades = unidades;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public boolean isNovedad() {
		return novedad;
	}

	public void setNovedad(boolean novedad) {
		this.novedad = novedad;
	}
		
	public Bitmap getImagen() {
		return imagen;
	}

	public void setImagen(Bitmap imagen) {
		this.imagen = imagen;
	}

	public String getImagenId() {
		return imagenId;
	}

	public void setImagenId(String imagenId) {
		this.imagenId = imagenId;
	}
	
	public ProductoPrecio getPrecio() {
		return precio;
	}

	public void setPrecio(ProductoPrecio precio) {
		this.precio = precio;
	}
	
	public String getFabricante() {
		return fabricante;
	}

	public void setFabricante(String fabricante) {
		this.fabricante = fabricante;
	}
}

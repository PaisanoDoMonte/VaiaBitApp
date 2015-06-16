package com.vaiabitapp.objetos;

import java.text.DecimalFormat;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.vaiabitapp.utils.Utils;

import android.graphics.Bitmap;

public class ProductoPrecio {
	// Variables *********************************************************
	private int id;
	private int productoId;
	private double descuento;
	private String fechaDescuentoInicio;
	private String fechaDescuentoFin;
	private double precio;
	private double iva;
	
	
	// Constructores *********************************************************
	
	public ProductoPrecio(int id, int productoId, float descuento,
			String fechaDescuentoInicio, String fechaDescuentoFin,
			double precio, float iva) {
		this.id = id;
		this.productoId = productoId;
		this.descuento = descuento;
		this.fechaDescuentoInicio = fechaDescuentoInicio;
		this.fechaDescuentoFin = fechaDescuentoFin;
		this.precio = precio;
		this.iva = iva;
	}
	
	public ProductoPrecio(float descuento,
			String fechaDescuentoInicio, String fechaDescuentoFin,
			double precio, float iva) {
		this.descuento = descuento;
		this.fechaDescuentoInicio = fechaDescuentoInicio;
		this.fechaDescuentoFin = fechaDescuentoFin;
		this.precio = precio;
		this.iva = iva;
	}
	
	public ProductoPrecio(){
		
	}
	
	public ProductoPrecio(JSONArray jsonArray){
		try {
			JSONObject json = jsonArray.getJSONObject(0);
			this.setDescuento(json.getDouble("Descuento"));
			this.setFechaDescuentoInicio(json.getString("FechaDescuentoInicio"));
			this.setFechaDescuentoFin(json.getString("FechaDescuentoFin"));
			this.setPrecio(json.getDouble("Precio"));
			this.setIva(json.getDouble("Iva"));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public ProductoPrecio(JSONObject json){
		try {
			this.setDescuento(json.getDouble("Descuento"));
			this.setFechaDescuentoInicio(json.getString("FechaDescuentoInicio"));
			this.setFechaDescuentoFin(json.getString("FechaDescuentoFin"));
			this.setPrecio(json.getDouble("Precio"));
			this.setIva(json.getDouble("Iva"));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	
	//Sobrescritos *********************************************************
	
	@Override
	public String toString() {
		return "ProductoPrecio [id=" + id + ", productoId=" + productoId
				+ ", descuento=" + descuento + ", fechaDescuentoInicio="
				+ fechaDescuentoInicio + ", fechaDescuentoFin="
				+ fechaDescuentoFin + ", precio=" + precio + ", iva=" + iva
				+ "]";
	}

	
	
	//METODOS *********************************************************
	
	public double getPrecioTotal(){
		double precio = this.precio;
		double descuento = this.descuento;
		double iva = this.iva;
		
		if(descuento!=0){
			return (precio*descuento)+((precio*descuento)*(iva/100)); 
		}
		else
			return precio+precio*(iva/100);
		
	}
	
	
	//Gets and Sets *********************************************************
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getProductoId() {
		return productoId;
	}

	public void setProductoId(int productoId) {
		this.productoId = productoId;
	}

	public double getDescuento() {
		return descuento;
	}

	public void setDescuento(double d) {
		this.descuento = d;
	}

	public String getFechaDescuentoInicio() {
		return fechaDescuentoInicio;
	}

	public void setFechaDescuentoInicio(String fechaDescuentoInicio) {
		this.fechaDescuentoInicio = fechaDescuentoInicio;
	}

	public String getFechaDescuentoFin() {
		return fechaDescuentoFin;
	}

	public void setFechaDescuentoFin(String fechaDescuentoFin) {
		this.fechaDescuentoFin = fechaDescuentoFin;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public double getIva() {
		return iva;
	}

	public void setIva(double iva) {
		this.iva = iva;
	}
	

}

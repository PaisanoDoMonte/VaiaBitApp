package com.vaiabitapp.utils;

import android.os.AsyncTask;


//Clase para la creacion de hilos, de insercion
public class HiloReservas extends AsyncTask<String, String, String> {
	private String direccion;
	private String metodo;		
	private String respuesta;
	private int productoId;
	private int UsuarioId;
	private int Unidades;
	
	//Constructor para definir que metodo vamos a llamar y pasarle los datos, con sobrecargas
	public HiloReservas(String ArchivoPHP, String metodo) {
		this.direccion= "http://5.134.115.139:8090/WebServices/"+ArchivoPHP+".php";
		this.metodo= metodo;			
	}
	
	public HiloReservas(String ArchivoPHP, String metodo, int productoId, int UsuarioId, int Unidades) {
		this(ArchivoPHP,metodo);
		this.productoId= productoId;
		this.UsuarioId = UsuarioId;
		this.Unidades = Unidades;
	}
	
	@Override
	protected String doInBackground(String... arg0) {
		respuesta = ConexionBD.ejecutarReservas(direccion, metodo, productoId, UsuarioId, Unidades);
		return respuesta;
	}		

}

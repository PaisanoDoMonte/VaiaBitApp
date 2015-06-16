package com.vaiabitapp.utils;

import org.json.JSONArray;

import com.vaiabitapp.objetos.Usuario;

import android.os.AsyncTask;
import android.text.style.SuperscriptSpan;

	//Clase para la creacion de hilos, de insercion
	public abstract class HiloManejarDomicilios extends AsyncTask<String, String, String> {
		private String direccion;
		private String metodo;		
		private String respuesta;
		private int usuarioId=0;
		private String direccion1="";
		private String direccion2="";
		private String ciudad="";
		private String provincia="";
		private String pais="";
		private int codigoPostal=0;
		private boolean insertar;
		private int id;
		
		//Constructor para definir que metodo vamos a llamar y pasarle los datos, con sobrecargas
		public HiloManejarDomicilios(String ArchivoPHP, String metodo) {
			this.direccion= "http://5.134.115.139:8090/WebServices/"+ArchivoPHP+".php";
			this.metodo= metodo;			
		}
		
		public HiloManejarDomicilios(String ArchivoPHP, String metodo, int usuarioId, String direccion1, String direccion2,
				String ciudad, String provincia, String pais, int codigoPostal, boolean insertar) {
			this(ArchivoPHP,metodo);
			this.usuarioId= usuarioId;
			this.direccion1 = direccion1;
			this.direccion2 = direccion2;
			this.ciudad = ciudad;
			this.provincia = provincia;
			this.pais = pais;
			this.codigoPostal = codigoPostal;
			this.insertar = insertar;
		}

		@Override
		protected String doInBackground(String... arg0) {
			// TODO Auto-generated method stub

				respuesta = ConexionBD.ejecutarDomicilio(direccion, metodo, usuarioId, direccion1, direccion2, ciudad, provincia, pais, codigoPostal);

			return respuesta;
		}		
		
		@Override
		protected abstract void onPostExecute(String result);

	}
package com.vaiabitapp.utils;

import org.json.JSONArray;
import android.os.AsyncTask;

	//Clase para la creacion de hilos, Sobreescribir el onPostExecute para manejar los datos
	//es la clase intermedia que llamará a la que accede a la BD del servidor, y nos permite manejar los datos
	public abstract class HiloGetJSON extends AsyncTask<String, String, JSONArray> {
		private String direccion;
		private String metodo;
		private String login="";
		private String password="";
		private JSONArray jarr;
		
		//Constructor para definir que metodo vamos a llamar y pasarle los datos, con sobrecargas
		public HiloGetJSON(String ArchivoPHP, String metodo) {
			this.direccion= "http://5.134.115.139:8090/WebServices/"+ArchivoPHP+".php";
			this.metodo= metodo;			
		}
		
		public HiloGetJSON(String ArchivoPHP, String metodo, String login, String password) {
			this(ArchivoPHP,metodo);
			this.login= login;
			this.password = password;
		}
		
		@Override
		protected JSONArray doInBackground(String... arg0) {
			// TODO Auto-generated method stub
			jarr = ConexionBD.cargarJson(direccion, metodo, login, password);
			return jarr;
		}		
		
		//metodo abstracto para que nos obligue a sobreescribirlo para manejar los datos
		@Override
		abstract protected void onPostExecute(JSONArray result);

	}

	
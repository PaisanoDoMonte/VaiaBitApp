package com.vaiabitapp.utils;

import org.json.JSONArray;

import com.vaiabitapp.objetos.Usuario;

import android.os.AsyncTask;
import android.text.style.SuperscriptSpan;

	//Clase para la creacion de hilos, de insercion
	public abstract class HiloManejarUsuarios extends AsyncTask<String, String, String> {
		private String direccion;
		private String metodo;		
		private String respuesta;
		private String login="";
		private String password="";
		private String nombre="";
		private String apellidos="";
		private String fechaNacimiento="";
		private String telefono1="";
		private String telefono2="";
		private String correo="";
		private boolean insertar;
		private int id;
		
		//Constructor para definir que metodo vamos a llamar y pasarle los datos, con sobrecargas
		public HiloManejarUsuarios(String ArchivoPHP, String metodo) {
			this.direccion= "http://5.134.115.139:8090/WebServices/"+ArchivoPHP+".php";
			this.metodo= metodo;			
		}
		
		public HiloManejarUsuarios(String ArchivoPHP, String metodo, String login, String password, String nombre,
				String apellidos, String correo, String fechaNacimiento, String telefono1, String telefono2, boolean insertar) {
			this(ArchivoPHP,metodo);
			this.login= login;
			this.password = password;
			this.telefono1 = telefono1;
			this.telefono2 = telefono2;
			this.nombre = nombre;
			this.apellidos = apellidos;
			this.fechaNacimiento = fechaNacimiento;
			this.correo = correo;
			this.insertar = insertar;
		}
		
		public HiloManejarUsuarios(String ArchivoPHP, String metodo,int id, String login, String password, String nombre,
				String apellidos, String correo, String fechaNacimiento, String telefono1, String telefono2, boolean insertar) {
			this(ArchivoPHP, metodo, login, password, nombre, apellidos, correo, fechaNacimiento, telefono1, telefono2, insertar);
			this.id = id;
		}
		
		@Override
		protected String doInBackground(String... arg0) {
			// TODO Auto-generated method stub
			if(insertar)
				respuesta = ConexionBD.ejecutarUsuario(direccion, metodo, login, password, nombre, apellidos,correo, fechaNacimiento, telefono1, telefono2);
			else
				respuesta = ConexionBD.ejecutarUsuario(direccion, metodo, id, login, password, nombre, apellidos,correo, fechaNacimiento, telefono1, telefono2);
			return respuesta;
		}		
		
		@Override
		protected abstract void onPostExecute(String result);

	}

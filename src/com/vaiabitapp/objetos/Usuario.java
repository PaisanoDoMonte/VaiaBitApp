package com.vaiabitapp.objetos;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.graphics.Bitmap;

import com.vaiabitapp.utils.Utils;

public class Usuario {
	// Variables *********************************************************
		private int id;
		private int rolId;
		private String login;
		private String password;
		private String correo;
		private String nombre;
		private String apellidos;
		private String fechaNacimiento; 
		private String telefono1;
		private String telefono2;
		private String fechaRegistro;
		private Domicilio domicilio;

		// Constructores *********************************************************
		
		public Usuario() {

		}

		public Usuario(int id, int rolId, String login, String password,
				String nombre, String correo, String apellidos,
				String fechaNacimiento, String telefono1, String telefono2, String fechaRegistro, Domicilio domicilio) {
			this.setId(id);
			this.setRolId(rolId);
			this.setLogin(login);
			this.setPassword(password);
			this.setNombre(nombre);
			this.setCorreo(correo);
			this.setApellidos(apellidos);
			this.setFechaNacimiento(fechaNacimiento);
			this.setFechaRegistro(fechaRegistro);
			this.setTelefono1(telefono1);
			this.setTelefono2(telefono2);
		}

		public Usuario(JSONArray jsonArray){
			try {
				JSONObject json = jsonArray.getJSONObject(0);
				this.setId(json.getInt("id"));
				this.setRolId(json.getInt("RolId"));
				this.setLogin(json.getString("Login"));
				this.setPassword(json.getString("PassWord"));
				this.setNombre(json.getString("Nombre"));
				this.setCorreo(json.getString("Correo"));
				this.setApellidos(json.getString("Apellidos"));
				this.setFechaNacimiento(json.getString("FechaNacimiento"));
				this.setFechaRegistro(json.getString("FechaRegistro"));
				this.setTelefono1(json.getString("Telefono1"));
				this.setTelefono2(json.getString("Telefono2"));
				//Cargar el Domicilio
				domicilio = new Domicilio(json);
							
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		public Usuario(JSONObject json){
			try {
				this.setId(json.getInt("id"));
				this.setRolId(json.getInt("RolId"));
				this.setLogin(json.getString("Login"));
				this.setPassword(json.getString("PassWord"));
				this.setNombre(json.getString("Nombre"));
				this.setCorreo(json.getString("Correo"));
				this.setApellidos(json.getString("Apellidos"));
				this.setFechaNacimiento(json.getString("FechaNacimiento"));
				this.setFechaRegistro(json.getString("FechaRegistro"));
				this.setTelefono1(json.getString("Telefono1"));
				this.setTelefono2(json.getString("Telefono2"));
				//Cargar el Domicilio
				domicilio = new Domicilio(json);
				
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		
		
		//Sobrescritos *********************************************************
		
		@Override
		public String toString() {
			return "Usuario [id=" + id + ", rolId=" + rolId + ", login="
					+ login + ", password=" + password + ", correo=" + correo
					+ ", nombre=" + nombre + ", apellidos=" + apellidos
					+ ", fechaNacimiento=" + fechaNacimiento + ", telefono1="
					+ telefono1 + ", telefono2=" + telefono2 +
					", fechaRegistro=" + fechaRegistro + "]";
		}
		
		
		
		//Gets and Sets *********************************************************
		
		public int getId() {
			return id;
		}	

		public void setId(int id) {
			this.id = id;
		}

		public int getRolId() {
			return rolId;
		}

		public void setRolId(int rolId) {
			this.rolId = rolId;
		}

		public String getLogin() {
			return login;
		}

		public void setLogin(String login) {
			this.login = login;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public String getCorreo() {
			return correo;
		}

		public void setCorreo(String correo) {
			this.correo = correo;
		}

		public String getNombre() {
			return nombre;
		}

		public void setNombre(String nombre) {
			this.nombre = nombre;
		}

		public String getApellidos() {
			return apellidos;
		}

		public void setApellidos(String apellidos) {
			this.apellidos = apellidos;
		}

		public String getFechaNacimiento() {
			return fechaNacimiento;
		}

		public void setFechaNacimiento(String fechaNacimiento) {
			this.fechaNacimiento = fechaNacimiento;
		}

		public String getTelefono1() {
			return telefono1;
		}

		public void setTelefono1(String telefono1) {
			this.telefono1 = telefono1;
		}

		public String getTelefono2() {
			return telefono2;
		}

		public void setTelefono2(String telefono2) {
			this.telefono2 = telefono2;
		}

		public String getFechaRegistro() {
			return fechaRegistro;
		}

		public void setFechaRegistro(String fechaRegistro) {
			this.fechaRegistro = fechaRegistro;
		}

		public Domicilio getDomicilio() {
			return domicilio;
		}

		public void setDomicilio(Domicilio domicilio) {
			this.domicilio = domicilio;
		}
			
		
}

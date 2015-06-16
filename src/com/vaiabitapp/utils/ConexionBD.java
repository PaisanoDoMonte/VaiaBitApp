package com.vaiabitapp.utils;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;

// Clase que realiza las comunicaciones con el server
public class ConexionBD { 
	
	// Metodo Sobrescrito para no incluir parametros
	public static JSONArray cargarJson(String phpFileAddress, String funcion) {
		return cargarJson(phpFileAddress, funcion, "", "");
	}
	
	public static JSONArray cargarJson(String phpFileAddress, String funcion, String login, String password){
		return cargarJson(phpFileAddress, funcion, login, password, 0);
	}
	
	public static JSONArray cargarJson(String phpFileAddress, String funcion, int usuarioId) {
		return cargarJson(phpFileAddress, funcion, "", "", usuarioId);
	}		
	
	// Devuelve un Array JSON de la consulta
	public static JSONArray cargarJson(String phpFileAddress, String funcion, String login, String password, int usuarioId) {
		
		//variables
		JSONArray jsonArray = null;

		try {

			URL url = new URL(phpFileAddress);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			//enviar post y preparar outputstream con los datos que enviamos por post
			conn.setRequestMethod("POST");
			conn.setDoOutput(true);
			DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
			wr.writeBytes("&Funcion=" + funcion + "&Login=" + login +"&PassWord="+password+"&UsuarioId="+usuarioId);
			wr.flush();
			wr.close();
			
			conn.connect();

			// Leer JSON devuelto
			InputStream is = conn.getInputStream();
			BufferedReader streamReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
			StringBuilder responseStrBuilder = new StringBuilder();

			String inputStr;
			while ((inputStr = streamReader.readLine()) != null)
				responseStrBuilder.append(inputStr);

			jsonArray = new JSONArray(responseStrBuilder.toString());

			conn.disconnect();
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//devolvemos el json que hemos recogido del servidor
		return jsonArray;
	}
	
	//Metodo sobrescrito
	public static String ejecutarUsuario(String phpFileAddress, String funcion, String login, String password, String nombre,
			String apellidos, String correo, String fechaNacimiento, String telefono1, String telefono2) {
		return ejecutarUsuario(phpFileAddress, funcion, 0, login, password, nombre, apellidos, correo, fechaNacimiento, telefono1, telefono2);

	}
	
	//metodo para insertar datos en la bd usuario
	public static String ejecutarUsuario(String phpFileAddress, String funcion,int id, String login, String password, String nombre,
			String apellidos, String correo, String fechaNacimiento, String telefono1, String telefono2) {

		String resp="";
		try {

			URL url = new URL(phpFileAddress);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			//enviar post y preparar outputstream con los datos que enviamos por post
			conn.setRequestMethod("POST");
			conn.setDoOutput(true);
			DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
			wr.writeBytes("&Funcion=" + funcion + "&Login=" + login +"&PassWord="+ password
					+"&Nombre="+nombre+"&Apellidos="+apellidos+"&FechaNacimiento="+fechaNacimiento
					+"&Telefono1="+telefono1+"&Telefono2="+telefono2+"&Correo="+correo+"&Id="+id);
			wr.flush();
			wr.close();
			
			conn.connect();
			
			InputStream is = conn.getInputStream();
			BufferedReader streamReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
			StringBuilder responseStrBuilder = new StringBuilder();

			String inputStr;
			while ((inputStr = streamReader.readLine()) != null)
				responseStrBuilder.append(inputStr);

			resp = responseStrBuilder.toString();
			
			conn.disconnect();
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resp;

	}

	
	//metodo para insertar datos en la bd reservas
		public static String ejecutarReservas(String phpFileAddress, String funcion, int productoId, int UsuarioId, int Unidades) {

			String resp="";
			try {

				URL url = new URL(phpFileAddress);
				HttpURLConnection conn = (HttpURLConnection) url.openConnection();
				//enviar post y preparar outputstream con los datos que enviamos por post
				conn.setRequestMethod("POST");
				conn.setDoOutput(true);
				DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
				wr.writeBytes("&Funcion=" + funcion + "&ProductoId=" + productoId +"&UsuarioId="+ UsuarioId
						+"&Unidades="+Unidades);
				wr.flush();
				wr.close();
				
				conn.connect();
				
				InputStream is = conn.getInputStream();
				BufferedReader streamReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
				StringBuilder responseStrBuilder = new StringBuilder();

				String inputStr;
				while ((inputStr = streamReader.readLine()) != null)
					responseStrBuilder.append(inputStr);

				resp = responseStrBuilder.toString();
				
				conn.disconnect();
				
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return resp;

		}
		
		//Metodo sobrescrito
		public static String ejecutarDomicilio(String phpFileAddress, String funcion, int usuarioId, String direccion1, String direccion2,
				String ciudad, String provincia, String pais, int codigoPostal) {
			return ejecutarDomicilio(phpFileAddress, funcion, 0, usuarioId, direccion1, direccion2, ciudad, provincia, pais, codigoPostal);

		}
		
		//metodo para insertar datos en la bd Domicilio
		public static String ejecutarDomicilio(String phpFileAddress, String funcion, int id, int usuarioId, String direccion1, String direccion2,
				String ciudad, String provincia, String pais, int codigoPostal) {

			String resp="";
			try {

				URL url = new URL(phpFileAddress);
				HttpURLConnection conn = (HttpURLConnection) url.openConnection();
				//enviar post y preparar outputstream con los datos que enviamos por post
				conn.setRequestMethod("POST");
				conn.setDoOutput(true);
				DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
				wr.writeBytes("&Funcion=" + funcion + "&UsuarioId=" + usuarioId +"&Direccion1="+ direccion1
						+"&Direccion2="+direccion2+"&Ciudad="+ciudad+"&Provincia="+provincia
						+"&Pais="+pais+"&CodigoPostal="+codigoPostal+"&Id="+id);
				wr.flush();
				wr.close();
				
				conn.connect();
				
				InputStream is = conn.getInputStream();
				BufferedReader streamReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
				StringBuilder responseStrBuilder = new StringBuilder();

				String inputStr;
				while ((inputStr = streamReader.readLine()) != null)
					responseStrBuilder.append(inputStr);

				resp = responseStrBuilder.toString();
				
				conn.disconnect();
				
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return resp;

		}
}

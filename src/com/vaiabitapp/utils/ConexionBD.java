package com.vaiabitapp.utils;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import org.json.JSONArray;
import org.json.JSONException;

// Clase que realiza las comunicaciones con el server
public class ConexionBD {
	
	// Devuelve un Array JSON de la consulta
	public static JSONArray cargarJson(String phpFileAddress, String funcion, String id) {
		
		//variables
		JSONArray jsonArray = null;

		try {

			URL url = new URL(phpFileAddress);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			//enviar post y preparar outputstream con los datos que enviamos por post
			conn.setRequestMethod("POST");
			conn.setDoOutput(true);
			DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
			wr.writeBytes("&Funcion=" + funcion + "&Id=" + id);
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

	// Metodo Sobrescrito para no incluir parametro ID
	public static JSONArray cargarJson(String phpFileAddress, String funcion) {
		return cargarJson(phpFileAddress, funcion, "");
	}
}

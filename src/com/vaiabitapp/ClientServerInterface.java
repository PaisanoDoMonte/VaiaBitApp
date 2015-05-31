package com.vaiabitapp;

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
import org.json.JSONObject;

public class ClientServerInterface {
	// Variables para devolver los JSON
	static InputStream is = null;
	static JSONObject jobj = null;
	static JSONArray jarr = null;
	static String json = "";
	public static enum DI  {rune,run,s};

	//Devuelve un Array JSON de la consulta
	public static JSONArray loadFromDatabaseNew(String phpFileAddress, String funcion, String id) {
		
		JSONArray jsonArray = null;

		try {
			
			URL url = new URL(phpFileAddress);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();		
			conn.setRequestMethod("POST");
			
			// Send post request
			conn.setDoOutput(true);
			DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
			wr.writeBytes("&Funcion="+funcion+"&Id="+id);
			//wr.writeBytes("&run=run&pin=pin");
			wr.flush();
			wr.close();
		
			conn.connect();

			//Leer JSON
			
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

		return jsonArray;
	}
	
	//Metodo Sobrescrito para no incluir parametro ID
	public static JSONArray loadFromDatabaseNew(String phpFileAddress, String funcion) {
		return loadFromDatabaseNew(phpFileAddress, funcion, "");
	}
}
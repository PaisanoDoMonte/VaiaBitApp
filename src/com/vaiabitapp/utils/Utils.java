package com.vaiabitapp.utils;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Authenticator;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.PasswordAuthentication;
import java.net.URL;
import java.net.URLConnection;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.TransactionTooLargeException;
import android.util.Base64;
import android.util.Log;

import com.vaiabitapp.R;

public class Utils {
	// Convertir 1 y 0 a true y false
	public static Boolean toBoolean(int integer) {
		if (integer == 1)
			return true;
		else
			return false;
	}

	// Metodo generico para cambiar los fragment a pantalla completa
	public static void cambiarFragment(Fragment fragment, FragmentManager manager, int container, String tag) {
		FragmentTransaction transaction = manager.beginTransaction();
		transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
		transaction.replace(container, (Fragment) fragment, tag);
		transaction.addToBackStack(null);
		transaction.commit();
	}
	
	// Metodo generico para abrir los fragment a pantalla completa
		public static void abrirFragment(Fragment fragment, FragmentManager manager, int container, String tag) {
			FragmentTransaction transaction = manager.beginTransaction();
			transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
			transaction.add(container, (Fragment) fragment, tag);
			transaction.commit();
		}
	
	// Metodo generico para borrar los fragment a pantalla completa
	public static void borrarFragment(Fragment fragment, FragmentManager manager) {
		FragmentTransaction transaction = manager.beginTransaction();
		transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
		transaction.remove(fragment);
		transaction.commit();
	}
	
	public static boolean usuarioLogin(JSONArray jarray){
		boolean registrado=false;
		try {
			JSONObject json = jarray.getJSONObject(0);
			if(json.getInt("registrado") == 1)
				registrado = true;		
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return registrado;
	}
	
	//Metodo para descargar las imagenes del ftp
	public static byte[] DescargarImagen(Context ctx, String strurl) {
	    try {	    	
	        
	        FTPClient ftpClient = new FTPClient();
			ftpClient.connect("5.134.115.139",21);

			ftpClient.enterLocalPassiveMode();
	        ftpClient.login("daemon", "4880.contrasenha");
	        
	        ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
	        ftpClient.changeWorkingDirectory("Imagenes");
	        

	        InputStream is = ftpClient.retrieveFileStream(strurl+".jpg");

	        byte[] buffer = new byte[8192];
	        int bytesRead;
	        ByteArrayOutputStream output = new ByteArrayOutputStream();
	        while ((bytesRead = is.read(buffer)) != -1) {
	            output.write(buffer, 0, bytesRead);
	        }
	        return output.toByteArray();
	    } catch (MalformedURLException e) {
	        e.printStackTrace();
	    return null;
	    } catch (IOException e) {
	        e.printStackTrace();
	        return null;
	    }
	}
}

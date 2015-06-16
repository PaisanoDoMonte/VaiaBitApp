package com.vaiabitapp.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import org.json.JSONArray;

import com.vaiabitapp.objetos.Producto;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

//Clase para la creacion de hilos, Sobreescribir el onPostExecute para manejar los datos
	//es la clase intermedia que llamará a la que accede a la BD del servidor, y nos permite manejar los datos
	public class HiloGetImagenes extends AsyncTask<String, String, String> {
		private ArrayList<Producto> productos;
		private Context context;
		private ProgressDialog progressDialog;
		
		//Constructor para definir que metodo vamos a llamar y pasarle los datos, con sobrecargas
		public HiloGetImagenes(Context context, ArrayList<Producto> productos) {
			this.productos = productos;	
			this.context = context;
		}
		
		@Override
	    protected void onPreExecute() {
	        super.onPreExecute();
	        progressDialog = ProgressDialog.show(context, "Espera", "Descargando...");
	    }
		
		@Override
		protected String doInBackground(String... arg0) {
			//agregamos las imagenes
			for(Producto productoAñadirImagen : productos){
				byte[] bytes = Utils.DescargarImagen(context, productoAñadirImagen.getImagenId());
				productoAñadirImagen.setImagen(BitmapFactory.decodeByteArray(bytes, 0, bytes.length));
				//yourImageview.setImageBitmap(bm);
			}
			return "Correcto";
		}		
		
		@Override
		protected void onPostExecute(String result){
			progressDialog.dismiss();
		}

	}
	
	
	
	
	
	
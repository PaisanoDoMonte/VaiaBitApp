package com.vaiabitapp;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import com.example.vaiabitapp.R;
import com.vaiabitapp.objetos.Producto;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.os.Build;

public class VistaPrincipal extends Activity {

	JSONObject jobj = null;
	JSONArray jarr = null;
	TextView textView;
	String ab;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_vista_principal);
		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.vista_principal, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_vista_principal,
					container, false);
			return rootView;
		}
	}
	
	//*****************************************
	//Clase para la creacion de hilos, Sobreescribir el onPostExecute para manejar los datos
	class RetreiveData extends AsyncTask<String, String, JSONArray> {
		private String direccion;
		private String metodo;
		private String id="";
		
		//Constructor para definir que metodo vamos a llamar y pasarle los datos, con sobrecargas
		public RetreiveData(String direccion, String metodo) {
			this.direccion= "http://5.134.115.139:8090/WebServices/"+direccion+".php";
			this.metodo= metodo;			
		}
		
		public RetreiveData(String direccion, String metodo, String id) {
			this(direccion,metodo);
			this.id= id;
		}
		
		@Override
		protected JSONArray doInBackground(String... arg0) {
			// TODO Auto-generated method stub
			jarr = ClientServerInterface.loadFromDatabaseNew(direccion, metodo, id);
			return jarr;
		}		

	}

	//*****************************************
	
	public void conectar(View View) {
		
		textView = (TextView) findViewById(R.id.textView1);
		// start background processing
		//AsyncTask<String, String, String> s = new RetreiveData().execute("","");
		
		new RetreiveData("Productos","GetProducto","1") {  
            @Override  
            public void onPostExecute(JSONArray message) {  
            	Producto p = new Producto(message);
            	textView.setText(p.toString());
            }  

		}.execute();
		 
	}
}

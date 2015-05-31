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

	class RetreiveData extends AsyncTask<String, String, String> {

		@Override
		protected String doInBackground(String... arg0) {
			// TODO Auto-generated method stub
			jarr = ClientServerInterface.loadFromDatabaseNew("http://5.134.115.139:8090/WebServices/Productos.php", ClientServerInterface.DI.rune);
			try {
				jobj = jarr.getJSONObject(0);
				//ab = String.valueOf(jobj.toString());
				
				ab= String.valueOf(jarr.toString());
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return ab;
		}

		protected void onPostExecute(String ab) {

			textView.setText(ab);
		}

	}

	public void conectar(View View) {
		
		textView = (TextView) findViewById(R.id.textView1);
		// start background processing
		new RetreiveData().execute();

	}
}

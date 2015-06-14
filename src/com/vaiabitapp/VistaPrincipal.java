package com.vaiabitapp;

import com.vaiabitapp.FragmentListaDetalles.OnFragmentDetallesListener;
import com.vaiabitapp.FragmentListaProductos.OnFragmentListaListener;
import com.vaiabitapp.FragmentLoginRegistro.OnFragmentInteractionListener;
import com.vaiabitapp.FragmentPrincipal.OnFragmentPrincipalListener;
import com.vaiabitapp.R;
import com.vaiabitapp.objetos.Producto;
import com.vaiabitapp.utils.ConexionBD;
import com.vaiabitapp.utils.HiloGetJSON;
import com.vaiabitapp.utils.Utils;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

public class VistaPrincipal extends Activity implements OnFragmentPrincipalListener, OnFragmentInteractionListener, OnFragmentListaListener, OnFragmentDetallesListener {

	FragmentManager manager;
	FragmentTransaction transaction;
	FragmentPrincipal fragment;
	private Boolean dualPane;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_vista_principal);
		//Comprobamos si el dispositivo es una tablet o no para cambiar como presentar los fragment
		int DisplaySize = (getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK);
		if( DisplaySize==  Configuration.SCREENLAYOUT_SIZE_XLARGE || DisplaySize == Configuration.SCREENLAYOUT_SIZE_LARGE){			
			dualPane = true;
			FrameLayout layout = (FrameLayout)findViewById(R.id.containerDetalles);
			//Ocultamos el fragment derecho
			layout.setVisibility(View.GONE); 
		}else{			
			setContentView(R.layout.activity_vista_principal);
			dualPane = false;
		}
	
		
		if (savedInstanceState != null){
			//Aquí recuperamos el fragment actual debido a rotacion del dispositivo
		}else{
			// Cargamos el fragment inicial
			manager = getFragmentManager();
			fragment = new FragmentPrincipal();
			Utils.abrirFragment(fragment, manager,R.id.containerPrincipal, "principal", false);
						
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

	
	
	@Override
	public void fragmentPrincipalListener() {
		//TODO: BOrrar PRObablemente
		
	}

	@Override
	public void onFragmentInteraction(Uri uri) {
		//TODO: BOrrar PRObablemente
		
	}
	
	@Override
	public void onFragmentDetalles(Producto producto) {
		//TODO: BOrrar PRObablemente
		
	}

	
	//Método de la interfaz lista, que nos permite abrir dos fragment a la vez si estamos en modo dual
	@Override
	public void onFragmentLista(Producto producto) {
		FragmentListaDetalles listaDetalles = new FragmentListaDetalles(producto);	
		if(dualPane){
			Utils.cambiarFragment(listaDetalles, manager,R.id.containerDetalles, "detalles", false);				
		}else{
			Utils.cambiarFragment(listaDetalles, manager,R.id.containerPrincipal, "detalles", true);		
		}

	}

	@Override
	public void onBackPressed() {
		//Si detectamos que estamos en modo dual, borramos el fragment detalles
		FrameLayout layout = (FrameLayout)findViewById(R.id.containerDetalles);
		if(dualPane && layout.isActivated()){
			Utils.borrarFragment(getFragmentManager().findFragmentByTag("detalles"), manager);
			layout = (FrameLayout)findViewById(R.id.containerDetalles);
			layout.setVisibility(View.GONE); 		
		}		
		
		super.onBackPressed();
	}
	
	
}

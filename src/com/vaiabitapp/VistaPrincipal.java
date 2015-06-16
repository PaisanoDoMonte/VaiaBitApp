package com.vaiabitapp;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.vaiabitapp.FragmentListaDetalles.OnFragmentDetallesListener;
import com.vaiabitapp.FragmentListaProductos.OnFragmentListaListener;
import com.vaiabitapp.FragmentLoginRegistro.OnFragmentInteractionListener;
import com.vaiabitapp.FragmentPrincipal.OnFragmentPrincipalListener;
import com.vaiabitapp.R;
import com.vaiabitapp.objetos.Producto;
import com.vaiabitapp.objetos.Usuario;
import com.vaiabitapp.utils.ConexionBD;
import com.vaiabitapp.utils.HiloGetJSON;
import com.vaiabitapp.utils.Utils;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.FrameLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.SimpleExpandableListAdapter;
import android.widget.Toast;

public class VistaPrincipal extends Activity implements OnFragmentPrincipalListener, OnFragmentInteractionListener, OnFragmentListaListener, OnFragmentDetallesListener {

	FragmentManager manager;
	FragmentTransaction transaction;
	FragmentPrincipal fragment;
	private Boolean dualPane;
	DrawerLayout drawerMenuLateral;
	ExpandableListView listaMenuLateral;
	Usuario usuario;
	ArrayList< Producto> productosCarrito;
	
	//Crear el menu
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_principal, menu);
		
		if(usuario==null){
			menu.getItem(0).setVisible(false);
			menu.getItem(1).setVisible(false);
		}
		else
			menu.getItem(0).setTitle(usuario.getLogin());

		
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.mnuLateral:
			if (drawerMenuLateral.isDrawerOpen(listaMenuLateral)) {
				drawerMenuLateral.closeDrawers(); 
			} else {
				drawerMenuLateral.openDrawer(listaMenuLateral);
			}
			return true;
		case R.id.mnuSalir:
			finish();
			return true;
		case R.id.mnuUsuario:
			FragmentLoginRegistro fragment = new FragmentLoginRegistro(usuario, "menuUsuario");
			Utils.cambiarFragment(fragment, manager,R.id.containerPrincipal, "pantallaUsuario");
			quitarFragmentDetalles();
			return true;
		case R.id.mnuCarrito:
			if(productosCarrito==null){
				Toast.makeText(this, "No hay productos en el carrito", Toast.LENGTH_SHORT).show();
			}else if(productosCarrito.size()==0){					
				Toast.makeText(this, "No hay productos en el carrito", Toast.LENGTH_SHORT).show();
			}else{
				FragmentListaProductos fragmentLp = new FragmentListaProductos(productosCarrito, true);
				Utils.cambiarFragment(fragmentLp, manager,R.id.containerPrincipal, "pantallaCarrito");
				quitarFragmentDetalles();
				return true;	
			}
				
		}
		

		return super.onOptionsItemSelected(item);
	}

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
			//habilitamos que solo se pueda usar en tumbado
			setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		}else{			
			dualPane = false;
			FrameLayout layout = (FrameLayout)findViewById(R.id.containerDetalles);
			//Ocultamos el fragment derecho
			layout.setVisibility(View.GONE); 
			//habilitamos que solo se pueda usar en portrait
			setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		}
	
		
		if (savedInstanceState != null){
			//Aquí recuperamos el fragment actual debido a rotacion del dispositivo
		}else{
			// Cargamos el fragment inicial
			manager = getFragmentManager();
			fragment = new FragmentPrincipal(usuario);
			Utils.abrirFragment(fragment, manager,R.id.containerPrincipal, "principal");
						
		}	
		
		//menu lateral ----------------------------
		
		listaMenuLateral =  (ExpandableListView) findViewById(R.id.menuLateral);
		drawerMenuLateral = (DrawerLayout) findViewById(R.id.drawer_layout);	
	
		//Lista para los elementos padre
			List<Map<String, String>> listaPadres = new ArrayList<Map<String, String>>() {{
			  add(new HashMap<String, String>() {{
			    put("Padre", "Categorias");
			  }});
			  add(new HashMap<String, String>() {{
			    put("Padre", "Interés");
			  }});
			}};
			
		//Lista elementos hijo categorias	
			final List<List<Map<String, String>>> listaHijos = new ArrayList<List<Map<String, String>>>();

			List<Map<String, String>> listaHijosCategoria = new ArrayList<Map<String, String>>(){{
			  add(new HashMap<String, String>() {{
			    put("Hijo", "Graficas");
			  }});
			  add(new HashMap<String, String>() {{
			    put("Hijo", "Ordenadores");
			  }});
			  add(new HashMap<String, String>() {{
				    put("Hijo", "Teclados");
			  }});
			}};
			listaHijos.add(listaHijosCategoria);

		//Lista elementos hijo Interes	
			final List<Map<String, String>> listaHijosInteres = new ArrayList<Map<String, String>>(){{
			  add(new HashMap<String, String>() {{
			    put("Hijo", "Novedades");
			  }});
			  add(new HashMap<String, String>() {{
			    put("Hijo", "Ofertas");
			  }});
			}};
			listaHijos.add(listaHijosInteres);
		
			
		//Adaptador de la lista expandible
			SimpleExpandableListAdapter adaptadorExpandible = new SimpleExpandableListAdapter(
				  this,

				  listaPadres,
				  android.R.layout.simple_expandable_list_item_1,
				  new String[] { "Padre" },
				  new int[] { android.R.id.text1 },

				  listaHijos,
				  android.R.layout.simple_list_item_activated_1,
				  new String[] { "Hijo", "Hijo" },
				  new int[] { android.R.id.text1, android.R.id.text2 }
				);

		//Click listener que detecta que hijo clicamos para cargar las distintas vistas	
		listaMenuLateral.setOnChildClickListener(new OnChildClickListener() {
			
			@Override
			public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
				FragmentListaProductos fragment;
				switch (groupPosition) {
				case 0:
					switch (childPosition) {
					case 0:
						fragment = new FragmentListaProductos("GetProductosGraficas");
						Utils.cambiarFragment(fragment, manager,R.id.containerPrincipal, "listaGraficas");
						break;
					case 1:
						fragment = new FragmentListaProductos("GetProductosOrdenadores");
						Utils.cambiarFragment(fragment, manager,R.id.containerPrincipal, "listaOrdenadores");
						break;
					case 2:
						fragment = new FragmentListaProductos("GetProductosTeclados");
						Utils.cambiarFragment(fragment, manager,R.id.containerPrincipal, "listaTeclados");
						break;
					}
					break;
				case 1:
					switch (childPosition) {
					case 0:
						fragment = new FragmentListaProductos("GetProductosNovedad");
						Utils.cambiarFragment(fragment, manager,R.id.containerPrincipal, "listaNovedades");
						break;
					case 1:
						fragment = new FragmentListaProductos("GetProductosOferta");
						Utils.cambiarFragment(fragment, manager,R.id.containerPrincipal, "listaOfertas");
						break;
					}
					break;
				}				
				drawerMenuLateral.closeDrawers();
				return false;
			}
		});
		listaMenuLateral.setAdapter(adaptadorExpandible);
		//FIn menu lateral ----------------------------
		
	}


	
	
	@Override
	public void fragmentPrincipalListener() { 
		//TODO: BOrrar PRObablemente
		
	}

	@Override
	public void onFragmentInteraction(Uri uri) {
		//TODO: BOrrar PRObablemente
		
	}
	
	
	//Método de la interfaz detalles, que nos permite guardar los productos para mostrarlos en la cesta o quitarlos
	@Override
	public void onFragmentDetalles(Producto producto, boolean borrar) {
		if(productosCarrito==null){
			productosCarrito = new ArrayList<Producto>();
		}
		if(borrar){			
			if(productosCarrito.size()==1){
				productosCarrito.remove(producto);
				FragmentPrincipal fragmentP = new FragmentPrincipal(usuario);
				Utils.cambiarFragment(fragmentP, manager,R.id.containerPrincipal, "pantallaPrincipal");
				quitarFragmentDetalles();
				getActionBar().setDisplayHomeAsUpEnabled(false);
				getActionBar().setHomeButtonEnabled(false);
			}else{
				productosCarrito.remove(producto);
				if(dualPane){
					FragmentListaProductos fragmentLp = new FragmentListaProductos(productosCarrito, true);
					Utils.cambiarFragment(fragmentLp, manager,R.id.containerPrincipal, "pantallaCarrito");
					quitarFragmentDetalles();
				}
			}
		}
		else
			productosCarrito.add(producto);
		
	}

	
	//Método de la interfaz lista, que nos permite abrir dos fragment a la vez si estamos en modo dual
	@Override
	public void onFragmentLista(Producto producto, boolean esCarrito) {
		FragmentListaDetalles listaDetalles = new FragmentListaDetalles(producto, dualPane, esCarrito);	
		if(dualPane){
			Utils.cambiarFragment(listaDetalles, manager,R.id.containerDetalles, "detalles");				
		}else{
			Utils.cambiarFragment(listaDetalles, manager,R.id.containerPrincipal, "detalles");		
		} 

	}

	
	
	@Override
	public void onBackPressed() {
		if (manager.getBackStackEntryCount() > 0) {
			quitarFragmentDetalles();
			super.onBackPressed();
			manager.popBackStack();
		}
	}
	
	public void quitarFragmentDetalles() {
		//Si detectamos que estamos en modo dual, borramos el fragment detalles
		FrameLayout layout = (FrameLayout)findViewById(R.id.containerDetalles);
		FragmentListaDetalles detalles = (FragmentListaDetalles) getFragmentManager().findFragmentByTag("detalles");
		if(dualPane){
			layout.setVisibility(View.GONE); 
			if(detalles!=null){
				Utils.borrarFragment(detalles, manager);
				layout = (FrameLayout)findViewById(R.id.containerDetalles);
			}					
		}		
	}
	
}

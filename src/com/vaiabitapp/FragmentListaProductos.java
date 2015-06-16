package com.vaiabitapp;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.client.utils.URIUtils;
import org.json.JSONArray;
import org.json.JSONException;

import com.vaiabitapp.FragmentListaProductos.OnFragmentListaListener;
import com.vaiabitapp.objetos.Producto;
import com.vaiabitapp.utils.HiloGetImagenes;
import com.vaiabitapp.utils.HiloGetJSON;
import com.vaiabitapp.utils.Utils;

import android.app.ActionBar;
import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.MediaStore.Images;
import android.app.Fragment;
import android.content.ContentUris;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.webkit.WebView.FindListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TabHost;
import android.widget.Toast;

public class FragmentListaProductos extends Fragment implements OnItemClickListener{

	private ListView list;
	private FragmentManager manager;
	private OnFragmentListaListener mListener;
	private ArrayList<Producto> productos;
	private String metodoPhp;
	private boolean esCarrito;

	public FragmentListaProductos(String metodoPhp) {
		this.metodoPhp = metodoPhp;
		this.esCarrito = false;
	}
	
	//Constructor para el carrito
	public FragmentListaProductos(ArrayList<Producto> productos, boolean esCarrito) {
		this.productos = productos;
		this.esCarrito = esCarrito;
	}

	
	//cambiamos el menú 
	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		getActivity().getActionBar().setDisplayHomeAsUpEnabled(true);
		getActivity().getActionBar().setTitle("Productos");
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {		
		if (manager.getBackStackEntryCount() > 0) {
			getActivity().onBackPressed();
			manager.popBackStack();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		
		//Comprobamos si el dispositivo es una tablet o no para cambiar como presentar los fragment
		int DisplaySize = (getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK);
		if( DisplaySize==  Configuration.SCREENLAYOUT_SIZE_XLARGE || DisplaySize == Configuration.SCREENLAYOUT_SIZE_LARGE){
			
			FrameLayout layout = (FrameLayout)getActivity().findViewById(R.id.containerDetalles);
			layout.setVisibility(View.VISIBLE);
			
			
		}
		
		View fragmentView = inflater.inflate(R.layout.fragment_lista_productos, container, false);
		manager = getFragmentManager();
		
		list = (ListView) fragmentView.findViewById(R.id.ListViewLista); 				
		
		//comprobamos si ya tenemos cargados productos
		if(productos!=null){
			list = RecuperarViewLista();
		} else {
			// inicializamos el array de productos
			productos = new ArrayList<Producto>();
			
			// Añadimos los productos al array
			new HiloGetJSON("Productos",metodoPhp) {			
				@Override
				protected void onPostExecute(JSONArray result) {
					
					for(int i=0;i<result.length();i++){
						try {
							productos.add(new Producto(result.getJSONObject(i)));
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					//Hilo que recoje las imagenes del ftp y las añade a los objetos
					//TODO: LA DESCARGA DE FTP LLEVA UN TIEMPO, SI SE CLICA MUY RAPIDO EN UN PRODUCTO ESTE NO CARGA LA IMAGEN			
					new HiloGetImagenes(getActivity(),productos).execute();
					
					list = RecuperarViewLista();		
									
				}
			}.execute();
		}
	
		//enganchamos el listener
		list.setOnItemClickListener(this);
		
		setHasOptionsMenu(true);
		
		return fragmentView;
	}

	
	//Metodo que salta al no implementar la interfaz
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		try {
			mListener = (OnFragmentListaListener) activity;
		} catch (ClassCastException e) {
			throw new ClassCastException(activity.toString()
					+ " Debes Implementar OnFragmentInteractionListener");
		}

	}
		
	// Interfaz para las comunicaciones con el fragmento
	public interface OnFragmentListaListener {
		// TODO: Update argument type and name
		public void onFragmentLista(Producto producto, boolean esCarrito);
	}
	
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		mListener.onFragmentLista(productos.get(position), esCarrito);
	}

	@Override
	public void onDestroy() {
		getActivity().getActionBar().setDisplayHomeAsUpEnabled(false);
		getActivity().getActionBar().setHomeButtonEnabled(false);
	    super.onDestroy();
	}
	
	public ListView RecuperarViewLista (){
		// Creamos un HashMap para mostrar los productos
        List<HashMap<String,Object>> aList = new ArrayList<HashMap<String,Object>>();
        // llenamos el hashmap con los datos de los productos
                
        for(int i=0;i<productos.size();i++){
        	HashMap<String, Object> hm = new HashMap<String,Object>();	
        	Producto p = productos.get(i);	           
            hm.put("txtNombre", p.getNombre());
            hm.put("txtDescCorta", p.getDescripcionCorta());		  	            
            hm.put("txtFabricante", p.getFabricante());
            hm.put("txtPrecio", new DecimalFormat("##.##").format(p.getPrecio().getPrecioTotal()) + " €");
			//switch para imagenes genericas
            switch (p.getCategoria()) {
			case "Ordenadores":
				hm.put("imgImagen", R.drawable.computer );
				break;
			case "Teclados":
				hm.put("imgImagen", R.drawable.keyboard );
				break;
			case "Graficas":
				hm.put("imgImagen", R.drawable.grafica );
				break;
			default:
				hm.put("imgImagen", R.drawable.nophotos );
				break;
			}
            
            //si es novedad añadimos una imagen
            if(p.isNovedad())
            	hm.put("imgNovedad", R.drawable.novedad);
            	            
            aList.add(hm);
        }
        // keys usadas en el Hashmap
        String[] keys = { "imgImagen","txtNombre","txtDescCorta","txtPrecio","txtFabricante","imgNovedad" };
        
        // Ids de las views del layout de productos
        int[] ids = { R.id.imgImagen,R.id.txtNombre,R.id.txtDescCorta,R.id.txtPrecio,R.id.txtFabricante,R.id.imgNovedad}; 
        
        //Creamos el adaptador
        SimpleAdapter adapter = new SimpleAdapter(getActivity(), aList, R.layout.listview_productos, keys, ids);	 

		list.setAdapter(adapter);		
		
		return list;
	}
	
}
package com.vaiabitapp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;

import com.vaiabitapp.FragmentListaProductos.OnFragmentListaListener;
import com.vaiabitapp.objetos.Producto;
import com.vaiabitapp.utils.HiloGetJSON;

import android.app.ActionBar;
import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
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

	public FragmentListaProductos() {
		// Required empty public constructor
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
		// inicializamos el array de productos
		productos = new ArrayList<Producto>();
		
		// Añadimos los productos al array
		new HiloGetJSON("Productos","GetProductos") {			
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
				
				// Creamos un HashMap para mostrar los productos
		        List<HashMap<String,String>> aList = new ArrayList<HashMap<String,String>>();
		        // llenamos el hashmap con los datos de los productos
		        for(int i=0;i<productos.size();i++){
		            HashMap<String, String> hm = new HashMap<String,String>();
		            hm.put("txtNombre", productos.get(i).getNombre());
		            hm.put("txtDescCorta", productos.get(i).getDescripcionCorta());
		            hm.put("imgImagen", Integer.toString(R.drawable.ic_launcher) );
		            aList.add(hm);
		        }
		        // keys usadas en el Hashmap
		        String[] keys = { "imgImagen","txtNombre","txtDescCorta" };
		        
		        // Ids de las views del layout de productos
		        int[] ids = { R.id.imgImagen,R.id.txtNombre,R.id.txtDescCorta}; 
		        
		        //Creamos el adaptador
		        SimpleAdapter adapter = new SimpleAdapter(getActivity(), aList, R.layout.listview_productos, keys, ids);	 

				list.setAdapter(adapter);
								
			}
		}.execute();
		
		//enganchamos el listener
		list.setOnItemClickListener(this);
		
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
		public void onFragmentLista(Producto producto);
	}
	
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		Toast.makeText(getActivity(), productos.get(position).getDescripcionCorta(), Toast.LENGTH_LONG).show();
		mListener.onFragmentLista(productos.get(position));
	}

	// Metodo para volver al fragment anterior
	public void volver() {
		manager.popBackStack();
	}
	
}
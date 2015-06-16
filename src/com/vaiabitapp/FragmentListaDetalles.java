package com.vaiabitapp;

import java.text.DecimalFormat;

import com.vaiabitapp.FragmentListaDetalles.OnFragmentDetallesListener;
import com.vaiabitapp.objetos.Producto;

import android.app.Activity;
import android.app.FragmentManager;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


public class FragmentListaDetalles extends Fragment {

	private FragmentManager manager;
	private OnFragmentDetallesListener mListener;
	private Producto producto; 
	private TextView txtNombre;
	private TextView txtDesc;
	private TextView txtCategoria;
	private ImageView imgImage;
	private TextView txtPrecio;
	private TextView txtFabricante;
	private ImageView imgNovedad;
	private TextView txtUnidades;
	private Button btnAñadirCarro;
	private boolean dualPane;
	private boolean esCarrito;
	
	public FragmentListaDetalles() {
		// Required empty public constructor
	}
	
	public FragmentListaDetalles(Producto producto, boolean dual, boolean esCarrito) { 
		this.producto=producto;
		this.dualPane = dual;
		this.esCarrito = esCarrito;
	}


	//cambiamos el menú 
	@Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		getActivity().getActionBar().setDisplayHomeAsUpEnabled(true); 
		if(!dualPane)
			getActivity().getActionBar().setTitle("Detalles");
    }
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {		
		if(manager.getBackStackEntryCount()>0){
			if (dualPane)
				getActivity().onBackPressed();			
			manager.popBackStack();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View fragmentView = inflater.inflate(R.layout.fragment_lista_detalles, container, false);
		manager = getFragmentManager();
		
		txtNombre = (TextView) fragmentView.findViewById(R.id.txtNombre);
		txtDesc = (TextView) fragmentView.findViewById(R.id.txtDesc);
		txtCategoria = (TextView) fragmentView.findViewById(R.id.txtCategoria);
		imgImage = (ImageView) fragmentView.findViewById(R.id.imgImagen);
		txtPrecio = (TextView) fragmentView.findViewById(R.id.txtPrecio);
		txtFabricante = (TextView) fragmentView.findViewById(R.id.txtFabricante);
		imgNovedad = (ImageView) fragmentView.findViewById(R.id.imgNovedad);
		txtUnidades = (TextView) fragmentView.findViewById(R.id.txtUnidades);
		btnAñadirCarro = (Button) fragmentView.findViewById(R.id.btnAddCarro);
		
		//Listener para añadir productos al carro o quitarlos si estamos en la vista de carrito
		if(esCarrito){
			btnAñadirCarro.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					mListener.onFragmentDetalles(producto, true);
				}
			});
		}else{
			btnAñadirCarro.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					mListener.onFragmentDetalles(producto, false);
				}
			});
		}
		
		//Cargamos el producto si es un fragment nuevo, osea no convive con la lista
		if(producto!=null){			
			txtNombre.setText(producto.getNombre());
			txtDesc.setText(producto.getDescripcion());
			txtCategoria.setText(producto.getCategoria());
			imgImage.setImageBitmap(producto.getImagen());
			txtFabricante.setText(producto.getFabricante());
			txtPrecio.setText( new DecimalFormat("##.##").format(producto.getPrecio().getPrecioTotal()) + " €");
			if(producto.isNovedad())
				imgNovedad.setImageDrawable(getResources().getDrawable(R.drawable.novedad));
			txtUnidades.setText(String.valueOf(producto.getUnidades()));
			//Ocultamos el boton de añadir al carro si no estamos logueados
			if(((VistaPrincipal)getActivity()).usuario== null){
				((ViewGroup) btnAñadirCarro.getParent()).removeView(btnAñadirCarro);
			}
			if(esCarrito){
				btnAñadirCarro.setText("Quitar del Carrito");
			}
		}
		
		setHasOptionsMenu(true);
		return fragmentView;
	}

	
	//Metodo que salta al no implementar la interfaz
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		try {
			mListener = (OnFragmentDetallesListener) activity;
		} catch (ClassCastException e) {
			throw new ClassCastException(activity.toString()
					+ " Debes Implementar OnFragmentInteractionListener");
		}
	}

	
	// Interfaz para las comunicaciones con el fragmento
	public interface OnFragmentDetallesListener {
		
		public void onFragmentDetalles(Producto producto, boolean borrar);
	}
	
		
}

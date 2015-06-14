package com.vaiabitapp;

import com.vaiabitapp.FragmentListaDetalles.OnFragmentDetallesListener;
import com.vaiabitapp.objetos.Producto;

import android.app.Activity;
import android.app.FragmentManager;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
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
	
	public FragmentListaDetalles() {
		// Required empty public constructor
	}
	
	public FragmentListaDetalles(Producto producto) {
		this.producto=producto;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View fragmentView = inflater.inflate(R.layout.fragment_lista_detalles, container, false);
		manager = getFragmentManager();
		
		txtNombre = (TextView) fragmentView.findViewById(R.id.txtNombre);
		txtDesc = (TextView) fragmentView.findViewById(R.id.txtDesc);
		txtCategoria = (TextView) fragmentView.findViewById(R.id.txtCategoria);
		imgImage = (ImageView) fragmentView.findViewById(R.id.imgImagen);
		
		//Cargamos el producto si es un fragment nuevo, osea no convive con la lista
		if(producto!=null){			
			txtNombre.setText(producto.getNombre());
			txtDesc.setText(producto.getDescripcion());
			txtCategoria.setText(producto.getCategoria());
			imgImage.setImageDrawable(fragmentView.getResources().getDrawable(R.drawable.ic_launcher));
		}
		

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
		
		public void onFragmentDetalles(Producto producto);
	}

	
}

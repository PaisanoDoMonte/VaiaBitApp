package com.vaiabitapp;

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

public class FragmentLoginRegistro extends Fragment {

	private FragmentManager manager;
	private OnFragmentInteractionListener mListener;
	private Button btnCancelar;
	private Button btnRegistrar;
	private Button btnIniciar;
	//variable que ocultará el boton de registro si es true y el de iniciar si es false
	private Boolean login;

	public FragmentLoginRegistro() {
		// Required empty public constructor
	}
	
	public FragmentLoginRegistro(Boolean login) {
		this.login = login;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View fragmentView = inflater.inflate(R.layout.fragment_login_registro, container, false);
		manager = getFragmentManager();
		
		// Añadimos los eventos de los botones
		btnCancelar = (Button) fragmentView.findViewById(R.id.btnCancelar);
		btnCancelar.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				volver();
			}
		});
		
		btnRegistrar = (Button) fragmentView.findViewById(R.id.btnRegistrar);
		btnRegistrar.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				//volver();
			}
		});
		
		btnIniciar = (Button) fragmentView.findViewById(R.id.btnIniciar);
		btnIniciar.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				volver();
			}
		});
		
		if(login)
			((ViewGroup) btnRegistrar.getParent()).removeView(btnRegistrar);
		else
			((ViewGroup) btnIniciar.getParent()).removeView(btnIniciar);

		return fragmentView;
	}

	
	//Metodo que salta al no implementar la interfaz
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		try {
			mListener = (OnFragmentInteractionListener) activity;
		} catch (ClassCastException e) {
			throw new ClassCastException(activity.toString()
					+ " Debes Implementar OnFragmentInteractionListener");
		}
	}

	
	// Interfaz para las comunicaciones con el fragmento
	public interface OnFragmentInteractionListener {
		
		public void onFragmentInteraction(Uri uri);
	}
	
	//Metodo para volver al fragment anterior
	public void volver(){
		manager.popBackStack();
	}

	
	
}

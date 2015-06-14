package com.vaiabitapp;

import com.vaiabitapp.utils.Utils;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;

public class FragmentPrincipal extends Fragment {

	FragmentManager manager;

	private OnFragmentPrincipalListener mListener;

	
	public FragmentPrincipal() {
		// Required empty public constructor
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View fragmentView = inflater.inflate(R.layout.fragment_principal, container, false);
		manager = getFragmentManager();

		// Añadimos los eventos de los botones
		Button btnRegistar = (Button) fragmentView.findViewById(R.id.btnRegistrar);
		btnRegistar.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				irRegistrar(v);
			}
		});
		Button btnLogin = (Button) fragmentView.findViewById(R.id.btnLogin);
		btnLogin.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				irLogin(v);
			}
		});
		Button btnApp = (Button) fragmentView.findViewById(R.id.btnApp);
		btnApp.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				irApp(v);
			}
		});

		return fragmentView;
	}

	//Metodo que salta al no implementar la interfaz
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		try {
			mListener = (OnFragmentPrincipalListener) activity;
		} catch (ClassCastException e) {
			throw new ClassCastException(activity.toString()
					+ " Debes implementar OnFragmentPrincipalListener");
		}
	}

	// Interfaz para las comunicaciones con el fragmento
	//TODO: PROBABLEMENTE NO HAGA FALTA, QUITAR
	public interface OnFragmentPrincipalListener {
		public void fragmentPrincipalListener();
	}

	// Metodos de los botones que permiten avanzar la app, sustituyen el layout
	// actual por un fragment

	public void irRegistrar(View View) {
		FragmentLoginRegistro fragment = new FragmentLoginRegistro(false);
		Utils.cambiarFragment(fragment,manager,R.id.containerPrincipal, "registro",true);

	}
	
	public void irLogin(View View) {
		FragmentLoginRegistro fragment = new FragmentLoginRegistro(true);
		Utils.cambiarFragment(fragment, manager,R.id.containerPrincipal, "login",true);

	}
	
	public void irApp(View View) {
		FragmentListaProductos fragment = new FragmentListaProductos();
		Utils.cambiarFragment(fragment, manager,R.id.containerPrincipal, "lista",true);
	}

}

package com.vaiabitapp;

import java.util.ArrayList;

import com.vaiabitapp.objetos.Producto;
import com.vaiabitapp.objetos.Usuario;
import com.vaiabitapp.utils.HiloReservas;
import com.vaiabitapp.utils.Utils;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.Toast;

public class FragmentPrincipal extends Fragment {

	FragmentManager manager;
	private Usuario usuario;

	private OnFragmentPrincipalListener mListener;

	
	public FragmentPrincipal(Usuario usuario) {
		this.usuario = usuario;
	}	
	

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View fragmentView = inflater.inflate(R.layout.fragment_principal, container, false);
		manager = getFragmentManager();
		
		Button btnReservar = (Button) fragmentView.findViewById(R.id.btnReservarProductos);
		Button btnMenuUsuario = (Button) fragmentView.findViewById(R.id.btnMenuUsuario);
		Button btnRegistar = (Button) fragmentView.findViewById(R.id.btnRegistrar);
		Button btnLogin = (Button) fragmentView.findViewById(R.id.btnLogin);
		Button btnApp = (Button) fragmentView.findViewById(R.id.btnApp);
		
		//Ocultamos los botones de usuario si no está logueado
		if(((VistaPrincipal)getActivity()).usuario==null){
			((ViewGroup) btnReservar.getParent()).removeView(btnReservar);
			((ViewGroup) btnMenuUsuario.getParent()).removeView(btnMenuUsuario);
		}else{
			((ViewGroup) btnLogin.getParent()).removeView(btnLogin);
			((ViewGroup) btnRegistar.getParent()).removeView(btnRegistar);
		}

		// Añadimos los eventos de los botones
		
		btnRegistar.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				irRegistrar(v);
			}
		});
		
		btnLogin.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				irLogin(v);
			}
		});
		
		btnApp.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				irApp(v);
			}
		}); 
		
		btnMenuUsuario.setOnClickListener(new OnClickListener() {
			//ir al menu de usuario
			@Override
			public void onClick(View v) {
				FragmentLoginRegistro fragment = new FragmentLoginRegistro(((VistaPrincipal)getActivity()).usuario, "menuUsuario");
				Utils.cambiarFragment(fragment, manager,R.id.containerPrincipal, "pantallaUsuario2");
				((VistaPrincipal)getActivity()).quitarFragmentDetalles();
			}
		});
		
		btnReservar.setOnClickListener(new OnClickListener() {
			//Reservar productos
			@Override
			public void onClick(View v) {
				ArrayList<Producto> productos = ((VistaPrincipal)getActivity()).productosCarrito;
				if(productos==null){
					Toast.makeText(getActivity(), "No tienes productos en el carrito", Toast.LENGTH_SHORT).show();
				}else{
					Producto p2=null;
					for(Producto p : productos){
						new HiloReservas("ProductoReservaUsuario","SetReserva", p.getId(), ((VistaPrincipal)getActivity()).usuario.getId(), 1).execute();
					}
					Toast.makeText(getActivity(), "Productos Reservados", Toast.LENGTH_SHORT).show();
					((VistaPrincipal)getActivity()).productosCarrito=null;
				}
			}
		});

		//volvemos a poner el titulo original 
		getActivity().getActionBar().setTitle("VaiaBitApp");
		
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
		FragmentLoginRegistro fragment = new FragmentLoginRegistro(usuario, "registro");
		Utils.cambiarFragment(fragment,manager,R.id.containerPrincipal, "registro");

	}
	
	public void irLogin(View View) {
		FragmentLoginRegistro fragment = new FragmentLoginRegistro(usuario, "login");
		Utils.cambiarFragment(fragment, manager,R.id.containerPrincipal, "login");

	}
	
	public void irApp(View View) {
		FragmentListaProductos fragment = new FragmentListaProductos("GetProductos");
		Utils.cambiarFragment(fragment, manager,R.id.containerPrincipal, "lista");
	}	

}

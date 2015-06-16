package com.vaiabitapp;

import org.json.JSONArray;

import com.vaiabitapp.objetos.Usuario;
import com.vaiabitapp.utils.ConexionBD;
import com.vaiabitapp.utils.HiloGetJSON;
import com.vaiabitapp.utils.HiloManejarUsuarios;
import com.vaiabitapp.utils.Utils;

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
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class FragmentLoginRegistro extends Fragment {

	private FragmentManager manager;
	private OnFragmentInteractionListener mListener;
	private Button btnCancelar;
	private Button btnRegistrar;
	private Button btnIniciar;
	private Button btnCerrarSesion;
	private Button btnActualizarUsuario;
	private EditText etNombre;
	private EditText etApellidos;
	private EditText etCorreo;
	private EditText etFechaNacimiento;
	private EditText etTelefono1;
	private EditText etTelefono2;
	private EditText etLogin;
	private EditText etPassword;
	private Usuario usuario;
	
	//variable que ocultará el boton de registro y ets si es true y el de iniciar si es false
	//tambien nos redirige a la pantalla deseada
	private String pantalla;

	public FragmentLoginRegistro() {

	}
	
	public FragmentLoginRegistro(Usuario usuario, String pantalla) {
		this.pantalla = pantalla;
		this.usuario = usuario;
	}
	
	//cambiamos el menú 
	@Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		getActivity().getActionBar().setDisplayHomeAsUpEnabled(true);
		if(usuario!=null){
			//Mandamos el usuario a la variable general de la app
			((VistaPrincipal) getActivity()).usuario = usuario;
		}
		
		switch (pantalla) {
		case "login":
			getActivity().getActionBar().setTitle("Iniciar Sesión");
			break;
		case "registro":
			getActivity().getActionBar().setTitle("Registrarse");
			break;
		case "menuUsuario":
			getActivity().getActionBar().setTitle("Menú usuario");
			break;

		}

    }
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		if(manager.getBackStackEntryCount()>0){
			getActivity().onBackPressed();			
			manager.popBackStack();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		
		View fragmentView = inflater.inflate(R.layout.fragment_login_registro, container, false);
		manager = getFragmentManager();
		
		//Cargar ets
		etNombre = (EditText) fragmentView.findViewById(R.id.etNombre);
		etApellidos= (EditText) fragmentView.findViewById(R.id.etApellidos);
		etCorreo= (EditText) fragmentView.findViewById(R.id.etCorreo);
		etFechaNacimiento= (EditText) fragmentView.findViewById(R.id.etFechaNacimiento);
		etTelefono1= (EditText) fragmentView.findViewById(R.id.etTelefono1);
		etTelefono2= (EditText) fragmentView.findViewById(R.id.etTelefono2);	
		etLogin = (EditText) fragmentView.findViewById(R.id.etLogin);
		etPassword = (EditText) fragmentView.findViewById(R.id.etPassword);
		btnIniciar = (Button) fragmentView.findViewById(R.id.btnIniciar);
		btnCancelar = (Button) fragmentView.findViewById(R.id.btnCancelar);
		btnRegistrar = (Button) fragmentView.findViewById(R.id.btnRegistrar);
		btnActualizarUsuario = (Button) fragmentView.findViewById(R.id.btnActualizarUsuario);
		btnCerrarSesion = (Button) fragmentView.findViewById(R.id.btnCerrarSesion);
		
		//Ocultar campos que no correspondan con la ventana pedida y añadir listeners
		switch (pantalla) {
		case "login":
			((ViewGroup) btnRegistrar.getParent()).removeView(btnRegistrar);
			((ViewGroup) btnActualizarUsuario.getParent()).removeView(btnActualizarUsuario);
			((ViewGroup) btnCerrarSesion.getParent()).removeView(btnCerrarSesion);
			((ViewGroup) etNombre.getParent()).removeView(etNombre);
			((ViewGroup) etApellidos.getParent()).removeView(etApellidos);
			((ViewGroup) etCorreo.getParent()).removeView(etCorreo);
			((ViewGroup) etFechaNacimiento.getParent()).removeView(etFechaNacimiento);
			((ViewGroup) etTelefono1.getParent()).removeView(etTelefono1);
			((ViewGroup) etTelefono2.getParent()).removeView(etTelefono2);
			
			//Si el usuario está registrado logueamos
			btnIniciar.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					
					new HiloGetJSON("Usuarios","GetUsuario",etLogin.getText().toString(),etPassword.getText().toString()) {
						
						@Override
						protected void onPostExecute(JSONArray result) {
							Usuario usuario = new Usuario(result);
							if(usuario.getId() != 0){
								Toast.makeText(getActivity(), "Login correcto", Toast.LENGTH_LONG).show();	
								FragmentLoginRegistro fragment = new FragmentLoginRegistro(usuario, "menuUsuario");
								Utils.cambiarFragment(fragment, manager,R.id.containerPrincipal, "Login");
							}
							else
								Toast.makeText(getActivity(), "Has introducido mal el login o la contraseña", Toast.LENGTH_LONG).show();	
						}
					}.execute();
				}
			});
			// Añadimos los eventos de los botones
			
			btnCancelar.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					manager.popBackStack();
				}
			});
					
			break;
		case "registro":
			((ViewGroup) btnIniciar.getParent()).removeView(btnIniciar);
			((ViewGroup) btnActualizarUsuario.getParent()).removeView(btnActualizarUsuario);
			((ViewGroup) btnCerrarSesion.getParent()).removeView(btnCerrarSesion);
			
			//Iniciar insercion de usuario ---------------------------
			btnRegistrar.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					
					new HiloManejarUsuarios("Usuarios", "SetUsuario", etLogin.getText().toString(), etPassword.getText().toString(),etNombre.getText().toString(),
							etApellidos.getText().toString(), etCorreo.getText().toString(),etFechaNacimiento.getText().toString(),etTelefono1.getText().toString(),
							etTelefono2.getText().toString(), true){
						@Override
						protected void onPostExecute(String result) {
							Toast.makeText(getActivity(), result, Toast.LENGTH_LONG).show();
							
						}
						
					}.execute();
				}
			});		
			// Añadimos los eventos de los botones
			btnCancelar = (Button) fragmentView.findViewById(R.id.btnCancelar);
			btnCancelar.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					manager.popBackStack();
				}
			});
					
			break;
		case "menuUsuario":
			((ViewGroup) btnIniciar.getParent()).removeView(btnIniciar);
			((ViewGroup) btnRegistrar.getParent()).removeView(btnRegistrar);
			((ViewGroup) btnCancelar.getParent()).removeView(btnCancelar);
			// Al ser el menu de usuario aprovechamos a rellenar los datos
			etLogin.setText(usuario.getLogin());
			etPassword.setText(usuario.getPassword());
			etNombre.setText(usuario.getNombre());
			etApellidos.setText(usuario.getApellidos());
			etCorreo.setText(usuario.getCorreo());
			etFechaNacimiento.setText(usuario.getFechaNacimiento());
			etTelefono1.setText(usuario.getTelefono1());
			etTelefono2.setText(usuario.getTelefono2());
			
			//Boton para actualizar los datos del usuario
			btnActualizarUsuario.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					new HiloManejarUsuarios("Usuarios","UpdateUsuario", usuario.getId(), etLogin.getText().toString(), etPassword.getText().toString(),etNombre.getText().toString(),
							etApellidos.getText().toString(), etCorreo.getText().toString(),etFechaNacimiento.getText().toString(),etTelefono1.getText().toString(),
							etTelefono2.getText().toString(), false) {
						
						@Override
						protected void onPostExecute(String result) {
							Toast.makeText(getActivity(), result, Toast.LENGTH_LONG).show();	
							btnCerrarSesion.performClick();
						}
					}.execute();
				}
			});
			
			
			//Boton para cerrar la sesion del usuario
			btnCerrarSesion.setOnClickListener(new OnClickListener() {			
				@Override
				public void onClick(View v) {
					Toast.makeText(getActivity(), "Sesión Cerrada", Toast.LENGTH_LONG).show();	
					Usuario usuario = null;
					FragmentPrincipal fragment = new FragmentPrincipal(usuario);
					Utils.cambiarFragment(fragment, manager,R.id.containerPrincipal, "Principal");
					((VistaPrincipal) getActivity()).usuario = usuario;
				}
			});
			break;
		}
		
		setHasOptionsMenu(true);
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
	
	@Override
	protected Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return super.clone();
	}
	
	
	@Override
	public void onDestroy() {
		getActivity().getActionBar().setDisplayHomeAsUpEnabled(false);
		getActivity().getActionBar().setHomeButtonEnabled(false);
	    super.onDestroy();
	}
	
	
}

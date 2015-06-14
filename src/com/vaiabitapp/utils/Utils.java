package com.vaiabitapp.utils;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.TransactionTooLargeException;

import com.vaiabitapp.R;

public class Utils {
	// Convertir 1 y 0 a true y false
	public static Boolean toBoolean(int integer) {
		if (integer == 1)
			return true;
		else
			return false;
	}

	// Metodo generico para cambiar los fragment a pantalla completa
	public static void cambiarFragment(Fragment fragment, FragmentManager manager, int container, String tag, boolean anterior) {
		FragmentTransaction transaction = manager.beginTransaction();
		transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
		transaction.replace(container, (Fragment) fragment, tag);
		if (anterior)
			transaction.addToBackStack("irAnterior");
		transaction.commit();
	}
	
	// Metodo generico para abrir los fragment a pantalla completa
		public static void abrirFragment(Fragment fragment, FragmentManager manager, int container, String tag, boolean anterior) {
			FragmentTransaction transaction = manager.beginTransaction();
			transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
			transaction.add(container, (Fragment) fragment, tag);
			if (anterior)
				transaction.addToBackStack("irAnterior");
			transaction.commit();
		}
	
	// Metodo generico para cambiar los fragment a pantalla completa
	public static void borrarFragment(Fragment fragment, FragmentManager manager) {
		FragmentTransaction transaction = manager.beginTransaction();
		transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
		transaction.remove(fragment);
		transaction.commit();
	}
}

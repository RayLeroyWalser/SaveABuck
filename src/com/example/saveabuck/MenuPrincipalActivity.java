package com.example.saveabuck;

import com.example.saveabuck.R;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class MenuPrincipalActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu_principal);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_principal, menu);
		return true;
	}
	
	/** Called when the user clicks the Categorias button */
	public void buttonCategoriasClicked(View view) {
	    Intent intent = new Intent(this, CategoriasActivity.class);
	    startActivity(intent);		
	}

	/** Called when the user clicks the Contas button */
	public void buttonContasClicked(View view) {
	    Intent intent = new Intent(this, ContasActivity.class);
	    startActivity(intent);		
	}	

	/** Called when the user clicks the Lancamento button */
	public void buttonLancamentoClicked(View view) {
	    Intent intent = new Intent(this, LancamentoActivity.class);
	    startActivity(intent);		
	}	

	/** Called when the user clicks the Contas button */
	public void buttonClicked(View view) {
		
	}	
}

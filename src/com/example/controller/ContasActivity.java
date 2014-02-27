package com.example.controller;

import java.util.ArrayList;

import com.example.controller.db.ControllerData;
import com.example.controller.model.Contas;

import android.os.Bundle;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

public class ContasActivity extends Activity {

	private EditText editText;
	private ListView listView;
	protected ControllerData DB;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		// Put the back icon on the action bar
		ActionBar actionBar = getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		
		setContentView(R.layout.activity_contas);
		
		// Initialize the DB
		
		DB = new ControllerData(ContasActivity.this);
		
		// Get the Activity elements
		editText = (EditText)findViewById(R.id.editTextContas);
		listView = (ListView)findViewById(R.id.listViewContas);
		
		populateListBox();		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.contas, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    switch (item.getItemId()) {
	        case android.R.id.home:
	            // app icon in action bar clicked; go home
	            Intent intent = new Intent(this, MenuPrincipalActivity.class);
	            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
	            startActivity(intent);
	            return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}	
	
	/** Called when the user clicks the + button */
	public void buttonAdd(View view) {
		String nomeConta = this.editText.getText().toString().trim();
		
		Contas conta = new Contas(nomeConta);
		
		DB.storeConta(conta);
		
		populateListBox();
	}
	
	public void populateListBox() {
		ArrayList<Contas> contas = DB.getContas();
		ArrayList<String> nomes = new ArrayList<String>();
		
		for(int count = 0; count < contas.size(); count++) {
			nomes.add(contas.get(count).getTitle());
		}
		
	    ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, nomes);
	    listView.setAdapter(adapter);
	}
		
}

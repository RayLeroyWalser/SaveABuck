package com.example.saveabuck;

import java.util.ArrayList;

import com.example.saveabuck.R;
import com.example.saveabuck.db.SaveABuckData;
import com.example.saveabuck.model.Envelope;

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

public class EnvelopeActivity extends Activity {

	private ListView listView;
	protected SaveABuckData DB;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		// Put the back icon on the action bar
		ActionBar actionBar = getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		
		setContentView(R.layout.activity_contas);
		
		// Initialize the DB
		
		DB = new SaveABuckData(EnvelopeActivity.this);
		
		// Get the Activity elements
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
	        	return goBackToMainMenu();
	        case R.id.action_add_conta:
	        	return launchAddEnvelopes();
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}	
	
	public void populateListBox() {
		ArrayList<Envelope> contas = DB.getEnvelopes();
		ArrayList<String> nomes = new ArrayList<String>();
		
		for(int count = 0; count < contas.size(); count++) {
			nomes.add(contas.get(count).getTitle());
		}
		
	    ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, nomes);
	    listView.setAdapter(adapter);
	}
	
	
	public boolean goBackToMainMenu() {
        // app icon in action bar clicked; go home
        Intent intent = new Intent(this, MainMenuActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        return true;
	}		
	
	public boolean launchAddEnvelopes() {
	    Intent intent = new Intent(this, AddEnvelope.class);
	    startActivity(intent);
		return true;
	}	
		
}

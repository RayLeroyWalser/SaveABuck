package com.caux.saveabuck;

import com.caux.saveabuck.db.SaveABuckData;
import com.caux.saveabuck.model.Group;
import com.caux.saveabuck.model.Transaction;
import com.example.saveabuck.R;
import com.example.saveabuck.R.layout;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager.LayoutParams;
import android.widget.EditText;

public class AddGroupActivity extends Activity {
	protected SaveABuckData DB;
	protected EditText editText;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_group);
		
		// Initialize the DB
		DB = new SaveABuckData(AddGroupActivity.this);
		
		// Get the resources
		editText = (EditText) findViewById(R.id.editTextValue);
		
        // Request focus and show soft keyboard automatically
		editText.requestFocus();
        this.getWindow().setSoftInputMode(LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        
        
	}
	
	/** Called when the user clicks the Ok button */
	public void buttonAddGroup(View view) {
		String groupName = editText.getText().toString();
		// TODO Get the group color
		
		Group newGroup = new Group(groupName, 0);
		
		DB.storeGroup(newGroup);
	    this.finish();			
	}	
	
	/** Called when the user clicks the Cancel button */
	public void buttonCancel(View view) {
	    this.finish();	
	}	
}

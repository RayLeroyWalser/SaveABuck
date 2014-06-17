package com.caux.saveabuck;

import java.text.NumberFormat;

import com.caux.saveabuck.db.SaveABuckData;
import com.caux.saveabuck.model.Group;
import com.caux.saveabuck.model.Transaction;
import com.example.saveabuck.R;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager.LayoutParams;
import android.widget.EditText;

public class AddGroupActivity extends Activity {
	protected SaveABuckData DB;
	protected EditText editText;
	protected String groupToEditAsString;

	
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
     
		// In the case this is editing existing Transactions we need to know which one we're editing
		Bundle extraVariables = getIntent().getExtras();
        if(extraVariables != null)
        {
        	groupToEditAsString = extraVariables.getString("groupToEdit");
        	
            if(groupToEditAsString != null)
            {
            	Group groupToEdit = DB.getGroup(Integer.parseInt(groupToEditAsString));
            	
            	editText.setText(groupToEdit.getTitle());
            }
        }        
        
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

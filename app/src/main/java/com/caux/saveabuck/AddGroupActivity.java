package com.caux.saveabuck;

import com.caux.saveabuck.colorpicker.Colorpicker;
import com.caux.saveabuck.db.SaveABuckData;
import com.caux.saveabuck.model.Group;
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
	private Colorpicker colorPicker;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_group);
		
		// Initialize the DB
		DB = new SaveABuckData(AddGroupActivity.this);
		
		// Get the resources
		editText = (EditText) findViewById(R.id.editTextValue);
	    colorPicker  = (Colorpicker) findViewById(R.id.Colorpicker);

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
		Integer groupColor = colorPicker.getSelectedColor();
		
		if(groupName.equals("")) {
			//TODO show the user he needs to enter a name!
		}
		else if (groupColor == -1) {
			colorPicker.showNoSelection();
		}
		else {
			Group newGroup = new Group(groupName, groupColor);
			
			DB.storeGroup(newGroup);
		    this.finish();
		}
	}	
	
	/** Called when the user clicks the Cancel button */
	public void buttonCancel(View view) {
	    this.finish();	
	}
	
	
	
	
}

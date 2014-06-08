package com.caux.saveabuck;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;

import com.caux.saveabuck.db.SaveABuckData;
import com.caux.saveabuck.model.Group;
import com.caux.saveabuck.model.Transaction;
import com.example.saveabuck.R;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager.LayoutParams;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.content.res.Configuration;
import android.util.Log;

public class AddTransactionActivity extends Activity {
	
	protected SaveABuckData DB;
	protected EditText editText;
	protected String transactionToEditAsString;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_transaction);
		
		// Initialize the DB
		DB = new SaveABuckData(AddTransactionActivity.this);		

		// Get the resources
		editText = (EditText) findViewById(R.id.editTextValue);
		
        // Request focus and show soft keyboard automatically
		editText.requestFocus();
        this.getWindow().setSoftInputMode(LayoutParams.SOFT_INPUT_STATE_VISIBLE);		
		
		// In the case this is editing existing Transactions we need to know which one we're editing
		Bundle extraVariables = getIntent().getExtras();
        if(extraVariables != null)
        {
        	transactionToEditAsString = extraVariables.getString("transactionToEdit");
        	
            if(transactionToEditAsString != null)
            {
            	Transaction transactionToEdit = DB.getTransaction(Integer.parseInt(transactionToEditAsString));
            	
            	editText.setText(NumberFormat.getCurrencyInstance().format(transactionToEdit.getValue()));
            }
        }
        else
        {
        	editText.setText(NumberFormat.getCurrencyInstance().format(0/100));
        }

        // Set cursor to the end of the Edit Text
        editText.setSelection(editText.getText().length());
        
        // Set a Textwatcher to format the input
        editText.setRawInputType(Configuration.KEYBOARD_12KEY); 
        addTransactionFormatter();   
        
        // Populate Choose Group listbox
        populateGroupListBox();		
	}
	
	/** Called when the user clicks the Ok button */
	public void buttonAddTransaction(View view) {
		Double value = Double.valueOf(editText.getText().toString().replaceAll("[$]", ""));
		// TODO Get the group ID
		
		Transaction newTransaction = new Transaction(0, 0, value);
		
		DB.storeTransaction(newTransaction);
	    this.finish();			
	}	
	
	/** Called when the user clicks the Cancel button */
	public void buttonCancel(View view) {
	    this.finish();	
	}
	
    public void addTransactionFormatter() {
    	editText.addTextChangedListener(new TextWatcher(){
	        private String current = "";
	        
	        
	        @Override
	        public void beforeTextChanged(CharSequence s, int start,
	                int count, int after) {
	        }
	        
	        
	        @Override
	        public void afterTextChanged(Editable s) {
	            if(!s.toString().equals(current)) {
	            	editText.removeTextChangedListener(this);

	            	String cleanString = s.toString().replaceAll("[$,.]", "");

	            	double parsed = Double.parseDouble(cleanString);
	            	String formated = NumberFormat.getCurrencyInstance().format((parsed/100));

	            	current = formated;
	            	editText.setText(formated);
	            	editText.setSelection(formated.length());

	            	editText.addTextChangedListener(this);
	            }
	        }


			@Override
			public void onTextChanged(CharSequence arg0, int arg1, int arg2,
					int arg3) {
				
			}
    	});
    }
    
	public void populateGroupListBox() {		
        SaveABuckData DB = new SaveABuckData(this);        

		ArrayList<Group> groups = DB.getGroups();
		ArrayList<String> values = new ArrayList<String>();

		for(int count = 0; count < groups.size(); count++) {
			// TODO Format colors
			values.add(groups.get(count).getTitle());
		}
		values.add("+");

	    ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, values);
	    
		// Get the Activity elements
        ListView listView = (ListView) this.findViewById(R.id.grouplist);	    
	    listView.setAdapter(adapter);
	}    
}

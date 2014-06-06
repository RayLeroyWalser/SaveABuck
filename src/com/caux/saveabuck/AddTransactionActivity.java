package com.caux.saveabuck;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import com.caux.saveabuck.db.SaveABuckData;
import com.caux.saveabuck.model.Transaction;
import com.example.saveabuck.R;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager.LayoutParams;
import android.widget.EditText;
import android.content.res.Configuration;
import android.util.Log;

public class AddTransactionActivity extends Activity {
	
	protected SaveABuckData DB;
	protected EditText editText;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_transaction);
		
		Bundle extraVariables = getIntent().getExtras();
        if(extraVariables != null)
        {
        	String transactionToEdit = extraVariables.getString("transactionToEdit");
        	
        	Log.d("AddTransaction", transactionToEdit);

        }
		
		
		// Initialize the DB
		DB = new SaveABuckData(AddTransactionActivity.this);
		
        // Request focus and show soft keyboard automatically
		editText = (EditText) findViewById(R.id.editTextValue);
		editText.requestFocus();
        this.getWindow().setSoftInputMode(LayoutParams.SOFT_INPUT_STATE_VISIBLE);	
        

        // Set a Textwatcher to format the input
        editText.setRawInputType(Configuration.KEYBOARD_12KEY); 
        addTransactionFormatter();        
        
		
	}
	
	/** Called when the user clicks the Ok button */
	public void buttonAddTransaction(View view) {
		EditText editText = (EditText) findViewById(R.id.editTextValue);
		
		
		Double value = Double.valueOf(editText.getText().toString().replaceAll("[$]", ""));
		
		Transaction aTransaction = new Transaction(0, 0, value);
		
		DB.storeTransaction(aTransaction);
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
}

package com.caux.saveabuck;

import com.caux.saveabuck.db.SaveABuckData;
import com.caux.saveabuck.model.Transaction;
import com.example.saveabuck.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager.LayoutParams;
import android.widget.EditText;

public class AddTransactionActivity extends Activity {
	
	protected SaveABuckData DB;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_transaction);
		
		// Initialize the DB
		DB = new SaveABuckData(AddTransactionActivity.this);
		
        // Request focus and show soft keyboard automatically
		EditText editText = (EditText) findViewById(R.id.editTextValue);
		editText.requestFocus();
        this.getWindow().setSoftInputMode(LayoutParams.SOFT_INPUT_STATE_VISIBLE);		
		
	}
	
	/** Called when the user clicks the Ok button */
	public void buttonAddTransaction(View view) {
		EditText editText = (EditText) findViewById(R.id.editTextValue);
		
		
		Double value = Double.valueOf(editText.getText().toString());
		
		Transaction aTransaction = new Transaction(0, 0, value);
		
		DB.storeTransaction(aTransaction);
	    this.finish();			
	}	
	
	/** Called when the user clicks the Cancel button */
	public void buttonCancel(View view) {
	    this.finish();	
	}		
	
}

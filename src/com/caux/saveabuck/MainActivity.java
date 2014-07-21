package com.caux.saveabuck;

import com.caux.saveabuck.fragments.TransactionListviewFragment;
import com.example.saveabuck.R;

import android.os.Bundle;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.View;


public class MainActivity extends FragmentActivity {
    /** Called when the activity is first created. */

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_main, menu);
		return true;	
	}
	
	@Override
	public void onResume()
	{
		super.onResume();
		
		TransactionListviewFragment transactionListView = (TransactionListviewFragment) getFragmentManager().findFragmentById(R.id.transactionListviewFragment);		
		transactionListView.populateTransactionListBox();		
	}
	
	
	/** Called when the user clicks the + button */
	public void buttonAddTransaction(View view) {
	    Intent intent = new Intent(this, AddTransactionActivity.class);
	    startActivity(intent);		
	}	
	
}

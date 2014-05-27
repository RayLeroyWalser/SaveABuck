package com.example.saveabuck;

import com.example.saveabuck.R;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class AddConta extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_conta);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add_conta, menu);
		return true;
	}

}

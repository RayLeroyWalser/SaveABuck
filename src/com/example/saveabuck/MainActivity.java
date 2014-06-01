package com.example.saveabuck;

import com.example.saveabuck.R;

import android.os.Bundle;
import android.content.res.Resources;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;

import com.example.saveabuck.piechart.PieChart;

public class MainActivity extends FragmentActivity {
    /** Called when the activity is first created. */
    float values[]={300,400,100,500};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
//		FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

		
//        Resources res = getResources();

//        final PieChart pie = (PieChart) this.findViewById(R.id.Pie);
//        pie.addItem("Agamemnon", 2, res.getColor(R.color.seafoam));
//        pie.addItem("Bocephus", 3.5f, res.getColor(R.color.chartreuse));
//        pie.addItem("Calliope", 2.5f, res.getColor(R.color.emerald));
//        pie.addItem("Daedalus", 3, res.getColor(R.color.bluegrass));
//        pie.addItem("Euripides", 1, res.getColor(R.color.turquoise));
//        pie.addItem("Ganymede", 3, res.getColor(R.color.slate));

	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_main, menu);
		return true;
	}
}

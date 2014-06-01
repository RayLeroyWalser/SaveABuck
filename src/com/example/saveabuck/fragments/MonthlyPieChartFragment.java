package com.example.saveabuck.fragments;

import com.example.saveabuck.R;
import com.example.saveabuck.piechart.PieChart;

import android.app.Fragment;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MonthlyPieChartFragment extends Fragment {
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

		Resources res = getResources();
		
		final PieChart pie = (PieChart) getActivity().findViewById(R.id.Pie);
		pie.addItem("Agamemnon", 2, res.getColor(R.color.seafoam));
		pie.addItem("Bocephus", 3.5f, res.getColor(R.color.chartreuse));
		pie.addItem("Calliope", 2.5f, res.getColor(R.color.emerald));
		pie.addItem("Daedalus", 3, res.getColor(R.color.bluegrass));
		pie.addItem("Euripides", 1, res.getColor(R.color.turquoise));
		pie.addItem("Ganymede", 3, res.getColor(R.color.slate));

    }	
	
	
	@Override	
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.piechart_fragment_month, container, false);		
		
		return view;
		
		
		
		
	}
}

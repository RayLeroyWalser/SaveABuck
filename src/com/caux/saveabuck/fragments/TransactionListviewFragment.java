package com.caux.saveabuck.fragments;

import java.util.ArrayList;

import com.caux.saveabuck.db.SaveABuckData;
import com.caux.saveabuck.model.Transaction;
import com.example.saveabuck.R;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class TransactionListviewFragment extends Fragment {
	
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
             

    }	
	
	
	@Override	
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_transaction_listview, container, false);		
		
		return view;
		
		
		
		
	}
	
	public void populateListBox() {		
        SaveABuckData DB = new SaveABuckData(this.getActivity());        

		ArrayList<Transaction> transactions = DB.getTransactions();
		ArrayList<String> values = new ArrayList<String>();

		for(int count = 0; count < transactions.size(); count++) {
			values.add(transactions.get(count).getValue().toString());
		}

	    ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_list_item_1, values);
	    
		// Get the Activity elements
        ListView listView = (ListView)this.getActivity().findViewById(R.id.transactionlist);	    
	    listView.setAdapter(adapter);
	}	
}

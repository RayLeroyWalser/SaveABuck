package com.caux.saveabuck.fragments;

import java.util.ArrayList;

import android.widget.AdapterView.OnItemClickListener;

import com.caux.saveabuck.AddTransactionActivity;
import com.caux.saveabuck.MainActivity;
import com.caux.saveabuck.db.SaveABuckData;
import com.caux.saveabuck.model.Transaction;
import com.example.saveabuck.R;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class TransactionListviewFragment extends Fragment {
	protected ListView listView;
	ArrayList<Transaction> transactions;
		
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
             
        listView = (ListView) this.getActivity().findViewById(R.id.transactionlist);
        
        populateTransactionListBox();
        addListViewClickHandler();

    }	
	
	
	@Override	
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_transaction_listview, container, false);		
		
		return view;
		
		
		
		
	}
	
	public void populateTransactionListBox() {		
        SaveABuckData DB = new SaveABuckData(this.getActivity());        

		transactions = DB.getTransactions();
		ArrayList<String> values = new ArrayList<String>();

		for(int count = 0; count < transactions.size(); count++) {
			// TODO Get real money unit from Settings
			String formatedString = "R$" + String.format("%.2f", transactions.get(count).getValue());
			values.add(formatedString);
		}

	    ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_list_item_1, values);
	    
		// Get the Activity elements
        //ListView listView = (ListView)this.getActivity().findViewById(R.id.transactionlist);	    
	    listView.setAdapter(adapter);
	}
	
    public void addListViewClickHandler() {
    	
    	listView.setOnItemClickListener(new OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
    	    Intent intent = new Intent(TransactionListviewFragment.this.getActivity(), AddTransactionActivity.class);
    	    intent.putExtra("transactionToEdit", transactions.get(position).getId().toString());
    	    startActivity(intent);	        	
            }
    	});
    }
}

package com.caux.saveabuck.fragments;

import java.util.ArrayList;

import com.caux.saveabuck.AddTransactionActivity;
import com.caux.saveabuck.db.SaveABuckData;
import com.caux.saveabuck.model.Group;
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
import android.widget.AdapterView.OnItemClickListener;

public class GroupListviewFragment extends Fragment {
	protected ListView listView;
	protected ArrayList<Group> groups;
	protected SaveABuckData DB;
	
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
               
        // Get the resources
        listView = (ListView) this.getActivity().findViewById(R.id.grouplist);
        
        // Initialize the DB
        DB = new SaveABuckData(this.getActivity()); 
        
        populateGroupListBox();
        addListViewClickHandler();

    }	
	
	
	@Override	
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_group_listview, container, false);		
		
		return view;
	}
	
	public void populateGroupListBox() {		
		groups = DB.getGroups();
		ArrayList<String> values = new ArrayList<String>();

		for(int count = 0; count < groups.size(); count++) {
			// TODO Format colors
			values.add(groups.get(count).getTitle());
		}
		values.add("+");

	    ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_list_item_1, values);
	    
		// Get the Activity elements
        ListView listView = (ListView) this.getActivity().findViewById(R.id.grouplist);	    
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

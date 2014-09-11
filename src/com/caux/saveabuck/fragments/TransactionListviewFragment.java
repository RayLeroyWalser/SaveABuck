package com.caux.saveabuck.fragments;

import java.text.DateFormat;
import java.util.List;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.caux.saveabuck.AddTransactionActivity;
import com.caux.saveabuck.db.SaveABuckData;
import com.caux.saveabuck.model.Group;
import com.caux.saveabuck.model.Transaction;
import com.example.saveabuck.R;

public class TransactionListviewFragment extends Fragment {
	protected ListView listView;
	protected TransactionAdapter transactions;
	protected SaveABuckData DB;
	
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        
        // Get the resources
        listView = (ListView) this.getActivity().findViewById(R.id.transactionlist);
        
        // Initialize the DB
        DB = new SaveABuckData(this.getActivity());

        addListViewClickHandler();
    }	
		
	@Override	
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_transaction_listview, container, false);		
		
		return view;		
	}
	
	public void populateTransactionListBox() {		
		transactions = new TransactionAdapter(this.getActivity(), android.R.layout.simple_list_item_1, DB.getTransactions());	    
	    listView.setAdapter(transactions);
	}
	
    public void addListViewClickHandler() {  	
    	listView.setOnItemClickListener(new OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
    	    Intent intent = new Intent(TransactionListviewFragment.this.getActivity(), AddTransactionActivity.class);
    	    intent.putExtra("transactionToEdit", transactions.getItem(position).getId().toString());
    	    startActivity(intent);	        	
            }
    	});
    }
    
    public class TransactionAdapter extends ArrayAdapter<Transaction> {
    	
    	public TransactionAdapter(Context context, int textViewResourceId) {
    	    super(context, textViewResourceId);
    	}

    	public TransactionAdapter(Context context, int resource, List<Transaction> items) {
    	    super(context, resource, items);
    	}

    	@Override
    	public View getView(int position, View convertView, ViewGroup parent) {
    	    View v = convertView;
    	    if (v == null) {
    	    	LayoutInflater vi;
    	        vi = LayoutInflater.from(getContext());
    	        v = vi.inflate(R.layout.row_transaction_listview, null);
    	    }

    	    Transaction transaction = getItem(position);
    	    if (transaction != null) {
    	        TextView valueTextView = (TextView) v.findViewById(R.id.transactionvalue);
    	        if (valueTextView != null) {
    				String formatedValue = "R$" + String.format("%.2f", transaction.getValue());
    				valueTextView.setText(formatedValue);
    	        }
    	        
    	        TextView groupTextView = (TextView) v.findViewById(R.id.transactiongroup);
    	        if (groupTextView != null) {
    	        	Group group = DB.getGroup(transaction.getGroup());
    	        	if(group != null) {
    	        		groupTextView.setText(group.getTitle());
    	        		groupTextView.setTextColor(group.getColor());
    	        	}
    	        }
    	        
    	        TextView dateTextView = (TextView) v.findViewById(R.id.transactiondate);
    	        if (dateTextView != null) {
    	        	DateFormat dateFormat = DateFormat.getDateTimeInstance();
    	        	String formatedDate = dateFormat.format(transaction.getTimestamp());
    	        	dateTextView.setText(formatedDate);
    	        }    	        
    	    }
    	    
    	    return v;
    	}
    }      
}

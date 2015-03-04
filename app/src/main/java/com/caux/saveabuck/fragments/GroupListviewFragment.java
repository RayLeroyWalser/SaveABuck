package com.caux.saveabuck.fragments;

import java.util.ArrayList;
import java.util.List;

import com.caux.saveabuck.AddGroupActivity;
import com.caux.saveabuck.db.SaveABuckData;
import com.caux.saveabuck.model.Group;
import com.example.saveabuck.R;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;

public class GroupListviewFragment extends Fragment {
	protected ListView listView;
	protected SaveABuckData DB;
	protected Integer selectedID = -1;
	protected GroupAdapter adapter;
	
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
               
        // Get the resources
        listView = (ListView) this.getActivity().findViewById(R.id.grouplist);
        
        // Initialize the DB
        DB = new SaveABuckData(this.getActivity());
                
        // Add click handler
        addListViewClickHandler();
    }	
	
	
	@Override	
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_group_listview, container, false);		
		
		return view;
	}
 
    public void showNoGroupSelected() {
    	adapter.showNoSelection();    	
    }


	public Integer getSelectedID() {
		return selectedID;
	}    
    
	public void populateGroupListBox() {
		// Create a temporary group adapter with the '+' group in it
		ArrayList<Group> groups = new ArrayList<Group>();
		Group addGroup = new Group("+", getResources().getColor(R.color.black));
		groups.add(addGroup);
		groups.addAll(DB.getGroups());

		// Now we create the official customized adapter, populate it with a the temporary adapter and connect the listview to it.
		adapter = new GroupAdapter(this.getActivity(), android.R.layout.simple_list_item_1, groups);
        ListView listView = (ListView) this.getActivity().findViewById(R.id.grouplist);	    
	    listView.setAdapter(adapter);
	}
	
    public void addListViewClickHandler() {  	
    	listView.setOnItemClickListener(new OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
	        	Group group = (Group) parent.getItemAtPosition(position);
	        	if(group.getTitle().equals("+")) {
	        	    Intent intent = new Intent(GroupListviewFragment.this.getActivity(), AddGroupActivity.class);
	        	    startActivity(intent);          		
	        	}
	        	else {
	       	        selectedID = group.getId();
	       	        
	        		TextView tt = (TextView) view.findViewById(R.id.groupname);
	    	        if (tt != null) {
	    	        	adapter.setHasSelection();
	            		adapter.notifyDataSetChanged();

	    	        	Animation animation = AnimationUtils.loadAnimation(view.getContext(), R.anim.wave_scale);
	        	    	animation.setDuration(100);
	        	    	view.startAnimation(animation);
	    	        }
	 	        		
	        	}        	
            }
    	});
    }	
	
    public class GroupAdapter extends ArrayAdapter<Group> {
    	private Boolean animatingViews = false;
    	
    	public GroupAdapter(Context context, int textViewResourceId) {
    	    super(context, textViewResourceId);
    	}

    	public GroupAdapter(Context context, int resource, List<Group> items) {
    	    super(context, resource, items);
    	}

    	@Override
    	public View getView(int position, View convertView, ViewGroup parent) {
    	    View v = convertView;
    	    if (v == null) {
    	    	LayoutInflater vi;
    	        vi = LayoutInflater.from(getContext());
    	        v = vi.inflate(R.layout.row_group_listview, null);
    	    }

    	    Group group = getItem(position);
    	    if (group != null) {
    	        TextView tt = (TextView) v.findViewById(R.id.groupname);
    	        if (tt != null) {
    	            tt.setText(group.getTitle());
    	            tt.setTextColor(group.getColor());
    	            
    	            Integer selectedID = GroupListviewFragment.this.selectedID;
    	            if((group.getId() != -1) && (group.getId() == selectedID)) {
    	            	tt.setBackgroundColor(getResources().getColor(R.color.light_gray));
    	            } else {
    	            	tt.setBackgroundColor(getResources().getColor(R.color.white));
    	            }
    	        }
    	    }

    	    if(animatingViews) {  
    	    	Animation animation = AnimationUtils.loadAnimation(getContext(), R.anim.shake);;
    	    	animation.setDuration(200);
    	    	convertView.startAnimation(animation);  	    	
    	    }
    	    
    	    return v;

    	}
    	
    	public void showNoSelection() {
    		animatingViews = true;
    		notifyDataSetChanged();
    	}
    	
    	public void setHasSelection() {
    		animatingViews = false;
    	}
    }   
}

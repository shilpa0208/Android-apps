package com.example.user.a2;

import android.app.Activity;
import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class ChiStateFragment extends ListFragment {

    //Obtain the reference of the listener
    private ListSelectionListener chiListerner=null;

    //Declares an interface for list selection action
    public interface ListSelectionListener{
        public  void onListSelection(int pos);
    }

    //Specifies the action to be taken on selecting an item in the list
    @Override
    public void  onListItemClick(ListView l,View v,int pos,long id)
    {
        //checks the item that is selected
        getListView().setItemChecked(pos, true);
        chiListerner.onListSelection(pos);
    }

    //Attaches the fragment to the activity
    @Override
    public void onAttach(Activity activity)
    {
        super.onAttach(activity);
        //helps to keep the selected item to be rememebred on any change in configuration
        setRetainInstance(true);
        chiListerner=(ListSelectionListener)activity;
    }

    //Used to inflate the view with the given xml file.
    @Override
    public  View onCreateView(LayoutInflater inflater,ViewGroup view, Bundle savedInstanceState)
    {
        //inflates the view with the given layout file
        return super.onCreateView(inflater,view,savedInstanceState);
    }

    //Used to obtain the refernces of needed items for displaying.
    @Override
    public void onActivityCreated(Bundle savedState)
    {
        super.onActivityCreated(savedState);
        // Set the list choice mode to allow only one selection at a time
        getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        // Set the list adapter for the ListView
        setListAdapter(new ArrayAdapter<String>(getActivity(),R.layout.chi_state_layout,getResources().getStringArray(R.array.Places)));
    }
}

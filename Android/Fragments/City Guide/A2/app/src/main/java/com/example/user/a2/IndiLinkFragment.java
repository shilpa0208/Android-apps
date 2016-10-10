package com.example.user.a2;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;


public class IndiLinkFragment extends Fragment {

    private int currIndex=-1;
    private WebView mIndiLinkView =null;

    //return the position of the current item
    public int getpos()
    {
        return currIndex;
    }

    //fetches the url at the given location.
    public void showLinkAtIndex(int pos)
    {
        currIndex=pos;
        mIndiLinkView.loadUrl(getResources().getStringArray(R.array.IndiLinks)[currIndex]);
    }

    //Attaches the fragment to the given activity
    @Override
    public void onAttach(Activity activity)
    {
        super.onAttach(activity);
    }

    //Used to inflate the view with the given xml file.
    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup view,Bundle savedSatae)
    {
        //inflates the view with the given layout file
        return inflater.inflate(R.layout.indi_link_layout,view,false);
    }

    //Used to obtain the refernces of needed items for displaying.
    @Override
    public  void onActivityCreated(Bundle bundle)
    {
        super.onActivityCreated(bundle);
        //helps to keeps the currently selected item as it is
        setRetainInstance(true);
        //obtain the reference of the webview
        mIndiLinkView =(WebView)getActivity().findViewById(R.id.webViewLinks);
    }
}

package com.example.user.a2;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.TextView;


public class ChiLinkFragment extends android.app.Fragment {

    private WebView mLinkView=null;
    private int currIndex=-1;

    //return the position of the current item
    public int getPos()
    {
        return currIndex;
    }

    //fetches the url at the given location.
    public void showLinkAtIndex(int pos)
    {
        currIndex=pos;
        mLinkView.loadUrl(getResources().getStringArray(R.array.Links)[currIndex]);

    }

    //Attaches the fragment to the given activity
    @Override
    public void onAttach(Activity activity)
    {
        super.onAttach(activity);
    }

    //Used to inflate the view with the given xml file.
    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup view,Bundle savedInstancestate)
    {
        //inflates the view with the given layout file
        return inflater.inflate(R.layout.chi_link_layout,view,false);
    }

    //Used to obtain the refernces of needed items for displaying.
    @Override
    public void onActivityCreated(Bundle savedInstance)
    {
        super.onActivityCreated(savedInstance);
        //helps to keeps the currently selected item as it is
        setRetainInstance(true);
        //obtain the reference of the webview
        mLinkView= (WebView)getActivity().findViewById(R.id.webView1);

    }
}

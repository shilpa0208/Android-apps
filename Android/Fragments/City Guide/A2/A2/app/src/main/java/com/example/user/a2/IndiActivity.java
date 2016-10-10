package com.example.user.a2;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.ComponentName;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.LinearLayout;


public class IndiActivity extends AppCompatActivity implements IndiStateFragment.ListSelectionListener {

    private final IndiLinkFragment mLinkFrag=new IndiLinkFragment();
    private FrameLayout indiLinkLayout, indiStateLayout;
    private FragmentManager fg;

    //function to initialise the view on creating it.
    @Override
    public  void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        //Set the screen with the given layout file
        setContentView(R.layout.indi_main_layout);

        //obtain reference of actionbar
        Toolbar actionBar=(Toolbar) findViewById(R.id.my_toolbar);
        //Set the action bar for viewing.
        setSupportActionBar(actionBar);
        actionBar.setBackgroundColor(Color.BLUE);

        //obtain references of framelayout
        indiStateLayout =(FrameLayout)findViewById(R.id.indi_state_container);
        indiLinkLayout =(FrameLayout)findViewById(R.id.indi_link_container);

        //Reference of fragment manager is obtained
        fg=getFragmentManager();
        //Get a reference fragment transaction
        FragmentTransaction ft=fg.beginTransaction();
        //replace the fragment by checking if it is already present or not.
        ft.replace(R.id.indi_state_container, new IndiStateFragment());
        //adds the fragment to the backstack
        ft.addToBackStack(null);
        //commit the transaction
        ft.commit();
        //Execute the pending transactions
        fg.executePendingTransactions();
        //seting the listener for onbackstackchanged listener
        fg.addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener(){

            @Override
            public void onBackStackChanged() {
                setlayout();
            }
        });
    }

    //function to return back to the previous screen on clicking the back button
    @Override
    public void onBackPressed()
    {
        if(mLinkFrag.isAdded())
        {
            //remove if the fragment is added
            FragmentTransaction ft=fg.beginTransaction();
            ft.remove(mLinkFrag);
            ft.addToBackStack(null);
            ft.commit();
            fg.executePendingTransactions();
        }
        else
        {
            //close when it is done
            this.finish();
        }

    }

    //function to create the options menu with overflow area
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        //Inflate the menu with the given layout file specified
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);
        return true;
    }

    //function to implement the action when an item in the options menu is selected.
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        //checks if the ite id is the same as any of the cases and executes accordingly
        switch(item.getItemId())
        {
            case R.id.info_Chicago:Intent chiIntent = new Intent();
                //explicit intent to start the required activity.
                chiIntent.setComponent(new ComponentName("com.example.user.a2", ChicagoActivity.class.getName()));
                startActivity(chiIntent);
                break;

            case R.id.info_Indianapolis:Intent indiIntent = new Intent();
                //explicit intent to start the required activity.
                indiIntent.setComponent(new ComponentName("com.example.user.a2", IndiActivity.class.getName()));
                startActivity(indiIntent);
                break;

            default:break;
        }
        return true;
    }

    //function to set the layout based on the configuration of the phone.
    private void setlayout()
    {
        //check the configuration and set the layout as specified.
        if(getResources().getConfiguration().orientation== Configuration.ORIENTATION_LANDSCAPE)
        {
            //if link fragment id added or not is checked and the following settings are made to the layout.
            if (!mLinkFrag.isAdded()) {
                indiStateLayout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
                indiLinkLayout.setLayoutParams((new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT)));
            } else {
                indiStateLayout.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1f));
                indiLinkLayout.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 2f));
            }
        }
        else if(getResources().getConfiguration().orientation== Configuration.ORIENTATION_PORTRAIT)
        {
            //if link fragment id added or not is checked and the following settings are made to the layout.
            if (!mLinkFrag.isAdded()) {
                indiStateLayout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
                indiLinkLayout.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT));
            } else {
                indiStateLayout.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT));
                indiLinkLayout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
            }
        }

    }

    //Action to be taken when an item in the list displayed is selected.
    @Override
    public void onListItemSelected(int pos) {

       if(!mLinkFrag.isAdded())
        {
            //If link fragment id not added the fragment gets added now using the fragment manager
            FragmentTransaction ft=fg.beginTransaction();
            ft.replace(R.id.indi_link_container,mLinkFrag);
            ft.addToBackStack(null);
            ft.commit();
            fg.executePendingTransactions();
        }

        //checks if the item displayed is the correct indexed one.
        mLinkFrag.showLinkAtIndex(pos);
    }

    //Method used to ensure that the currently opened paged is only displayed when the configuration is changed.
    @Override
    public void onConfigurationChanged(Configuration myConfig)
    {
        super.onConfigurationChanged(myConfig);

        if(myConfig.orientation==Configuration.ORIENTATION_PORTRAIT)
        {
            if(mLinkFrag.isAdded())
            {
                indiStateLayout.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT));
                indiLinkLayout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
            }
        }
        else if(myConfig.orientation==Configuration.ORIENTATION_LANDSCAPE)
        {
            indiStateLayout.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1f));
            indiLinkLayout.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 2f));
        }
    }
}

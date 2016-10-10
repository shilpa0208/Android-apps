package com.shilpa.cs478.project_1;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    //Initialising the request code for activity created
    public static final int START_COMPOSE_ACTIVITY= 123;

    //Reference of edittext initialised to null
    EditText phEditText = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    /**
     * Method is called when the OK button is click which matches the pattern in the textbox with
     * the phone formats (xxx)xxxxxxx and (xxx) xxxxxxx.It also sets the new activity with the
     * extracted pattern and starts the activity.
     * @param view
     */
    public void onSubmitClicked(View view)
    {
        // Used to find the given edittext box in the layout provided.
        phEditText = (EditText)findViewById(R.id.phEditText);

        // Converts the entered text into string format.
        String phEnteredText = phEditText.getText().toString();

        // Matches the given input pattern to find only two types of phone numbers formats.
        Pattern phPattern = Pattern.compile("\\(\\d{3}\\) ?\\d{3}\\-\\d{4}");
        Matcher phMatcher = phPattern.matcher(phEnteredText);

        //Used to find the phone number from the entered text
        if (phMatcher.find()) {
            //Creates a new mesgintent to start the Compose Message Activity.
            Intent mesgintent = new Intent(Intent.ACTION_VIEW);
            mesgintent.setClassName("com.android.mms", "com.android.mms.ui.ComposeMessageActivity");

            //Used to send the data from the parent activity to the child activity. A bundle which
            // is a parceable object is created to handle this.
            Bundle extras = getIntent().getExtras();
            if (extras != null) {
                mesgintent.putExtras(extras);
            }

            //Sets the data with the matched pattern
            mesgintent.setData(Uri.parse(phMatcher.group(0)));

            //Starting the activity along with the request code.
            startActivityForResult(mesgintent,START_COMPOSE_ACTIVITY);
        }
    }

    //This method is used to perform callback activities when returned from the child activity.
    @Override
    public void onActivityResult(int reqcode,int rescode,Intent intent)
    {
       //Checks which activity is returned.
       if(reqcode==START_COMPOSE_ACTIVITY)
       {
               phEditText.setText("Returning from Compose Message...");
       }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

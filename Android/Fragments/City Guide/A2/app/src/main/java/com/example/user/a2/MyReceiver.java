package com.example.user.a2;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;

public class MyReceiver extends BroadcastReceiver {

    //Executed when a braodcast is received
    @Override
    public void onReceive(Context context, Intent intent) {

        //Checks if it is Chicago city
        if (intent.getAction().equals("ChicagoMessage")) {
            Intent chiIntent = new Intent();
            //explicitly sets the intent
            chiIntent.setComponent(new ComponentName("com.example.user.a2", ChicagoActivity.class.getName()));
            //sets flag to open a new task
            chiIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            //starts the activity
            context.startActivity(chiIntent);
        }
        //Checks if it is indianapolis
        else if(intent.getAction().equals("IndiMessage"))
        {
            Intent indiIntent = new Intent();
            //explicitly sets the intent
            indiIntent.setComponent(new ComponentName("com.example.user.a2", IndiActivity.class.getName()));
            //sets flag to open a new task
            indiIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            //starts the activity
            context.startActivity(indiIntent);
        }
    }
}



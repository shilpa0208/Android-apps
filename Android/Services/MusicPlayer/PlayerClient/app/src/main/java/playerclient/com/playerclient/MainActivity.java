package playerclient.com.playerclient;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.ResolveInfo;
import android.database.Cursor;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;

import audioserver.com.audioserver.IMusicServer;

public class MainActivity extends AppCompatActivity {

    //Declare field members
    private EditText songNumber=null;
    private IMusicServer musicService = null;

    //Define the service connection object that helps to connect and disconnect from the server.
    private final ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            musicService = IMusicServer.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            musicService = null;
        }
    };

    //Method executed when the activity is created.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //set the view to be main layout.
        setContentView(R.layout.activity_main);
        //Obtain the reference of the editText field.
        songNumber=(EditText)findViewById(R.id.songNum);
    }

    // Method executed when the activity comes to focus.
    @Override
    protected void onResume() {
        super.onResume();
        //if theere is any service connection then do the statements specified.
        if (musicService == null) {
            //Create an explicit intent with the server as the target class
            Intent intent = new Intent(IMusicServer.class.getName());
            //Information that is obtained by resolving the intent filter to get the package name.This is used to make the
            //intent travel across different packages in different applications
            ResolveInfo info = getPackageManager().resolveService(intent, Context.BIND_AUTO_CREATE);
            intent.setComponent(new ComponentName(info.serviceInfo.packageName, info.serviceInfo.name));
            //Bind the service to the given client.
            bindService(intent, connection, Context.BIND_AUTO_CREATE);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
            }

    //Method call on clicking the button PLAY to play the song
    public void onPlayClick(View view) {
        try {
            if(musicService != null){
                //initialise the id to be a default of -1.
                int id =  -1;
                try{
                    //Obtain the text that is entered in teh EditText box.
                    id = Integer.parseInt(songNumber.getText().toString().trim());

                }catch(Exception ex){
                    //display a toast message if any invalid number is entered.
                    Toast.makeText(this,"Invalid Song number!!",Toast.LENGTH_LONG).show();
                    return;
                }
                // if the EditText field is Null, it shows a toast message to enter a valid number.
                if ( songNumber.getText().toString().equals(" ")){
                    Toast.makeText(this,"Please enter a value in the box",Toast.LENGTH_LONG).show();
                }
                //handles any other random number typed other than the valid one's.
                else if(id == -1 || id < 0 || id > 3){
                    Toast.makeText(this,"Invalid Song number!!",Toast.LENGTH_LONG).show();
                    return;
                }
                //play the music with the given id.
                musicService.playMusic(id);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Method call on clicking the button PAUSE to pause the song
    public void onPauseClick(View v)
    {
        try{
            if(musicService != null){
                //pause the song.
                musicService.pauseMusic();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Method call on clicking the button RESUME to resume the song
    public void onResumeClick(View v)
    {
        try{
            if(musicService != null){
                //Resume the song.
                musicService.resumeMusic();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Method call on clicking the button STOP to stop the call
    public void onStopClick(View v)
    {
        try{
            if(musicService != null){
                //Stop the song
                musicService.stopMusic();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        /*try {
            musicService.stopMusic();
        }catch(RemoteException e){
            e.printStackTrace();
        }
*/
    }

    //Method called when the Get Transaction History button is clicked.
    public void getTransactionHistory(View view){
        try{
            //if there exists a service connection then execute the following.
            if(musicService != null){
                //Obtain a list of rows that is returned from the server side.
                List<String> rows = musicService.getTransactions();
                //Create an explicit intent by setting the component name and adding the List using extras.
                Intent intent = new Intent();
                intent.setComponent(new ComponentName(this, ListSupportActivity.class));
                intent.putStringArrayListExtra("Transactions", (ArrayList<String>) rows);
                //start the intent.
                startActivity(intent);
            }
        }catch (RemoteException e){
            e.printStackTrace();
        }
    }
}

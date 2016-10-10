package audioserver.com.audioserver;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.RemoteException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class MusicServerBinder extends IMusicServer.Stub{

    //Declare the fields.
    Context context = null;
    MediaPlayer mediaPlayer = new MediaPlayer();
    List<String> songs = new ArrayList<String>();
    MusicServerDBHelper mSDBHelper = null;
    String currSongName;
    static int currSongId;
    String currState;
    String stateDescr;

    //Create an integer array with the given songs.
    private int[] musicArray = new int[]{R.raw.leanon, R.raw.seeyouagain, R.raw.thewoolpiles};
    //Create an array with the song names.
    private String[] musicNameArray = new String[]{"Lean on", "See You Again", "The Wool Piles"};

    //Declare a parameterized constructor.
    public MusicServerBinder(Context context){
        this.context = context;
        mSDBHelper = new MusicServerDBHelper(context);
    }

    //Mthod to play the music using Mediaplayer.
    @Override
    public void playMusic(int id){
        try{
            //set the current song id and name to the given song.
            currSongId = id;
            currSongName = musicNameArray[id-1];
            stateDescr = "PLAYING TRACK " + id;
            //MediaPlayer is reset to play as required without creating a new Mediaplayer.
            mediaPlayer.reset();
            //Play the song only if it is not already created.
            AssetFileDescriptor fd = context.getResources().openRawResourceFd(musicArray[id-1]);
            //Set the song to the given place where it paused at.
            mediaPlayer.setDataSource(fd.getFileDescriptor(), fd.getStartOffset(), fd.getLength());
            //Prepare the MediaPlayer and start the song.
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mediaPlayer.prepare();
            mediaPlayer.start();
            //Insert the values into the SQLiteDatabase.
            insertValues(currSongId, currSongName, Calendar.getInstance().getTime(), "PLAY" , stateDescr);

        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

    //Method to stop the music.
    @Override
    public void stopMusic() throws RemoteException {
        if(mediaPlayer.isPlaying()){
            stateDescr = "STOPPED TRACK" + currSongId + " WHICH WAS PLAYED";
            //If the song is playing then stop the song.
            mediaPlayer.stop();
            //Insert the current transaction into the SQLiteDatabase.
            insertValues(currSongId, currSongName, Calendar.getInstance().getTime(), "STOP", stateDescr);
        }
    }

    //Method to pause the music.
    @Override
    public void pauseMusic() throws RemoteException {
        if(mediaPlayer.isPlaying()) {
            stateDescr = "PAUSED WHILE PLAYING TRACK " + currSongId ;
            //If the song is playing then pause the song.
            mediaPlayer.pause();
            //Insert the current transaction into the SQLiteDatabase.
            insertValues(currSongId, currSongName, Calendar.getInstance().getTime(), "PAUSE", stateDescr);
        }
    }

    @Override
    public void resumeMusic() throws RemoteException {
        stateDescr = "RESUMING TRACK " + currSongId + " WHICH WAS PAUSED";
        //Go the current location where the music was stopped and resume.
        mediaPlayer.seekTo(mediaPlayer.getCurrentPosition());
        mediaPlayer.start();
        //Insert the current transaction into the SQLiteDatabase.
        insertValues(currSongId, currSongName, Calendar.getInstance().getTime(), "RESUME", stateDescr);
    }

    //Method to display the Transaction History
    @Override
    public List getTransactions() throws RemoteException {

        //Create a rows list to store te rows of the table in the SQLiteDatabase.
        List<String> rows = new ArrayList<String>();
        //The query to be executed is specified as a String.
        final String SELECT_CMD = " SELECT * FROM transactions ";
        //Create an instance of the helper class to start the database transactions.
        MusicServerDBHelper mHelper = new MusicServerDBHelper(context);
        SQLiteDatabase db = mHelper.getReadableDatabase();
        //Create a cursor object to store the result of the query.
        Cursor cursor = db.rawQuery(SELECT_CMD, null);
        //cursor.moveToFirst();
        //Extract the contents from the cursor object to a list with each row.
        while(cursor.moveToNext()){
            StringBuilder sb = new StringBuilder();
            sb.append("Song ID: ").append(cursor.getString(cursor.getColumnIndex(MusicServerDBHelper.SONG_ID))).append("\n")
                    .append("Song Name: ").append(cursor.getString(cursor.getColumnIndex(MusicServerDBHelper.SONG_NAME))).append("\n")
                    .append("Time of Request: ").append(cursor.getString(cursor.getColumnIndex(MusicServerDBHelper.TIME_STAMP))).append("\n")
                    .append("Kind of Request: ").append(cursor.getString(cursor.getColumnIndex(MusicServerDBHelper.CURR_STATE))).append("\n")
                    .append("Current State:").append(cursor.getString(cursor.getColumnIndex(MusicServerDBHelper.STATE_DESC)));
            //Add the constructed Row string to teh rows List.
            rows.add(sb.toString());
        }
        //Close the cursor object and the database..
        cursor.close();
        db.close();
        //return teh rows obtained as a string.
        return rows;
    }

    //Method to insert values into the SQLiteDatabase
    public void insertValues(int id, String name, Date date, String state, String prevState){
        //Obtain the database in writable format.
        SQLiteDatabase db = mSDBHelper.getWritableDatabase();
        //Create a reference to content values to populate the data into the database.
        ContentValues values = new ContentValues();
        values.put(MusicServerDBHelper.SONG_ID,id);
        values.put(MusicServerDBHelper.SONG_NAME, name);
        values.put(MusicServerDBHelper.TIME_STAMP, date.toString());
        values.put(MusicServerDBHelper.CURR_STATE, state);
        values.put(MusicServerDBHelper.STATE_DESC,prevState);
        //Insert the data into the database
        db.insert(MusicServerDBHelper.TABLE_NAME, null, values);
        //Close the database.
        db.close();
    }
}

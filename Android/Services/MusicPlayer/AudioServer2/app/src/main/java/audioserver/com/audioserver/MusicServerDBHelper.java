package audioserver.com.audioserver;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.Date;

public class MusicServerDBHelper extends SQLiteOpenHelper {

    //Declare the string values for the database.
    final static String TABLE_NAME = "transactions";
    final static String SONG_ID = "_id";
    final static String SONG_NAME = "song_name";
    final static String TIME_STAMP = "time_stamp";
    final static String CURR_STATE = "state";
    final static String STATE_DESC = "prev_state";
    final static String[] columns = { SONG_ID, SONG_NAME, TIME_STAMP, CURR_STATE, STATE_DESC};
    //Declare a string command for creating a database.
    final private static String CREATE_CMD = " CREATE TABLE transactions ( "
            + SONG_ID + " INTEGER NOT NULL, "
            + SONG_NAME + " TEXT NOT NULL, "
            + TIME_STAMP + " TEXT NOT NULL, "
            + CURR_STATE + " TEXT NOT NULL, "
            + STATE_DESC + " TEXT NOT NULL)";
    //Declare the DB name and version number.
    final private static String NAME = "transaction_db";
    final private static Integer VERSION = 1;
    final private Context mContext;

    //Create a parametrised constructor to initialise the various values.
    public MusicServerDBHelper(Context context)
    {
        super(context, NAME, null, VERSION);
        this.mContext =context;
    }

    //Called when the database is created.
    @Override
    public void onCreate(SQLiteDatabase db) {
        //Create the database.
        db.execSQL(CREATE_CMD);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //deleteDatabase();
    }

    //Called when the database is to be deleted.
    public void deleteDatabase(){
        mContext.deleteDatabase(NAME);
    }

    public void insertValues(int id, String name, Date date, String state, String prev_state){
        //Insert the values into the database.
        ContentValues values = new ContentValues();
        values.put(SONG_ID,id);
        values.put(SONG_NAME,name);
        values.put(TIME_STAMP, date.toString());
        values.put(CURR_STATE, state);
        values.put(STATE_DESC,prev_state);
        //Insert the values into the given database.
        getWritableDatabase().insert(TABLE_NAME, null, values);

    }
}

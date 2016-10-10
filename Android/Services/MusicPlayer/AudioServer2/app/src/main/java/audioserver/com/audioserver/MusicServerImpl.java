package audioserver.com.audioserver;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class MusicServerImpl extends Service {

    //Create a reference to the the musicServiceBinder class
    MusicServerBinder musicServerBinder = null;

    //Bind the service to the specified class.
    @Override
    public IBinder onBind(Intent intent){
        musicServerBinder =  new MusicServerBinder(getApplicationContext());
        return musicServerBinder;
    }

    @Override
    public boolean onUnbind(Intent intent){
        super.onUnbind(intent);
        return true;
    }

}

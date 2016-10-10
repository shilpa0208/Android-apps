package com.example.user.musicapp;

import android.app.ListActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

//MainActivity class used for the MusicApp which inherits the ListActivity.
public class MainActivity extends ListActivity {

    //musicList is a list of objects to hold the information of the various fields of a view.
    List<Music> musicList;

    //Oncreate method to initialise the activity with the listView
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //The list is populated with values required for each view in the listView.
        musicList = new ArrayList<Music>();
        musicList.add(new Music("Ink", "ColdPlay", R.drawable.coldplayink, 1, "https://www.youtube.com/watch?v=gKM15TaKLUI", "https://en.wikipedia.org/wiki/Ink_(song)", "https://en.wikipedia.org/wiki/Ghost_Stories_(Coldplay_album)"));
        musicList.add(new Music("Wildest Dreams", "Taylor Swift", R.drawable.wildest, 2, "https://www.youtube.com/watch?v=IdneKLhsWOQ", "https://en.wikipedia.org/wiki/Wildest_Dreams_(Taylor_Swift_song)", "https://en.wikipedia.org/wiki/1989_(Taylor_Swift_album)"));
        musicList.add(new Music("Hello", "Adele", R.drawable.adelehello, 3, "https://www.youtube.com/watch?v=YQHsXMglC9A", "https://en.wikipedia.org/wiki/Hello_(Adele_song)", "http://adele.wikia.com/wiki/25"));
        musicList.add(new Music("See You Again", "Wiz Khalifa", R.drawable.seeyouagain, 4, "https://www.youtube.com/watch?v=RgKAFK5djSk", "https://en.wikipedia.org/wiki/See_You_Again_(Wiz_Khalifa_song)", "https://en.wikipedia.org/wiki/Wiz_Khalifa"));
        musicList.add(new Music("Blank Space", "Taylor Swift", R.drawable.blankspace, 5, "https://www.youtube.com/watch?v=e-ORhEE9VVg", "https://en.wikipedia.org/wiki/Blank_Space", "https://en.wikipedia.org/wiki/Taylor_Swift"));
        musicList.add(new Music("Hey Mama", "David Gutta", R.drawable.heymama, 6, "https://www.youtube.com/watch?v=uO59tfQ2TbA", "https://en.wikipedia.org/wiki/Hey_Mama_(David_Guetta_song)", "https://en.wikipedia.org/wiki/David_Guetta"));
        musicList.add(new Music("High Hopes","Pink Floyd",R.drawable.highhopes,7,"https://www.youtube.com/watch?v=7jMlFXouPk8","https://en.wikipedia.org/wiki/High_Hopes_(Pink_Floyd_song)","https://en.wikipedia.org/wiki/Pink_Floyd"));
        musicList.add(new Music("Unfaithful","Rihanna",R.drawable.unfaithful,8,"https://www.youtube.com/watch?v=rp4UwPZfRis&index=34&list=RDynMk2EwRi4Q","https://en.wikipedia.org/wiki/Unfaithful_(song)","https://en.wikipedia.org/wiki/File:Rihanna_-_Unfaithful.png"));
        musicList.add(new Music("Smack That", "Akon", R.drawable.smackthat, 9, "https://www.youtube.com/watch?v=bKDdT_nyP54", "https://en.wikipedia.org/wiki/Smack_That", "https://en.wikipedia.org/wiki/Akon"));

        //Garbage collector is initialised to ensure proper memory usage.
        System.gc();
        //ListAdapter is registered for usage.
        setListAdapter(new MyAdapter(this, musicList));


        //Obtain a view reference and register the context menu for the given list.
        View view = getListView();
        registerForContextMenu(view);
     }

    //Method invoked on clicking an item in the listView. It opens the respective webpage of the given song.
    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        //Obtain a reference of the class with holding the item at the given position.
        Music music = (Music) l.getAdapter().getItem(position);
        //Create an intent to open the browse with the respective webpage of the song.
        Intent browserIntent = new Intent(Intent.ACTION_VIEW);
        browserIntent.setData(Uri.parse(music.getWebpage()));
        //start thw activity with the created intent.
        startActivity(browserIntent);
    }

    //Method called to craete the context menu with different choices.
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        //super class called.
        super.onCreateContextMenu(menu, v, menuInfo);
        //Create a menu inflater to inflate the menu with the given items in the menu.xml file.
        MenuInflater menuinflate=getMenuInflater();
        menuinflate.inflate(R.menu.menu,menu);

    }

    //Method invoked when an item is selected in the given context menu.
    @Override
    public boolean onContextItemSelected(MenuItem menu)
    {
        //Create an adapter for the context menu to hold the various values.
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)menu.getMenuInfo();
        //Pbtain a reference of the listView.
        ListView l=getListView();
        //info-position will give the index of selected item
        int IndexSelected=info.position;
        //Obtain the reference of item selected from the music class.
        Music music= (Music) l.getAdapter().getItem(IndexSelected);
        //Craete an intent to open the respective activity.
        Intent browserIntent = new Intent(Intent.ACTION_VIEW);
            //Perform a check on the item selected to display the respective page.
            switch(menu.getItemId())
            {
                case R.id.songpage:
                    //If songpage is selected the activity is started with the respective intent.
                    browserIntent.setData(Uri.parse(music.getWikiSong()));
                    startActivity(browserIntent);
                    break;
                case R.id.artistpage:
                    //If artistpage is selected the activity is started with the respective intent.
                    browserIntent.setData(Uri.parse(music.getWikiArtist()));
                    startActivity(browserIntent);
                    break;
                case R.id.song:
                    //If song is selected the activity is started with the respective intent.
                    browserIntent.setData(Uri.parse(music.getWebpage()));
                    startActivity(browserIntent);
                    break;
            }
            return true;
        }
    }



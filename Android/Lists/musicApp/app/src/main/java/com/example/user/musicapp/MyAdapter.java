package com.example.user.musicapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import java.util.zip.Inflater;

//MyAdapter class is the custom adapter class defined to populate the listView item.
public class MyAdapter extends BaseAdapter {

    Context context;
    List<Music> music;
    //Constructor is defined with the context and the list passed as arguments.
    MyAdapter(Context cont,List<Music> music)
    {
        this.context=cont;
        this.music=music;
    }

    //Methods are overriden to obtain the size, the position, id and the view.
    @Override
    public int getCount() {
        return music.size();
    }

    @Override
    public Object getItem(int position) {
        return music.get(position);
    }

    @Override
    public long getItemId(int position) {
        return music.get(position).getId();
    }

    //Method called when each view of the listView is created.
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        //The listview is assigned to the convertView to recycle the memory of the list.
        View viewList=convertView;
        if(viewList==null) {
            //inflate the layout only if not done previously.
            viewList = LayoutInflater.from(context).inflate(R.layout.layout, null);
        }
        //Obtain the reference of the two textfields and the image field that are declared and set them to their respective values.
        TextView view1=(TextView)viewList.findViewById(R.id.textView);
        view1.setText(music.get(position).getTitle());

        TextView view2=(TextView)viewList.findViewById(R.id.textView2);
        view2.setText(music.get(position).getArtist());

        ImageView view3=(ImageView)viewList.findViewById(R.id.imageView);
        view3.setImageResource(music.get(position).getImageResourceId());

        System.gc();
        //return the view created.
        return viewList;

    }
}

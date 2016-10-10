package playerclient.com.playerclient;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.List;

public class MyAdapter extends BaseAdapter {

    //Declare the fields.
    Context context;
    List<String> rows;

    //Parameterised constructor to initialise the fields.
    MyAdapter(Context context, List<String> rows)
    {
        this.context = context;
        this.rows = rows;
    }

    //Used to otain the count of the elements.
    @Override
    public int getCount() {
        return rows.size();
    }

    //Used to obtain the item from the list.
    @Override
    public Object getItem(int position) {
        return rows.get(position);
    }

    //Used to obtain the item id from the list.
    @Override
    public long getItemId(int position) {
        return position;
    }

    //Create a view with the given list by inflating the required layout.
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //Prevents overlapping while scrolling.
        View viewList = convertView;
        //If there is no view set then inflate the layout.
        if(viewList == null){
            viewList = LayoutInflater.from(context).inflate(R.layout.list_transac_layout,null);
        }
        // Obtain the reference of the textfield that has to be inflated.
        TextView view = (TextView)viewList.findViewById(R.id.song);
        //Set the value of the field as required.
        view.setText(rows.get(position));
        //Return the view List created.
        return viewList;
    }
}

package playerclient.com.playerclient;

import android.app.ListActivity;
import android.os.Bundle;

public class ListSupportActivity extends ListActivity {

    //Activity that is used to display the List view.
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Set the list adapter to the list passed with the intent.
        setListAdapter(new MyAdapter(this, getIntent().getStringArrayListExtra("Transactions")));
    }
}

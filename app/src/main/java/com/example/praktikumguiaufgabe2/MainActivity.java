package com.example.praktikumguiaufgabe2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.ListViewCompat;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;

//public class MainActivity extends AppCompatActivity {
public class MainActivity extends ListActivity {

// ohne AppCompatActivity fehlt ToolBar
// ohne ListActivity fehlt ListView...this.setListAdapter !!
    private TextView tv;
    private ListView lv;

    private String [] content = {
            "Fotos", "Bildschirmfotos", "Anderes Verzeichnis..."};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv = (ListView) findViewById(android.R.id.list);
        lv.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        ArrayAdapter<String> myList = new ArrayAdapter<String>(this,
                                    R.layout.simple_list_item_multiple_choice, content);

        // CheckedTextView erstellen und verknüpfen!!
        this.setListAdapter(myList);

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

        switch(id) {
            case R.id.quit: tv.setText("Beenden");
            break;
        }

        return super.onOptionsItemSelected(item);
    }


}


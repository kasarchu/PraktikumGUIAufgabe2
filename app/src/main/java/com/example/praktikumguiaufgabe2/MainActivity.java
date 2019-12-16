package com.example.praktikumguiaufgabe2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.ListViewCompat;

import android.app.ListActivity;
import android.media.Image;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;

// AppCompatActivity wegen Toolbar
public class MainActivity extends AppCompatActivity {

    private Button btnTest;
    private Button btnAuswählen;
    private TextView tv;
    private ListView lv;
    private ImageView iv;

    // wird dem Adapter übergeben
    private String [] content = {"Fotos", "Bildschirmfotos", "Anderes Verzeichnis..."};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAuswählen = (Button) findViewById(R.id.btnAuswählen);

        // ListView initialisieren
        lv = (ListView) findViewById(R.id.listView);
        lv.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        // Adapter anlegen um die ListView zu befüllen
        ArrayAdapter<String> myList = new ArrayAdapter<String>(this,
                                    R.layout.simple_list_item_multiple_choice, content);

        // hier: verknüpfen der ListView mit der CheckedTextView
        lv.setAdapter(myList);


        // ImageView initialisieren
        iv = (ImageView) findViewById(R.id.imageView);

        iv.setImageResource(R.drawable.hintergrund);

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

    /*
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        lv.setItemChecked(position, true);

        if (lv.isItemChecked(position)) {
            btnAuswählen.setEnabled(true);
        }
    }
    */

}


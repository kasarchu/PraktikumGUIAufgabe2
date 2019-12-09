package com.example.praktikumguiaufgabe2;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class ListClass extends android.app.ListActivity {

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

        this.setListAdapter(myList);
        // CheckedTextView erstellen und verknüpfen!!
    }

}

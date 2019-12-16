package com.example.praktikumguiaufgabe2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.widget.ListViewCompat;

import android.Manifest;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
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
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;

// AppCompatActivity wegen Toolbar
public class MainActivity extends AppCompatActivity {

    private int READ_STORAGE_PERMISSION_CODE = 1;
    private int WRITE_STORAGE_PERMISSION_CODE = 1;

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

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                btnAuswählen.setEnabled(true);

                String selectedFromList = (lv.getItemAtPosition(position).toString());
                Toast.makeText(MainActivity.this, selectedFromList, Toast.LENGTH_SHORT).show();

                btnAuswählen.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // hier wird neue Sub Activity gestartet
                    }
                });
                }
        });

        // ImageView initialisieren
        iv = (ImageView) findViewById(R.id.imageView);

        iv.setImageResource(R.drawable.hintergrund);


        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            // Permission is granted
            Toast.makeText(this, "Zugriffsberechtigung bereits erteilt", Toast.LENGTH_SHORT).show();
            // Permission is denied
        } else {
            requestStoragePermission();
        }

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_DENIED) {
            // Permission is not granted
            // Should we show an explanation
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                // Show an explanation to the user *asynchronously* -- don´t block
                // this thread waiting for the user´s response! After the user sees
                // the explanation, try again to request the permission
            } else {
                // No explanation needed; request the permission
                ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE}, 23142);
            }
        } else {
            // Permission has already been granted
        }


    }

    // Toolbar
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

    // ListView Selection
    /*
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        lv.setItemChecked(position, true);

        if (lv.isItemChecked(position)) {
            btnAuswählen.setEnabled(true);
        }
    }
    */

    private void requestStoragePermission() {

        // Checkt
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
           new AlertDialog.Builder(this).setTitle("Permission needed")
                                                .setMessage("This Permission is needed to read external Storage")
                                                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialog, int which) {
                                                        ActivityCompat.requestPermissions(MainActivity.this, new String[]
                                                                        {Manifest.permission.READ_EXTERNAL_STORAGE},
                                                                READ_STORAGE_PERMISSION_CODE);
                                                    }
                                                })
                   .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                       @Override
                       public void onClick(DialogInterface dialog, int which) {
                           dialog.dismiss();
                       }
                   })
                   // Dialog erstellen und anzeigen
                   .create().show();
        } else {
            ActivityCompat.requestPermissions(this, new String[]
                                                {Manifest.permission.READ_EXTERNAL_STORAGE},
                                                READ_STORAGE_PERMISSION_CODE);
        }
    }

    // check result of our permission
    // Callback Methode
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {

        if (requestCode == READ_STORAGE_PERMISSION_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permission granted", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show();
            }
        }
    } // end method

}


package com.example.androidpraktikum;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.*;
import android.content.*;
import android.content.pm.PackageManager;
import android.os.*;
import android.view.*;
import android.widget.*;

// AppCompatActivity wegen Toolbar
public class MainActivity extends AppCompatActivity {

    private int READ_STORAGE_PERMISSION_CODE = 1;
    private int WRITE_STORAGE_PERMISSION_CODE = 1;

    private Button btnAuswählen;
    private TextView tv;
    private ListView lv;
    private ImageView iv;
    private int i = 0;
    private String selectedFromList;

    // wird dem Adapter übergeben
    private String[] content = {"Foto", "Screenshots", "Anderes Verzeichnis..."};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Button
        btnAuswählen = (Button) findViewById(R.id.btnAuswählen);

        // ListView
        lv = (ListView) findViewById(R.id.listView);
        lv.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        // Adapter anlegen um die ListView zu befüllen
        ArrayAdapter<String> myList = new ArrayAdapter<String>(this, R.layout.simple_list_item_multiple_choice, content);
        // hier: verknüpfen der ListView mit der CheckedTextView
        lv.setAdapter(myList);

        // Beim Selektieren eines Verzeichnis wird ein Pfad gespeichert…
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
                btnAuswählen.setEnabled(true);
                selectedFromList = (lv.getItemAtPosition(position).toString());
                Toast.makeText(MainActivity.this, selectedFromList, Toast.LENGTH_SHORT).show();

                btnAuswählen.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        switch (selectedFromList) {
                            case "Foto":
                                subFoto(view);
                                break;
                            case "Screenshots":
                                subScreenshot(view);
                                break;
                            default:
                                Toast.makeText(MainActivity.this, "Nicht implementiert", Toast.LENGTH_SHORT).show();
                                break;
                        }
                    }
                });
            }
        });

        // ImageView
        iv = (ImageView) findViewById(R.id.imageView);
        iv.setImageResource(R.drawable.hintergrund);

        // Permissions / Zugriffsrechte
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
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 23142);
            }
        } else {
            // Permission has already been granted
        }
    }// end onCreate

    // Toolbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.quit:
                tv.setText("Beenden");
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    // startet FotoActivity
    public void subFoto(View view) {
        Intent i = new Intent(MainActivity.this, FotoActivity.class);
        this.startActivity(i);
    }

    // startet ScreenshotActivity
    public void subScreenshot(View view) {
        Intent i = new Intent(MainActivity.this, ScreenshotActivity.class);
        startActivity(i);
    }

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

    // Callback Methode...checkt auf was geklickt wurde
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {

        if (requestCode == READ_STORAGE_PERMISSION_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permission granted", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show();
            }
        }
    } // end method
} // end MainActivity
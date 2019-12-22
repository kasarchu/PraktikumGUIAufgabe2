package com.example.androidpraktikum;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.*;
import android.os.*;
import android.view.*;
import android.widget.*;

import java.io.File;

public class FotoActivity extends AppCompatActivity {

    private ImageView iv;
    private Button btnTest;

    String[] bilderNamen;
    int i = 0;
    private GestureDetector gDetect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foto);

        iv = (ImageView) findViewById(R.id.imageViewFoto);
        btnTest = (Button) findViewById(R.id.btnTest);

        // Pfad für das Verzeichnis
        String imgFile = Environment.getExternalStorageDirectory().getAbsolutePath().toString()
                + "/DCIM/Fotos";

        // alle Bilddateien in array speichern
        bilderNamen = loadImageNames(imgFile);

        // erstes Bild anzeigen
        File imgFile2 = new File(Environment.getExternalStorageDirectory().getAbsolutePath().toString()
                + "/DCIM/Fotos/" + bilderNamen[0]);
        if(imgFile2.exists()) {
            Bitmap myBitmap = BitmapFactory.decodeFile(imgFile2.getAbsolutePath());
            FotoActivity.this.iv.setImageBitmap(myBitmap);
        } else {
            Toast.makeText(this, "Diesen Pfad gibt´s nicht", Toast.LENGTH_SHORT).show();
        }

        // onTouchListener
        gDetect = new GestureDetector(this, new FlingSwipeListener2(FotoActivity.this));
        iv.setOnTouchListener(new OnTouchListener(gDetect));

        // onClickListener
        btnTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(FotoActivity.this, bilderNamen[i], Toast.LENGTH_SHORT).show();
            }
        });
    } // end onCreate

    // Toolbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_foto, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.back) {
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }

    // Touch-Geste
    public void links() {
        i++;
        if(i == bilderNamen.length) {
            i = 0;
        }
        File nextImage = new File(Environment.getExternalStorageDirectory().getAbsolutePath().toString()
                + "/DCIM/Fotos/" + bilderNamen[i]);

        if(nextImage.exists()) {
            Bitmap myBitmap = BitmapFactory.decodeFile(nextImage.getAbsolutePath());
            FotoActivity.this.iv.setImageBitmap(myBitmap);
        } else {
            Toast.makeText(this, "Fehler aufgetreten: nextImage.", Toast.LENGTH_SHORT).show();
        }
    }

    public void rechts() {
        i--;
        if(i == -1) {
            i = bilderNamen.length - 1;
        }
        File prevImage = new File(Environment.getExternalStorageDirectory().getAbsolutePath().toString()
                + "/DCIM/Fotos/" + bilderNamen[i]);

        if(prevImage.exists()) {
            Bitmap myBitmap = BitmapFactory.decodeFile(prevImage.getAbsolutePath());
            FotoActivity.this.iv.setImageBitmap(myBitmap);
        } else {
            Toast.makeText(this, "Fehler aufgetreten: prevImage.", Toast.LENGTH_SHORT).show();
        }
    }

    // Bilder anzeigen
    // holt die Namen der Dateien eines Verzeichnisses
    public static String[] loadImageNames(String path) {
        File dir = new File(path);
        String[] filenames = dir.list();
        return filenames;
    }

}

package com.example.androidpraktikum;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.*;
import android.os.*;
import android.view.*;
import android.widget.*;

import java.io.File;

public class ScreenshotActivity extends AppCompatActivity {

    private ImageView iv;
    private Button btnTest;

    String[] bilderNamen;
    int i = 0;
    private GestureDetector gDetect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screenshot);

        iv = (ImageView) findViewById(R.id.imageViewScreenshot);
        btnTest = (Button) findViewById(R.id.btnTest);

        // Pfad für das Verzeichnis
        String imgFile = Environment.getExternalStorageDirectory().getAbsolutePath().toString()
                        + "/DCIM/Screenshots/";

        // alle Bilddateien in array speichern
        bilderNamen = loadImageNames(imgFile);

        // erstes Bild anzeigen
        File imgFile2 = new File(Environment.getExternalStorageDirectory().getAbsolutePath().toString()
                        + "/DCIM/Screenshots/" + bilderNamen[0]);
        if(imgFile2.exists()) {
            Bitmap myBitmap = BitmapFactory.decodeFile(imgFile2.getAbsolutePath());
            ScreenshotActivity.this.iv.setImageBitmap(myBitmap);
        } else {
            Toast.makeText(this, "Diesen Pfad gibt´s nicht", Toast.LENGTH_SHORT).show();
        }

        // onTouchListener
        gDetect = new GestureDetector(this, new FlingSwipeListener(ScreenshotActivity.this));
        iv.setOnTouchListener(new OnTouchListener(gDetect));

        // onClickListener
        btnTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ScreenshotActivity.this, bilderNamen[i], Toast.LENGTH_SHORT).show();
            }
        });
    } // end onCreate

    // Toolbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_screenshot, menu);
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
                    + "/DCIM/Screenshots/" + bilderNamen[i]);

        if(nextImage.exists()) {
            Bitmap myBitmap = BitmapFactory.decodeFile(nextImage.getAbsolutePath());
            ScreenshotActivity.this.iv.setImageBitmap(myBitmap);
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
                    + "/DCIM/Screenshots/" + bilderNamen[i]);

        if(prevImage.exists()) {
            Bitmap myBitmap = BitmapFactory.decodeFile(prevImage.getAbsolutePath());
            ScreenshotActivity.this.iv.setImageBitmap(myBitmap);
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

    /*
    public void openImageChooser() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("images/*");
        startActivityForResult(Intent.createChooser(intent, "Select Image"), SELECT_IMAGE);
    }
     */

    /*
    // Ein Bild aus dem Pfad selektieren
    public void onImageGalleryClicked(View v) {
        // invoke the image gallery using an implicit intent
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);

        // where do we want to find the data?
        String pictureDirectory = Environment.getExternalStorageDirectory().getAbsolutePath().toString() + "/DCIM/Screenshots";

        // finally, get a URI representation
        //Uri data = Uri.parse("content://com.android.externalstorage.documents/document/primary%3A" + "DCIM/Screenshots");
        Uri data = Uri.parse(pictureDirectory);

        // set the data und type. get all image types
        photoPickerIntent.setDataAndType(data, "image/*");

        // we will invoke this activity and get something back from it
        startActivityForResult(photoPickerIntent, SELECT_IMAGE);
    }
    */

}

package gui.inf.hsas.de.a2neueversion;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.File;

public class FileIO {
  
  // holt die Namen der Dateien eines Verzeichnisses
  public static String[] loadImageNames(String path) {
    
    File dir = new File(path);
    String[] filenames = dir.list();
    return filenames;
  }
  
 public static String[] loadImagePathNames(String path) {
    String TAG="loadImagePathNames";
    File dir = new File(path);
    Log.v(TAG,"Path---: " + path);
    String[] filenames = dir.list();
    String[] paths = new String[filenames.length];
    for(int i=0; i<paths.length;i++) {
      paths[i] = path + "/" + filenames[i];
      System.out.println("Path: " + paths[i]);
    }
    return paths;
  }
  
  // Erzeugt aus einer Bilddatei eine Bitmap
  public static Bitmap createBitmapFromFile(String filepath) {
    Bitmap imageBitmap = null;
      Bitmap scaledBitmap = null;
    if(filepath != null) {
      File imageFile = new File(filepath);
      if(imageFile.exists()) {
        imageBitmap = BitmapFactory.decodeFile(imageFile.getAbsolutePath());

      } // end if
      
    }// end if
    return imageBitmap;
  } // end method

}

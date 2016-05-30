package InstaGalleryAPI;

import android.app.Activity;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.provider.OpenableColumns;
import android.util.Patterns;

import java.io.ObjectOutputStream;
import java.security.Key;
import java.util.ArrayList;

/**
 * Created by KBibars on 5/27/2016.
 */
public class IGImageLoader   {
 public  ArrayList<IGImage> mIGImageList= new ArrayList<IGImage>();
    ArrayList<String > listOfAllImages;

    private static final int isURI= 1;
    private static final int isURL=0;

/*    Returns an arraylist of IGImage using an Arraylist of Strings of URI/URL with starting index and ending index to load
    a specific amount of images if needed */
    public  ArrayList<IGImage> loadImagesList(ArrayList<String> mStringList,int startingIndex,int endingIndex)
    {
        if(endingIndex==-1)
        {
            endingIndex=mStringList.size();
        }
        for(int i =startingIndex;i<endingIndex;i++)
        {
            IGImage igImage= new IGImage();
            igImage.setmImagePath(mStringList.get(i));
           if(checkPathType(mStringList.get(i))==isURI)
           {
              //Call URILoader
               IGUriLoader igUriLoader=new IGUriLoader();
               igImage=igUriLoader.loadURI(igImage,isURI,mStringList.size());

           }
            else if (checkPathType(mStringList.get(i))==isURL)
           {
               //Call URL CLASS

           }
           /* Add the returned IGImage to the list */
            mIGImageList.add(igImage);
        }

        return mIGImageList;
    }

    private int  checkPathType(String path){

        if (Patterns.WEB_URL.matcher(path).matches()) {
            return isURL;
        }
        else
        {
            return isURI;
        }
    }
/*Loads a string list with all the URIs of the images in the device*/
    public ArrayList<String> loadAllDeviceImages(Activity activity){
        /*Uses Cursor to get all the URIs in the device*/
        Uri uri;
        Cursor cursor;

        int column_index_data, column_index_folder_name;
        String PathOfImage = null;
        uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        listOfAllImages = new ArrayList<String>();
        String[] projection = {MediaStore.MediaColumns.DATA,
                MediaStore.Images.Media.BUCKET_DISPLAY_NAME};
        cursor = activity.getContentResolver().query(uri, projection, null,
                null, null);
        column_index_data = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);

     //TODO:After considertion I might have been able to get the size,date, and name of the file at this point and use them which might have saved a bit of memory instead of loading them in the ImageLoader
        while (cursor.moveToNext()) {
            PathOfImage = cursor.getString(column_index_data);
            listOfAllImages.add(PathOfImage);
        }

        return listOfAllImages;
    }


}

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
public class IGImageLoader {
 public  ArrayList<IGImage> mIGImageList= new ArrayList<IGImage>();
    ArrayList<String > listOfAllImages;

private static final int isURI= 1;
    private static final int isURL=0;

    public  ArrayList<IGImage> loadImagesList(ArrayList<String> mStringList)
    {
        for(int i =0;i<mStringList.size();i++)
        {
            IGImage igImage= new IGImage();
            igImage.setmImagePath(mStringList.get(i));
           if(checkPathType(mStringList.get(i))==isURI)
           {
              //Call URI CLASS
               IGUriLoader igUriLoader=new IGUriLoader();
               igImage=igUriLoader.loadURI(igImage,isURI,mStringList.size());

           }
            else if (checkPathType(mStringList.get(i))==isURL)
           {
               //Call URL CLASS

           }
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

    public ArrayList<IGImage> loadAllDeviceImages(Activity activity){
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
        column_index_folder_name = cursor
                .getColumnIndexOrThrow(MediaStore.Images.Media.BUCKET_DISPLAY_NAME);
        while (cursor.moveToNext()) {
            PathOfImage = cursor.getString(column_index_data);
            int nameIndex = cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME);
            int sizeIndex = cursor.getColumnIndex(OpenableColumns.SIZE);
            if(nameIndex!=-1)
            {
                String karim = cursor.getString(nameIndex);
            }
            listOfAllImages.add(PathOfImage);
        }

        return loadImagesList(listOfAllImages);
    }




}

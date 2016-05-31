package InstaGalleryAPI;

import android.app.Activity;
import android.os.AsyncTask;

import com.android.volley.toolbox.ImageLoader;

import java.util.ArrayList;

import instagallery.kbibars.com.instagallery.MainActivity;

/**
 * Created by kbibars on 5/31/16.
 */
public class LoaderThread extends AsyncTask<Integer,Integer,Integer>  {
    ArrayList<String>strings;
    int startingIndex;
    int endingIndex;
    ArrayList<IGImage> igImages;
    Activity context;

    public LoaderThread(ArrayList<String> strings,int startingIndex,int endingIndex,ArrayList<IGImage>igImages , Activity context){
        this.startingIndex=startingIndex;
        this.strings=strings;
        this.endingIndex=endingIndex;
        this.igImages=igImages;
this.context=context;
    }

    @Override
    protected Integer doInBackground(Integer... params) {
        IGImageLoader igImageLoader= new IGImageLoader();
        igImages= igImageLoader.loadImagesList(strings,startingIndex,endingIndex);
        return 0;
    }

    @Override
    protected void onPostExecute(Integer integer) {
        MainActivity.reloadcompletion(igImages,context);
    }
}

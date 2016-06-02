package InstaGalleryAPI;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import instagallery.kbibars.com.instagallery.MyAdapter;

/**
 * Created by kbibars on 5/31/16.
 */
public class LoaderThread extends AsyncTask<Integer, Integer, Integer> {

    ArrayList<String> strings;
    int startingIndex;
    int endingIndex;
   public static ArrayList<IGImage> igImages;
    Activity context;
    ProgressDialog progressDialog;
    RecyclerView recyclerView;
    MyAdapter adapter;

    public LoaderThread(ArrayList<String> strings, int startingIndex, int endingIndex, Activity context,RecyclerView recyclerView,MyAdapter adapter) {
        this.startingIndex = startingIndex;
        this.strings = strings;
        this.endingIndex = endingIndex;
        this.context = context;
        this.recyclerView=recyclerView;
        this.adapter=adapter;
    }

    @Override
    protected Integer doInBackground(Integer... params) {
        IGImageLoader igImageLoader = new IGImageLoader();
        igImages = igImageLoader.loadImagesList(strings, startingIndex, endingIndex);
        return 0;
    }

    @Override
    protected void onPostExecute(Integer integer) {
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(context, 3);
        recyclerView.setLayoutManager(mLayoutManager);
        adapter = new MyAdapter(igImages, context, 1);
        recyclerView.setAdapter(adapter);
        progressDialog.dismiss();

    }

    @Override
    protected void onPreExecute() {
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Loading Images...Please Wait ");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setIndeterminate(true);
        progressDialog.show();
        super.onPreExecute();
    }
}

package instagallery.kbibars.com.instagallery;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.provider.OpenableColumns;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;

import InstaGalleryAPI.IGImage;
import InstaGalleryAPI.IGImageLoader;

public class MainActivity extends AppCompatActivity {
    ImageView myView;
    ArrayList<String> listOfAllImages;
    RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myView = (ImageView) findViewById(R.id.myView);
        recyclerView=(RecyclerView)findViewById(R.id.recyler);

        ArrayList<String> karim2= new ArrayList<String>();
      //  karim2.add("/storage/emulated/0/DCIM/Camera/IMG_20160520_191145.jpg");

        IGImageLoader karim = new IGImageLoader();

       // IGImage karim3= karim.loadAllDeviceImages(this).get(0);
    //    myView.setImageBitmap(karim3.getmImage());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        MyAdapter adapter=new MyAdapter(karim.loadAllDeviceImages(this),getParent(),1);
        recyclerView.setAdapter(adapter);
        //Picasso.with(this).load(karim3.getmImagePath()).into(myView);

    }
}





package instagallery.kbibars.com.instagallery;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.util.LruCache;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;

import InstaGalleryAPI.IGImage;
import InstaGalleryAPI.IGImageLoader;

public class MainActivity extends AppCompatActivity {
    ImageView myView;
    ArrayList<String> listOfAllImages;
    RecyclerView recyclerView;
    Button button;

    private LruCache<String, IGImage> mMemoryCache;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myView = (ImageView) findViewById(R.id.myView);
        recyclerView = (RecyclerView) findViewById(R.id.recyler);
        button = (Button) findViewById(R.id.tempbutton);


        IGImageLoader karim = new IGImageLoader();

        IGImage karim3 = karim.loadAllDeviceImages(this).get(0);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 3);
        recyclerView.setLayoutManager(mLayoutManager);
        final MyAdapter adapter = new MyAdapter(karim.loadAllDeviceImages(this), this, 1);
        recyclerView.setAdapter(adapter);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IGImageLoader karim = new IGImageLoader();

                //IGImage karim3 = karim.loadAllDeviceImages(this).get(0);
                RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(MainActivity.this, 3);
                recyclerView.setLayoutManager(mLayoutManager);
                final MyAdapter adapter = new MyAdapter(karim.loadAllDeviceImages(MainActivity.this), MainActivity.this, 1);
                recyclerView.setAdapter(adapter);
                recyclerView.invalidate();


            }
        });

    }





        public void loading ()
    {

    }


    }












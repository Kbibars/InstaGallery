package instagallery.kbibars.com.instagallery;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.util.LruCache;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import InstaGalleryAPI.IGImage;
import InstaGalleryAPI.IGImageLoader;

public class MainActivity extends AppCompatActivity {
    ImageView myView;
    ArrayList<String> listOfAllImages;
    RecyclerView recyclerView;
    Button button;
int dirty =0;
    private LruCache<String, IGImage> mMemoryCache;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myView = (ImageView) findViewById(R.id.myView);
        recyclerView = (RecyclerView) findViewById(R.id.recyler);
        button = (Button) findViewById(R.id.tempbutton);


        IGImageLoader karim = new IGImageLoader();

     //   ArrayList<IGImage> karim3 = karim.loadAllDeviceImages(this);
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




/*
        public void save (ArrayList<IGImage>karim3)
    {
        FileOutputStream fos = null;
        try {
            fos = this.openFileOutput("karim", Context.MODE_PRIVATE);
            ObjectOutputStream os = null;
            os = new ObjectOutputStream(fos);
            os.writeObject(karim3);
            os.close();
            fos.close();

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

public IGImage loading() throws IOException, ClassNotFoundException {

    FileInputStream fis = this.openFileInput("karim");
    ObjectInputStream is = new ObjectInputStream(fis);
    IGImage simpleClass = (IGImage) is.readObject();
    is.close();
    fis.close();
    return  simpleClass;
}*/
    }












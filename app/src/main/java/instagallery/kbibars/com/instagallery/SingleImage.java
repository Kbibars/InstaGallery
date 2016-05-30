package instagallery.kbibars.com.instagallery;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.graphics.Palette;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.io.File;

import InstaGalleryAPI.IGImage;

public class SingleImage extends AppCompatActivity {
    ImageView imageView;
    TextView size;
    TextView date;
    TextView name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_image);
        Intent intent = getIntent();
        String name_text = "Name "+ intent.getStringExtra("name");
        String size_text = "Size="+intent.getStringExtra("size")+" KB";
        String path=intent.getStringExtra("path");
        String date_text="Date: "+intent.getStringExtra("date");



        imageView=(ImageView)findViewById(R.id.single_image);
        size=(TextView)findViewById(R.id.size);
        date=(TextView)findViewById(R.id.date);
        name=(TextView)findViewById(R.id.name);

        File mFile=new File(path);
        Bitmap mBitmap = BitmapFactory.decodeFile(mFile.getAbsolutePath());

        Palette.from(mBitmap).generate(new Palette.PaletteAsyncListener() {
            @Override
            public void onGenerated(Palette palette) {
              date.setBackgroundColor(palette.getSwatches().get(0).getBodyTextColor());
                name.setBackgroundColor(palette.getSwatches().get(0).getTitleTextColor());
            }
        });

        imageView.setImageBitmap(mBitmap);
        size.setText(size_text);
        date.setText(date_text);
        name.setText(name_text);




    }
}

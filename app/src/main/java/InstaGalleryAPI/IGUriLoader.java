package InstaGalleryAPI;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v4.util.LruCache;

import java.io.File;
import java.util.Date;

/**
 * Created by KBibars on 5/27/2016.
 */
public class IGUriLoader {
    LruCache<String,IGImage>mMemoryCache;

    public void karim (){
        // Get max available VM memory, exceeding this amount will throw an
        // OutOfMemory exception. Stored in kilobytes as LruCache takes an
        // int in its constructor.
        final int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);

        // Use 1/8th of the available memory for this memory cache.
        final int cacheSize = maxMemory / 8;


        mMemoryCache = new LruCache<String, IGImage>(cacheSize) {
            @Override
            protected int sizeOf(String key, IGImage bitmap) {
                // The cache size will be measured in kilobytes rather than
                // number of items.
                return bitmap.getmImage().getByteCount() / 1024;
            }
        };

    }

    public  IGImage loadURI(IGImage mImage,int mtype,int mImageListCount)
    {
        File mFile=new File(mImage.getmImagePath());
        Long mImagelength = mFile.length();
        String mImagename=  mFile.getName();
        Date mImagedate=new Date(mFile.lastModified());
        mImagelength=mImagelength/1024;
        karim();
        if (mFile.exists())
        {
            mImage.setmImageName(mImagename);
            mImage.setmImageSize(mImagelength);
            mImage.setmDate(mImagedate);
            mImage.setmType(mtype);

            BitmapFactory.Options options = new BitmapFactory.Options();
            //Better to use powers of 2 for some reason (Need to investigate)
            if (mImageListCount>20) {
                options.inSampleSize = 8;
            }
            else
            {
                options.inSampleSize=0;
            }
            if(mMemoryCache.get(mImage.getmImageName())!=null)
            {
                // mImage.setmImage(getBitmapFromMemCache(mImage.getmImagePath()).getmImage());
                mImage=getBitmapFromMemCache(mImage.getmImagePath());
            }
            else {
                Bitmap mBitmap = BitmapFactory.decodeFile(mFile.getAbsolutePath(), options);
                mImage.setmImage(mBitmap);
                addBitmapToMemoryCache(mImage.getmImagePath(),mImage);
            }
        }

        return mImage;
    }

    public void addBitmapToMemoryCache(String key, IGImage bitmap) {
        if (getBitmapFromMemCache(key) == null) {
            mMemoryCache.put(key, bitmap);
        }
    }

    public IGImage getBitmapFromMemCache(String key) {
        return mMemoryCache.get(key);
    }

}

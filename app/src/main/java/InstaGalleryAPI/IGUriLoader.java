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
public class IGUriLoader  extends AsyncTask<IGImage,Void,IGImage>{


    @Override
    protected IGImage doInBackground(IGImage... params) {
        return loadURI(params[0],1,200);
    }

    public  IGImage loadURI(IGImage mImage,int mtype,int mImageListCount)
    {
        File mFile=new File(mImage.getmImagePath());
        Long mImagelength = mFile.length();
        String mImagename=  mFile.getName();
        Date mImagedate=new Date(mFile.lastModified());
        mImagelength=mImagelength/1024;

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
                Bitmap mBitmap = BitmapFactory.decodeFile(mFile.getAbsolutePath(), options);
                mImage.setmImage(mBitmap);

        }

        return mImage;
    }



}

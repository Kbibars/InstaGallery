package InstaGalleryAPI;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.File;
import java.util.Date;

/**
 * Created by KBibars on 5/27/2016.
 */
public class IGUriLoader {

    public static IGImage loadURI(IGImage mImage,int mtype,int mImageListCount)
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
            options.inSampleSize=10;

            Bitmap mBitmap = BitmapFactory.decodeFile(mFile.getAbsolutePath(),options);
            mImage.setmImage(mBitmap);
        }
        return mImage;
    }
}

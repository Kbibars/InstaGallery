package InstaGalleryAPI;

import android.graphics.Bitmap;

import java.util.Date;

/**
 * Created by KBibars on 5/27/2016.
 */
public class IGImage {
    Bitmap mImage;
    String mImageName;
    String mImagePath;
    Long mImageSize;
    int mType; //0 for URL , 1for URI
    Date mDate;


    public Bitmap getmImage() {
        return mImage;
    }

    public void setmImage(Bitmap mImage) {
        this.mImage = mImage;
    }

    public String getmImageName() {
        return mImageName;
    }

    public void setmImageName(String mImageName) {
        this.mImageName = mImageName;
    }

    public String getmImagePath() {
        return mImagePath;
    }

    public void setmImagePath(String mImagePath) {
        this.mImagePath = mImagePath;
    }

    public Long getmImageSize() {
        return mImageSize;
    }

    public void setmImageSize(Long mImageSize) {
        this.mImageSize = mImageSize;
    }
    public int ismType() {
        return mType;
    }

    public void setmType(int mType) {
        this.mType = mType;
    }

    public Date getmDate() {
        return mDate;
    }

    public void setmDate(Date mDate) {
        this.mDate = mDate;
    }
}

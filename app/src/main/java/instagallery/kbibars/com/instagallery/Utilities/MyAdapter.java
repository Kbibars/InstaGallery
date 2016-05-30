package instagallery.kbibars.com.instagallery.Utilities;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import InstaGalleryAPI.IGImage;
import instagallery.kbibars.com.instagallery.R;
import instagallery.kbibars.com.instagallery.SingleImage;

/**
 * Created by KBibars on 5/28/2016.
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    public int mActivyID;
    private ArrayList<IGImage> mDataset;
    private Context mcontext;


     /*Provide a reference to the views for each data item usinga ViewHodler*/

    /*Adapter Constructor*/
    public MyAdapter(ArrayList<IGImage> myDataset, Context context, int mActivityID) {
        mDataset = myDataset;
        mcontext = context;
        mActivyID = mActivityID;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.single_item, parent, false);
        // set the view's size, margins, paddings and layout parameters
        return new ViewHolder(v);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.mTextView.setText(mDataset.get(position).getmImageName());
        holder.mImageView.setImageBitmap(mDataset.get(position).getmImage());
        //HandleClicklistner on the view to view full image
        holder.mSingleLatyout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mcontext, SingleImage.class);
                intent.putExtra("name", mDataset.get(position).getmImageName());
                intent.putExtra("date", mDataset.get(position).getmDate().toString());
                intent.putExtra("size", mDataset.get(position).getmImageSize().toString());
                intent.putExtra("path", mDataset.get(position).getmImagePath());
                mcontext.startActivity(intent);

            }
        });


    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextView;
        public ImageView mImageView;
        public LinearLayout mSingleLatyout;

        public ViewHolder(View v) {
            super(v);
            mImageView = (ImageView) v.findViewById(R.id.mImage_view);
            mTextView = (TextView) v.findViewById(R.id.mTextview);
            mSingleLatyout=(LinearLayout)v.findViewById(R.id.mSingleLatyout) ;


        }


    }
}

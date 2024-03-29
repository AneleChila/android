package com.notice.noticeboard;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ViewHolder> {
    private ArrayList<Image> mDataset;
    Image image;
    private FeedActivity mActivity;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextView;
        public ImageView mImageView;
        public Button mLikeButton;


        public ViewHolder(View v) {
            super(v);
            mTextView = v.findViewById(R.id.textView2);
            mImageView = v.findViewById(R.id.imageView);
            mLikeButton = v.findViewById(R.id.likeButton);
        }
    }

    public ImageAdapter(ArrayList<Image> myDataset, FeedActivity activity) {
        mDataset = myDataset;
        mActivity = activity;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ImageAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.image_view, parent, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        //Image image =  mDataset.get(position);
        image =  mDataset.get(position);
        if (image.user != null) {
            holder.mTextView.setText(image.user.displayName);
        }
        Picasso.get()
               // .load(" https://firebasestorage.googleapis.com/v0/b/noticeboard-b67bb.appspot.com/o/images%2FtBFWWuGvtHS6BYgxEmaLYuUSR1u2%2FtBFWWuGvtHS6BYgxEmaLYuUSR1u2_20190910_180436?alt=media&token=f6873e4d-16bd-401f-a742-7d3189eb3396")
                .load(image.getDownloadUrl())
               // .fit()
                .into(holder.mImageView);

        holder.mLikeButton.setText("Report (" + image.likes + ")");
        if(image.hasLiked) {
            holder.mLikeButton.setBackgroundColor(mActivity.getResources().getColor(R.color.colorAccent));
        } else {
            holder.mLikeButton.setBackgroundColor(mActivity.getResources().getColor(R.color.colorPrimary));
        }
        holder.mLikeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mActivity.setLiked(image);
            }
        });
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public void addImage(Image image) {
        mDataset.add(0, image);
        notifyDataSetChanged();
    }
}

package com.example.broadway;

import com.example.broadway.model.Musical;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private List<Musical> mList;
    private final OnItemClickListener listener;
    private Context mContext;


    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public ImageView mImageView;
        public TextView mTextViewTitle;
        public TextView mTextViewAuthor;

        public ViewHolder(View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.image_view);
            mTextViewTitle = itemView.findViewById(R.id.text_view_title);
            mTextViewAuthor = itemView.findViewById(R.id.text_view_author);

        }
    }


    // Provide a suitable constructor (depends on the kind of dataset)
    public MyAdapter(Context context,List<Musical> list, OnItemClickListener listener){
        mList = list;
        this.listener = listener;
        mContext = context;

    }
    // Create new views (invoked by the layout manager)
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        // create a new view
        LayoutInflater inflater = LayoutInflater.from(
                parent.getContext());
        View v = inflater.inflate(R.layout.row_layout, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        final Musical currentItem = mList.get(position);

        String imageUrl = currentItem.getImage_url();
        String titleName = currentItem.getTitle();
        String authorName = currentItem.getBy();

        holder.mTextViewTitle.setText(titleName);
        holder.mTextViewAuthor.setText("By: " + authorName);
        Picasso.get().load(imageUrl).fit().centerInside().into(holder.mImageView);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(currentItem);

            }
        });
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mList.size();
    }

}

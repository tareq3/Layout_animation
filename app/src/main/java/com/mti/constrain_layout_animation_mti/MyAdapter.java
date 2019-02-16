package com.mti.constrain_layout_animation_mti;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/***
 * Created by mtita on 17,February,2019.
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private OnItemClickListener mOnItemClickListener;
    private Context mContext;
    private List<Item> dataList;

    public MyAdapter(Context context, List<Item> dataList, OnItemClickListener onItemClickListener) {
        mContext = context;
        this.dataList = dataList;
        mOnItemClickListener=onItemClickListener;
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView image;
        TextView name;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            image=itemView.findViewById(R.id.imageview_item);
            name=itemView.findViewById(R.id.textview_name);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(mOnItemClickListener!=null) mOnItemClickListener.onItemClick(v, getAdapterPosition());
        }
    }





    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater= LayoutInflater.from(mContext);
        View   view = inflater.inflate(R.layout.grid_item, viewGroup, false);


        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        // Load the thumbnail image

        GlideApp.with(myViewHolder.image.getContext())
                .load(dataList.get(i).getThumbnailUrl()).into(myViewHolder.image);

        // Set the TextView's contents
        myViewHolder.name.setText(dataList.get(i).getName());
    }



    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public interface OnItemClickListener{
        public void onItemClick(View view, int position);
    }

}
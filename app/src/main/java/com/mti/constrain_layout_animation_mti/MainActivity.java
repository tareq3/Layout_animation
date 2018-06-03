package com.mti.constrain_layout_animation_mti;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

import static com.mti.constrain_layout_animation_mti.Item.getItem;


/*
    * For using Shared element Activity transition you must have min lollipop or android 5.0 or sdk 21
    *
    * we had used glide for image fetching
    *
    * As this is First activity It will have Transition Exit and ReEnter.
    *
    * We had used XML Tranition for this Activity
   */


public class MainActivity extends AppCompatActivity {

    private RecyclerView mGridView;

        StaggeredGridLayoutManager mStaggeredGridLayoutManager;
    private GridAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grid);

        // Setup the GridView and set the adapter
        mGridView = findViewById(R.id.grid);

        mStaggeredGridLayoutManager=new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        mGridView.setLayoutManager(mStaggeredGridLayoutManager);

        mAdapter = new GridAdapter(Item.ITEMS); //GridAdapter is a Custom Adapter
        mGridView.setAdapter(mAdapter);

    }



    private class GridAdapter extends RecyclerView.Adapter<GridAdapter.MyViewHolder>  {


ArrayList<Item> mItems;

        public GridAdapter(ArrayList<Item> items) {
            mItems = items;
        }

        View view;
        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull final ViewGroup parent, int viewType) {

          view = LayoutInflater.from(parent.getContext()).inflate( R.layout.grid_item, parent, false);




            MyViewHolder myViewHolder=new MyViewHolder(view);
            return myViewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {



            ImageView imageView;
            TextView textView;

            imageView=holder.img1;
            textView=holder.textView;

             Item item =mItems.get(position);

            // Load the thumbnail image
            GlideApp.with(view).load(item.getThumbnailUrl()).placeholder(R.drawable.ic_launcher_background).into(imageView);


            // Set the TextView's contents
            textView.setText(item.getName());
        }

        @Override
        public int getItemCount() {
            return Item.ITEMS.size();
        }


        public  class MyViewHolder extends RecyclerView.ViewHolder {

        TextView textView;
        ImageView img1;


        public MyViewHolder(View itemView) {
            super(itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final Item item =  mItems.get(getAdapterPosition());

                    //Toast.makeText(this, ""+ item, Toast.LENGTH_SHORT).show();
                    // Construct an Intent as normal
                    Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                    intent.putExtra(DetailActivity.EXTRA_PARAM_ID, item.getId());



                    Bundle bundle= ActivityOptions.makeSceneTransitionAnimation(MainActivity.this).toBundle(); //Triggering Transition Animation
                    startActivity(intent,bundle);
                }
            });

            textView = (TextView) itemView.findViewById(R.id.textview_name);
            img1 = (ImageView) itemView.findViewById(R.id.imageview_item);


        }


        }
    }
}

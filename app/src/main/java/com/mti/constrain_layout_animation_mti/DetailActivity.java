/*
 * Copyright (C) 2014 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.mti.constrain_layout_animation_mti;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.transition.Fade;
import android.transition.Slide;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.view.Gravity;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.annotation.GlideModule;


/**
 * Our secondary Activity which is launched from {@link MainActivity}. Has a simple detail UI
 * which has a large banner image, title and body text.
 *
 * As this is second Activity it will have Transition Enter and Return
 *
 * we had used Code Transition for this activity
 */
public class DetailActivity extends AppCompatActivity {

    // Extra name for the ID parameter
    public static final String EXTRA_PARAM_ID = "detail:_id";


    private ImageView mHeaderImageView;
    private TextView mHeaderTitle;

    private Item mItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details);

        // Retrieve the correct Item instance, using the ID provided in the Intent
        mItem = Item.getItem(getIntent().getIntExtra(EXTRA_PARAM_ID, 0));

        mHeaderImageView = (ImageView) findViewById(R.id.imageview_header);
        mHeaderTitle = (TextView) findViewById(R.id.textview_title);


        loadItem();
    }

    private void loadItem() {
        // Set the title TextView to the item's name and author
        mHeaderTitle.setText(getString(R.string.image_header, mItem.getName(), mItem.getAuthor()));

        if ( (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP )&& m_AddTransition() ) {

            // If all other cases we should just load the full-size image now
            loadFullSizeImage();
        }
    }


    /**
     * Load the item's full-size image into our {@link ImageView}.
     */
    private void loadFullSizeImage() {
        GlideApp.with(mHeaderImageView.getContext())
                .load(mItem.getPhotoUrl())
                .thumbnail(GlideApp.with(mHeaderImageView.getContext()).load(mItem.getThumbnailUrl()))

                 .into(mHeaderImageView);
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private boolean m_AddTransition() {

        //region Transition in detail
        Slide slide=new Slide(Gravity.BOTTOM);
        slide.addTarget(R.id.description);
        slide.setInterpolator(
                AnimationUtils.loadInterpolator(this,
                        android.R.interpolator.linear_out_slow_in)
        );
        slide.setDuration(300);

        getWindow().setEnterTransition(slide);
        //endregion

        //region Fast Transition
        getWindow().setReturnTransition(new Fade().setDuration(300));
        //endregion

        return true;
    }

}

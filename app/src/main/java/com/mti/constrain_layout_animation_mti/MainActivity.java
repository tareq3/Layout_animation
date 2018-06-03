package com.mti.constrain_layout_animation_mti;

import android.animation.TimeInterpolator;
import android.content.Context;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.transition.Scene;
import android.support.v7.app.AppCompatActivity;
import android.transition.ChangeBounds;
import android.transition.TransitionInflater;
import android.transition.TransitionManager;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnticipateOvershootInterpolator;
import android.widget.ImageView;

/*
 * This is a very basic Scene Transition Animation using Transition Manager
 *
 * For creating this type of animation we need only write some transition code.

*/

public class MainActivity extends AppCompatActivity {

    private static boolean show;
    ViewGroup sceneRoot;
    Scene scene1,scene0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_scene_0);

         sceneRoot=findViewById(R.id.constraint);
         scene1 = Scene.getSceneForLayout(sceneRoot, R.layout.activity_main_scene_1, this);
         scene0 = Scene.getSceneForLayout(sceneRoot, R.layout.activity_main_scene_0, this);

    }

    //this method is using from background image click action
    public void changeScene(View v){

        if (show) {
            hideComponents();
        } else {
            showComponents();
        }

    }


    //In this follwong method we are not using any transition xml
    private  void showComponents() {
        show = true;

        ChangeBounds transition = new ChangeBounds(); //Type of transition

        transition.setInterpolator((TimeInterpolator)(new AnticipateOvershootInterpolator(1.0F))); //for bounciness
        transition.setDuration(1200L);

        TransitionManager.go(scene1,transition); //Starting Transition
    }

    //In this following method we are using transition xml which is doing the same thing like previous method
    private void hideComponents() {
        show = false;


        TransitionManager.go(scene0, TransitionInflater.from(this).inflateTransition(R.transition.simple_transition));

    }
}

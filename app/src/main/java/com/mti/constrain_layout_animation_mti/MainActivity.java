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

    /* ConstraintSet class allows you to define programmatically
     a set of constraints(means element's properties and coordination like position, color, text) to be used with ConstraintLayout.
     It lets you create and save constraints, and apply them to an existing ConstraintLayout. so no need to change layout
     *** just change layout elements properties
    */
    ConstraintSet constraintSet ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_scene_0);


         sceneRoot=findViewById(R.id.constraint);
        constraintSet=new ConstraintSet();

        //as Here layout not changing is not changing so we can call the click listener directly
         findViewById(R.id.backgroundImage).setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 if (show) {
                     hideComponents();
                 } else {
                     showComponents();
                 }
             }
         });
    }



    //In this follwong method we are not using any transition xml
    private  void showComponents() {
        show = true;


        constraintSet.clone((Context)this, R.layout.activity_main_scene_1); //cloning constrainSet means copying the elements properties and coordination

        ChangeBounds transition = new ChangeBounds();
        transition.setInterpolator((TimeInterpolator)(new AnticipateOvershootInterpolator(1.0F)));
        transition.setDuration(1200L);

        TransitionManager.beginDelayedTransition(sceneRoot,transition);
        constraintSet.applyTo((ConstraintLayout) sceneRoot); //apply all constraint element properties from scene_1 to sceneroot , nothing is replacing just changing the coordination
    }

    //In this following method we are using transition xml which is doing the same thing like previous method
    private void hideComponents() {
        show = false;


        constraintSet.clone((Context)this, R.layout.activity_main_scene_0);


        TransitionManager.beginDelayedTransition(sceneRoot,TransitionInflater.from(this).inflateTransition(R.transition.simple_transition));
        constraintSet.applyTo((ConstraintLayout) sceneRoot);
    }
}

package com.mti.constrain_layout_animation_mti;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Fade;
import android.transition.Slide;
import android.transition.TransitionManager;
import android.transition.TransitionSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_close);

        final ViewGroup transitionContainer= findViewById(R.id.transitionContainer);
        final TextView textView=findViewById(R.id.textView);
        final Button b1= findViewById(R.id.button);
        b1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        TransitionManager.beginDelayedTransition(transitionContainer,
                                new TransitionSet()
                                        .addTransition(new Fade())
                                        .addTransition(new Slide(Gravity.END))
                        );
                    }
                }
                transitionContainer.removeView(textView);//by this line we are adding animation on certain view, in this case textView will have the animation
                transitionContainer.removeView(b1); //we can apply animation on multiple view at a single time.

            }
        });
    }
}

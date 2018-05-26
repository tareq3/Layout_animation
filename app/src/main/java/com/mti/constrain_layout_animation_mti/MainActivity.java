package com.mti.constrain_layout_animation_mti;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Scene;
import android.transition.TransitionInflater;
import android.transition.TransitionManager;
import android.view.View;
import android.view.ViewGroup;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scene1);

        findViewById(R.id.close_Button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    TransitionManager.go(
                            Scene.getSceneForLayout(
                                    (ViewGroup)findViewById(R.id.root),
                                    R.layout.scene2,
                                    MainActivity.this

                            ),
                            TransitionInflater.from(MainActivity.this).inflateTransition(R.transition.defaul_to_info)
                    );
                }
            }
        });
    }
}

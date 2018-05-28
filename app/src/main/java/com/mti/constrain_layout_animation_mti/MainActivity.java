package com.mti.constrain_layout_animation_mti;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Scene;
import android.transition.TransitionInflater;
import android.transition.TransitionManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scene1);






    }

    public void start_Scene2(View view){
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

    public void start_Scene1(View view) {
      //  Toast.makeText(MainActivity.this, "clicked", Toast.LENGTH_SHORT).show();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            TransitionManager.go(
                    Scene.getSceneForLayout(
                            (ViewGroup) findViewById(R.id.root),
                            R.layout.scene1,
                            MainActivity.this

                    ),
                    TransitionInflater.from(MainActivity.this).inflateTransition(R.transition.back_to_default)
            );
        }
    }
}

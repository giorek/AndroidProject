package com.monty.uicomponent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.monty.loglibrary.Logger;
import com.monty.uicomponent.annotation.AnnotationProxy;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startMontyActivity();
        startAnnotation();
    }

    private void startMontyActivity(){
        Intent intent = new Intent(this,MontyActivity.class);
        startActivity(intent);
    }

    private void startAnnotation(){
        Logger.Companion.getInstance().d(TAG,"startAnnotation ");
        AnnotationProxy annotationProxy = new AnnotationProxy();
        String keyOne = "haha";
        String keyTwo = "nihao";
        annotationProxy.compare(keyOne,keyTwo);
        new Thread(new Runnable() {
            @Override
            public void run() {
                Logger.Companion.getInstance().d(TAG,"startAnnotation in new Thread");
                AnnotationProxy annotationProxy1 = new AnnotationProxy();
                String keyOne = "haha";
                String keyTwo = "nihao";
                annotationProxy1.compare(keyOne,null);
            }
        }).start();
    }
}
package com.monty.uicomponent.annotation;

import androidx.annotation.NonNull;
import androidx.annotation.UiThread;

import com.monty.loglibrary.Logger;

public class AnnotationProxy {
    private static final String TAG = "AnnotationProxy";
    @UiThread
    public void compare(@NonNull String key,@NonNull String key1){
        Logger.Companion.getInstance().d(TAG,"key:"+key+" key1:"+key1);
    }
}

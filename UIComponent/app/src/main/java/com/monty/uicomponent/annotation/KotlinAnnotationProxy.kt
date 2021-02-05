package com.monty.uicomponent.annotation

import androidx.annotation.NonNull
import androidx.annotation.UiThread

class KotlinAnnotationProxy {
    @UiThread
    fun add(@NonNull a:Int,@NonNull b:Int){

    }
}
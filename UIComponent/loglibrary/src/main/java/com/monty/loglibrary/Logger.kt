package com.monty.loglibrary

import android.util.Log

class Logger {
    companion object{
        const val TAG = "Logger"
        val DEBUG = true
        val Instance = Logger()
    }

    public fun i(tag:String?,msg:String){
        if(!DEBUG) false
        if(tag == null){
            Log.i(TAG,msg)
        }else{
            Log.i(tag,msg)
        }
    }

    fun d(tag:String?,msg:String){
        if(!DEBUG) false
        if(tag == null){
            Log.d(TAG,msg)
        }else{
            Log.d(tag,msg)
        }
    }

    fun e(tag:String?,msg:String){
        if(!DEBUG) false
        if(tag == null){
            Log.e(TAG,msg)
        }else{
            Log.e(tag,msg)
        }
    }
}
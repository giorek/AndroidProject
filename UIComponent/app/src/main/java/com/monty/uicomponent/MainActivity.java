package com.monty.uicomponent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.monty.loglibrary.Logger;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
/*        String[] mGender = getResources().getStringArray(R.array.user_gender);
        for(int i = 0 ;i< mGender.length ;i++){
            Log.d("TAG"," gender[index] "+mGender[i]);
        }


        String one = getResources().getQuantityString(R.plurals.numOfSound,1);
        String five = getResources().getQuantityString(R.plurals.numOfSound,5);
        String oneh = getResources().getQuantityString(R.plurals.numOfSound,100);
        //Log.d("TAG"," one:"+one +" five:"+five +" oneh:"+oneh);
        Log.d("TAG"," one:"+one );*/

        startMontyActivity();
    }

    private void startMontyActivity(){
        //Intent intent = new Intent(this,OtherActivity.class);
        Intent intent = new Intent(this,MontyActivity.class);
        startActivity(intent);
    }
}
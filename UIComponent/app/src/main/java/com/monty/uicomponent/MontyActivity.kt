package com.monty.uicomponent

import android.app.Activity
import android.os.Bundle
import android.widget.TextView
import com.monty.loglibrary.Logger

class MontyActivity() : Activity() {
    companion object{
        const val TAG = "MontyActivity"
    }

    private lateinit var userName:TextView
    private lateinit var userPaw:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Logger.Instance.i(TAG," onCreate ")
        setContentView(R.layout.user_layout)

        userName = findViewById(R.id.user_layout_name) as TextView
        userPaw = findViewById(R.id.user_layout_pwd) as TextView

        var resTypeProxy = ResTypeProxy(this)
        resTypeProxy.getBolValue()
        resTypeProxy.getStr()
        resTypeProxy.getStrArray()
        resTypeProxy.getIntegerValue()
        resTypeProxy.getIntegerArrayValue()
        var color = resTypeProxy.getTypeArrayValue()
        userName.setTextColor(color)

    }


}
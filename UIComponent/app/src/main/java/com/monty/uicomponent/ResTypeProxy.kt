package com.monty.uicomponent

import android.content.Context
import com.monty.loglibrary.Logger

class ResTypeProxy(context: Context) {
    companion object{
        const val TAG = "ResTypeProxy"

    }
    private var context: Context = context

    /**
     * 获取bool类型值
     */
    fun getBolValue(){
        var clickable = context.resources.getBoolean(R.bool.bol_clickable)
        Logger.Instance.i(TAG," getBolValue clickable:${clickable}")
    }

    /**
     * 获取字符串
     */
    fun getStr():String{
        var userName = context.resources.getString(R.string.user_name)
        Logger.Instance.i(TAG," getStr userName:${userName}")
        return userName
    }

    /**
     * 获取字符串数组
     */
    fun getStrArray():Array<String>{
        var gender:Array<String> = context.resources.getStringArray(R.array.user_gender)
        for(index in 0 until  gender.size){
            Logger.Instance.i(TAG," gender[${index}]=${gender[index]} ")
        }
        return gender;
    }

    /**
     * 获取integer属性
     */
    fun getIntegerValue():Int{
        var maxVlue = context.resources.getInteger(R.integer.max_value)
        Logger.Instance.i(TAG," getIntegerValue:${maxVlue}")
        return maxVlue
    }

    /**
     * 获取int array
     */
    fun getIntegerArrayValue():IntArray{
        var genderArray = context.resources.getIntArray(R.array.gender_range)
        for(index in 0 until  genderArray.size){
            Logger.Instance.i(TAG," getIntegerArrayValue genderArray[${index}]=${genderArray[index]} ")
        }
        return genderArray
    }

    /**
     * 获取typeArray属性
     */
    fun getTypeArrayValue():Int{
        var colorTypeArray = context.resources.obtainTypedArray(R.array.custom_colors)
        var color = colorTypeArray.getColor(0,0)
        colorTypeArray.recycle()
        Logger.Instance.i(TAG," getIntegerArrayValue color:${color} ")
        return color
    }
}
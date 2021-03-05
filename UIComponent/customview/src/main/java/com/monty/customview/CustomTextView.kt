package com.monty.customview

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import com.monty.loglibrary.Logger

/**
 * 自定义view，绘制text
 * text的高度计算，存在五根参考线从上到下依次是
 * top-----
 * ascent-----
 * baseline----- 基准线
 * descent-----
 * bottom----
 *
 * 从基准线，向下为正值，向下为负值
 *
 *
 */
class CustomTextView : androidx.appcompat.widget.AppCompatTextView {

    companion object{
        const val TAG = "CustomTextView"
    }

    constructor(context:Context):super(context){
        initView()
    }

    constructor(context: Context,attributeSet: AttributeSet):super(context,attributeSet){
        var typeArray = context.obtainStyledAttributes(attributeSet,R.styleable.CustomView)
        attrWidth = typeArray.getInt(R.styleable.CustomTextView_textwidth,0)
        attrHeight = typeArray.getInt(R.styleable.CustomTextView_textheight,0)
        typeArray.recycle()
        initView()
    }

    constructor(context: Context,attributeSet: AttributeSet,defaultStyle:Int):super(context,attributeSet,defaultStyle){

    }

    private lateinit var paint:Paint
    private var attrWidth:Int = 0
    private var attrHeight:Int = 0
    fun initView(){
        paint = Paint()
        paint.color = Color.BLUE
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        var layoutWidth = width
        var layoutHeight = height
        Logger.Instance.d(TAG,"onDraw layoutWidth:${layoutWidth} layoutHeight:${layoutHeight}")

        var rect = Rect(0,0,layoutWidth,layoutHeight)
        canvas?.drawRect(rect,paint)

        paint.color = Color.GREEN
        canvas?.drawLine(0f,0f,100f,100f,paint)


        paint.textSize = 50f
        val fm: Paint.FontMetrics = paint.fontMetrics

        //再textview底部绘制底色
        var fontWrapperHeight = getFontWrapperHeight(paint.textSize)
        var textBgRect = Rect(0,0,layoutWidth, fontWrapperHeight.toInt())
        canvas?.drawRect(textBgRect,paint)

        var fontHeight = getFontHeight(paint.textSize)
        paint.color = Color.BLACK
        canvas?.drawText("绘制",0f,fontHeight,paint)
        //canvas?.drawText("绘制",50f,getFontCenterHeight(paint.textSize),paint)
    }

    /**
     * 获取字体高度
     * @param textSize : textSize==null时  获取字符串的默认字体,
     */
    fun getFontHeight(textSize: Float?): Float {
        val paint = Paint()
        if (textSize != null) {
            paint.textSize = textSize.toFloat()
        }
        val fm: Paint.FontMetrics = paint.fontMetrics
        var fontHeight = fm.descent - fm.ascent
        Logger.Instance.d(TAG,"onDraw top:${fm.top} bottom:${fm.bottom} leading:${fm.leading}  descent:${fm.descent} ascent:${fm.ascent} y:$y fontHeight:${fontHeight}")
        return fontHeight
    }

    fun getFontCenterHeight(textSize: Float?): Float{
        val paint = Paint()
        if (textSize != null) {
            paint.textSize = textSize.toFloat()
        }
        val fm: Paint.FontMetrics = paint.fontMetrics
        var fontHeight = fm.descent - fm.ascent
        var finalHeight = 0.5f * fontHeight - fm.bottom
        Logger.Instance.d(TAG,"onDraw getFontCenterHeight fontHeight:${fontHeight} finalHeight:${finalHeight}")
        return finalHeight
    }

    fun getFontWrapperHeight(textSize: Float?):Float{
        val paint = Paint()
        if (textSize != null) {
            paint.textSize = textSize.toFloat()
        }
        val fm: Paint.FontMetrics = paint.fontMetrics
        var fontWrapperHeight = fm.bottom - fm.top
        Logger.Instance.d(TAG,"onDraw fontWrapperHeight:${fontWrapperHeight} :${fontWrapperHeight.toInt()}")
        return fontWrapperHeight
    }
}
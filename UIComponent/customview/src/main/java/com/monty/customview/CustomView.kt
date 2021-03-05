package com.monty.customview

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.os.Build
import android.util.AttributeSet
import android.view.View
import androidx.annotation.RequiresApi
import com.monty.loglibrary.Logger

/**
 * 自定义view
 * 继承自View,
 */
class CustomView :View {
    companion object{
        const val TAG:String = "CustomView"
    }
    /**
     * 代码中调用时使用
     * 自动调用
     */
    constructor(context:Context):super(context){
        initView()
    }

    /**
     * 再xml文件声明view时使用
     * 自动调用
     */
    constructor(context: Context,attr:AttributeSet):super(context,attr){
        var typeArray = context.obtainStyledAttributes(attr,R.styleable.CustomView)
        width = typeArray.getFloat(R.styleable.CustomView_customWidth,0f)
        height = typeArray.getFloat(R.styleable.CustomView_customHeight,0f)
        mColor = typeArray.getColor(R.styleable.CustomView_customColor,Color.BLACK)
        typeArray.recycle()
        initView()
        Logger.Instance.d(TAG,"constructor width:$width height:$height")
    }

    /**
     * 非自动调用
     * 可以再第二个构造方法中调用此构造
     * 如果有默认style，再构造函数2中调用
     */
    constructor(context: Context,attr: AttributeSet,defStyleAttrInt: Int):super(context,attr,defStyleAttrInt){
        initView()
    }

    /**
     * 非自动调用 api版本 >21 用到
     * 如果有默认style，再构造函数2中调用
     */
    @RequiresApi(api=Build.VERSION_CODES.LOLLIPOP)
    constructor(context: Context,attr: AttributeSet,defStyleAttrInt: Int,defStyleRes:Int):super(context,attr,defStyleAttrInt,defStyleRes){

    }

    private lateinit var paint:Paint
    private var width:Float = 0f
    private var height:Float = 0f
    private var mColor: Int = Color.BLACK

    fun initView(){
        paint = Paint()
        paint.setColor(mColor)
    }

    /**
     * 由ViewGroup调用子view
     * 会多次调用
     */
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        var widthSize = MeasureSpec.getSize(widthMeasureSpec)
        var widthMode = MeasureSpec.getMode(widthMeasureSpec)

        var heightSize = MeasureSpec.getSize(heightMeasureSpec)
        var heightMode = MeasureSpec.getMode(heightMeasureSpec)

        Logger.Instance.d(TAG,"onMeasure:{widthSize:$widthSize widthMode:$widthMode heightSize:$heightSize heightMode:$heightMode" +
                " ${widthMode == MeasureSpec.AT_MOST}  ${heightMode == MeasureSpec.AT_MOST}}")

        if (widthMode == MeasureSpec.AT_MOST && heightMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(300, 300);
        } else if (widthMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(300, heightSize);
        } else if (heightMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(widthSize, 300);
        }
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        Logger.Instance.d(TAG,"onLayout:{changed:$changed left:$left  top:$top right:$right bottom:$bottom}")
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        var paddingLeft = paddingLeft
        var paddingRight = paddingRight
        var paddingTop = paddingTop
        var paddingBottom = paddingBottom

        //通过layout中布局的宽高，进去自身的padding
        var width = getWidth() - paddingLeft - paddingRight
        var height = getHeight() - paddingTop - paddingBottom
        Logger.Instance.d(TAG,"onDraw {paddingLeft:${paddingLeft} paddingRight:${paddingRight} " +
                "paddingTop:${paddingTop} paddingBottom:${paddingBottom} } {width:${width} height:${height}}")

        canvas?.drawRect(paddingLeft.toFloat(), paddingTop.toFloat(), (width+paddingLeft).toFloat(), (height+paddingTop).toFloat(),paint)

    }
}
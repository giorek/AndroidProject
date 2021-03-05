package com.monty.customview

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.VelocityTracker
import android.view.View
import android.view.ViewGroup
import android.widget.Scroller
import com.monty.loglibrary.Logger

/**
 * 继承自ViewGroup则需要实现onLayout接口
 */
class CustomViewGroup : ViewGroup {
    companion object{
        const val TAG = "CustomViewGroup"
    }

    constructor(context:Context):super(context){
        init(context)
    }

    constructor(context: Context,attributeSet: AttributeSet):super(context,attributeSet){
        init(context)
    }

    private var lastX:Float = 0f
    private var lastY:Float = 0f

    private var currentIndex = 0;
    private var childWidth = 0
    private lateinit var scroller:Scroller
    private lateinit var tracker:VelocityTracker


    private fun init(context:Context) {
        scroller = Scroller(context);
        tracker = VelocityTracker.obtain();
    }

    /**
     * 测量view的尺寸
     */
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        //获取宽高的测量模式以及测量值
        var widthMode = MeasureSpec.getMode(widthMeasureSpec);
        var widthSize = MeasureSpec.getSize(widthMeasureSpec);
        var heightMode = MeasureSpec.getMode(heightMeasureSpec);
        var heightSize = MeasureSpec.getSize(heightMeasureSpec);
        //测量所有子View
        measureChildren(widthMeasureSpec, heightMeasureSpec);
        //如果没有子View，则View大小为0，0
        if (getChildCount() == 0) {
            setMeasuredDimension(0, 0);
        } else if (widthMode == MeasureSpec.AT_MOST && heightMode == MeasureSpec.AT_MOST) {
            var childOne = getChildAt(0);
            var childWidth = childOne.getMeasuredWidth();
            var childHeight = childOne.getMeasuredHeight();
            //View的宽度=单个子View宽度*子View个数，View的高度=子View高度
            setMeasuredDimension(getChildCount() * childWidth, childHeight);
        } else if (widthMode == MeasureSpec.AT_MOST) {
            var childOne = getChildAt(0);
            var childWidth = childOne.getMeasuredWidth();
            //View的宽度=单个子View宽度*子View个数，View的高度=xml当中设置的高度
            setMeasuredDimension(getChildCount() * childWidth, heightSize);
        } else if (heightMode == MeasureSpec.AT_MOST) {
            var childOne = getChildAt(0);
            var childHeight = childOne.getMeasuredHeight();
            //View的宽度=xml当中设置的宽度，View的高度=子View高度
            setMeasuredDimension(widthSize, childHeight);
        }

    }

    /**
     * 布局内容
     */
    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        var childCount = childCount
        var left = 0
        var child:View
        for (index in 0 until  childCount){
            child = getChildAt(index)
            if(child.visibility != View.GONE){
                childWidth = child.measuredWidth
                child.layout(left,0,left + childWidth,child.measuredHeight)
                left += childWidth + 50
            }
        }
    }

//以下为按键处理事件
    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        var intercept = false
        var x = ev?.x!!
        var y = ev?.y!!
        when(ev?.action){
            MotionEvent.ACTION_MOVE -> {
                var delX = x - lastX!!
                var delY = y - lastY
                if(delX > delY) intercept = true
                Logger.Instance.d(TAG,"onInterceptTouchEvent lastPoint:{x:$lastX y:$lastY} curPoint:{x:$x y:$y} delta:{x:$delX y:$delY} intercept:$intercept")
            }
        }
        lastX = x
        lastY = y
        return intercept
    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        return super.dispatchTouchEvent(ev)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        var x = event?.x!!
        var y = event?.y!!
        when(event.action){
            MotionEvent.ACTION_MOVE ->{
                var delX = x - lastX
                var delY = y - lastY
                Logger.Instance.d(TAG,"onTouchEvent ACTION_MOVE lastPont{x:$lastX y:$lastY} delX:$delX delY:$delY")
                scrollBy(-delX.toInt(),0)
            }
        }
        return true
    }

}
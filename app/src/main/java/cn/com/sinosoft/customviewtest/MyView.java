package cn.com.sinosoft.customviewtest;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * 以后再写····可能会忘记不写了····
 * 主要就是onMeasure和onDraw方法
 * 关于自定义view的组合view····还没开始····
 * 贝塞尔曲线·····不会先看看···
 *
 */
public class MyView extends View {

    /**
     * java代码直接new出来
     * @param context
     */
    public MyView(Context context) {
        super(context);
    }

    /**
     * 如果该view直接在xml文件中申明，则调用该函数
     * 自定义属性有AttributeSet传进来
     * @param context
     * @param attrs
     */
    public MyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * 这个不会自动调用
     * 通常是在第二个构造函数中主动调用
     * 当view 有style属性的时候
     * @param context
     * @param attrs
     * @param defStyleAttr
     */
    public MyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * API 21 之后开始使用
     * 这个不会自动调用
     * 通常是在第二个构造函数中主动调用
     * 当view 有style属性的时候
     * @param context
     * @param attrs
     * @param defStyleAttr
     * @param defStyleRes
     */
    public MyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }
}

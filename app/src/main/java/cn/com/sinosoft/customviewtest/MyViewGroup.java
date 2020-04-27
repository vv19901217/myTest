package cn.com.sinosoft.customviewtest;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * demo：
 * 自己手写一个自定义的流式布局，类名比较随意......
 * padding````
 * <p>
 * 通常自定义的绘制流程：onMeasure（测量view的大小）--> onLayout (确定每个子view的布局) --> onDraw(实际的绘制内容)
 * <p>
 * 自定义view主要是实现 onMeasure和onDraw
 * 自定义viewgroup主要就是实现onMeasure和onLayout
 */
public class MyViewGroup extends ViewGroup {

    private int mHorizontalSpacing = 10; //每个item横向间距
    private int mVerticalSpacing = 10; //每个item横向间距
    private List<List<View>> allviews; // 记录所有的行，一行一行的存储
    List<Integer> lineHeights; // 记录每一行的行高

    /**
     * 在java中直接new
     *
     * @param context
     */
    public MyViewGroup(Context context) {
        super(context);
    }

    /**
     * xml申明
     *
     * @param context
     * @param attrs
     */
    public MyViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * xml申明且有style
     *
     * @param context
     * @param attrs
     * @param defStyleAttr
     */
    public MyViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    public MyViewGroup(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }


    /**
     * 初始化
     */
    private void initMeasure() {
        allviews = new ArrayList<>();
        lineHeights = new ArrayList<>();

    }

    /**
     * 测量
     * onMeasure的目的就是去测量子view 在父view的占用的大小，在特定的模式下去执行操作......
     * 每添加一个子view 父view 的size都是动态变化的！
     * <p>
     * 以树形结构的方式去测量所有的childview，会触发每个childview 的onMeasure函数
     * 需要理解的点：
     * MeasureSpec：
     * 1. 32位的int类型；高两位表示mode，低30位表示size
     * 2. 测量模式 mode + 尺寸信息size （size 低于30位）构成；
     * mode分三类：
     * 1. UNSPECIFIED 一般是系统控件使用，无限制----->例如listview
     * 2. EXACTLY  给出一个具体的dp值---->例如指定100dp或者march_parent
     * 3. AT_MOST  不超过父类控件的宽高 -----> warp_parent,通常都考虑这个情况，其他的直接沿用系统的测量值吧！
     * <p>
     * <p>
     * 注意：
     * getWidth()方法和getMeasureWidth()的值基本相同，但是getWidth必须在onLayout执行之后才有值，而onlayout执行在onMeasure之后，所以要使用getMeasureWidth();height也一样！
     *
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        initMeasure();
// TODO: 2020/4/22  开始测量view大小

//        viewgroup的解析的宽高,参数就是onMeasure中的widthMeasureSpec，heightMeasureSpec
        int viewgroup_width = MeasureSpec.getSize(widthMeasureSpec);
        int viewgroup_height = MeasureSpec.getSize(heightMeasureSpec);


//       测量所有的view大小

        int childview_count = getChildCount();//获取所有的childview数量
//        获取view距离上下左右的值
        int paddingLeft = getPaddingLeft();
        int paddingRight = getPaddingRight();
        int paddingTop = getPaddingTop();
        int paddingBottom = getPaddingBottom();

        int line_width = 0;//记录单行的宽
        int line_height = 0;//记录单行的高
        int childview_width = 0;//测量的childview的宽
        int childview_height = 0;//测量的childview的宽
        int myview_width = 0;//最终测量出来的MyViewGroup的宽
        int myview_height = 0;//最终测量出来的MyViewGroup的高

        List<View> viewList = new ArrayList<>();//存放每行view的arraylist

//        遍历所有的view
        for (int i = 0; i < childview_count; i++) {
            View childview = getChildAt(i);

            LayoutParams childViewLayoutParams = childview.getLayoutParams();
            int childWidth_measureSpec = getChildMeasureSpec(widthMeasureSpec, paddingLeft + paddingRight, childViewLayoutParams.width);
            int childHeight_measureSpec = getChildMeasureSpec(heightMeasureSpec, paddingTop + paddingBottom, childViewLayoutParams.height);
            childview.measure(childWidth_measureSpec, childHeight_measureSpec);
            childview_width = childview.getMeasuredWidth();
            childview_height = childview.getMeasuredHeight();
            Log.e("zzw", "count=" + childview_count + "\nwidth==" + childview_width + "\n" +
                    "heigh==" + childview_height + "\nline_width=" + line_width);

            if (line_width + childview_width + mHorizontalSpacing > viewgroup_width) {//超出就要换行

//                可以确定此时父view的宽高
                myview_width = Math.max(myview_width, line_width);
                myview_height = line_height + line_height + mVerticalSpacing;

                allviews.add(viewList);
                lineHeights.add(line_height);


//                viewList.clear();
                viewList = new ArrayList<>();
                line_width = 0;
                line_height = 0;
            }

            line_width = line_width + childview_width + mHorizontalSpacing;
            line_height = Math.max(line_height, childview_height + mVerticalSpacing);
            viewList.add(childview);


//                判断是不是最后一个view
            if (i == childview_count - 1) {
                lineHeights.add(line_height);
                allviews.add(viewList);
                myview_width = Math.max(myview_width, line_width);//找最宽的
                myview_height += myview_height;//累加所有的高度
            }


        }
        //根据子View的度量结果，来重新度量自己ViewGroup
        // 作为一个ViewGroup，它自己也是一个View,它的大小也需要根据它的父亲给它提供的宽高来度量
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        int realWidth = (widthMode == MeasureSpec.EXACTLY) ? viewgroup_width : myview_width;
        int realHeight = (heightMode == MeasureSpec.EXACTLY) ? viewgroup_height : myview_height;

        setMeasuredDimension(realWidth, realHeight);


    }


    /**
     * 确定布局位置，parms是相对于父控件的
     *
     * @param changed
     * @param l
     * @param t
     * @param r
     * @param b
     */
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        // TODO: 2020/4/22 测量好了大小就开始摆放位置了！
        int cur_left = 0; //左边距
        int cur_top = 0; //上边距

        for (int i = 0; i < allviews.size(); i++) {
            // 获取行数
            List<View> lines = allviews.get(i);
            for (int j = 0; j < lines.size(); j++) {

                View view = lines.get(j); //找到每一个view,子view 距离 父控件左边和上边 的 边距
                int left = cur_left;
                int top = cur_top;
                int bottom = top + view.getMeasuredHeight();
                int right = left + view.getMeasuredWidth();

                view.layout(left, top, right, bottom);//注意顺序是左 - 上 - 右 - 下
//               在同一行内， 摆放一个后，下一个的左边距要加上前面摆放好的边距，此时cur_left需要赋值
                cur_left = right + mHorizontalSpacing;//注意不要漏掉间隔
            }
//            换行后，cur_left需要置0,但是top需要加上已摆放行的
            cur_left = 0;
            cur_top = cur_top + mVerticalSpacing + lineHeights.get(i);
        }


    }

    /**
     * 问题：
     *     1. xml声明该控件，设置padding属性，会导致边上的部分不显示,设置margin属性正常
     *     2. 如果在流式布局中嵌套一个流式布局去展示，嵌套的流式布局下方与接下来的控件的高度距离，这个垂直间距和理想的不一样！
     *     3。 关于最后一行的处理的问题
     */

}

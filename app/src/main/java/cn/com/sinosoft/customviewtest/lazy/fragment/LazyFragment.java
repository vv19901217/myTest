package cn.com.sinosoft.customviewtest.lazy.fragment;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import cn.com.sinosoft.customviewtest.R;
import cn.com.sinosoft.customviewtest.customview.AppProgressDialog;

/**
 * 懒加载 基类
 * <p>
 * 目的就是关闭viewpager的预加载机制，实现哪一个页面被用户看见，则操作数据
 * 方法setUserVisibleHint（AndroidX过时了）：FragmentManager并没有提供为Fragment被用户所看到的回调方法，而是在FragmentPagerAdapter和FragmentStatePagerAdapter中，
 * 调用了Fragment.setUserVisibleHint(boolean)来表明Fragment是否已经被作为primaryFragment. 所以这个方法可以被认为是一个回调方法。
 * 方法setUserVisibleHint此方法会在onCreateView(）之前执行，所以为了防止首次调用还未创建fragment，所以要判断fragment是否初始化，
 * 其次要对生命周期做好处理
 */
public abstract class LazyFragment extends Fragment {

    /**
     * 是否初始化过布局
     */
    protected boolean isViewInitiated = false;
    /**
     * 当前界面是否可见
     */
    protected boolean isVisibleToUser = false;
    /**
     * 是否加载过数据
     */
    protected boolean isDataInitiated = false;

    ImageView loadingImg;
    AnimationDrawable animationDrawable;
    private AppProgressDialog progressDialog;

    View view;
    FrameLayout vp;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(getLayoutResId(), container, false);
        initData();
        initView();
        vp = view.findViewById(R.id.rootview);
        return view;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        isViewInitiated = true;
        load();
    }

    /**
     * 返回布局
     *
     * @return
     */
    public View getView() {
        return view;
    }

    ;

    /**
     * 返回布局
     *
     * @return
     */
    public View getRootView() {
        return view;
    }


    public abstract int getLayoutResId();

    public abstract void initView();

    /**
     * 懒加载的话，support包的核心处理方法
     *
     * @param isVisibleToUser
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

        if (isViewInitiated) {
            this.isVisibleToUser = isVisibleToUser;

            if (isVisibleToUser) {
                //可见，加载数据
                load();

            } else {
                //不可见

            }

        }
    }

    /**
     * 懒加载请求的数据
     */
    public abstract void lazyLoadDate();

    public void load() {
        if (isVisibleToUser && isViewInitiated && (!isDataInitiated)) {
            showProgressDialog();
            lazyLoadDate();
            dismissProgressDialog();
            isDataInitiated = true;
        }
//        if (!isDataInitiated) {
//            lazyLoadDate();
//            isDataInitiated=true;
//        }
    }

    ImageView imageView;

    public void startanime() {

        Log.e("zzw_loading", "go");
        //创建动画
        imageView = new ImageView(getActivity());
        imageView.setBackgroundResource(R.drawable.progressanime );
//        imageView.setImageResource(R.drawable.progressanime);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        layoutParams.gravity = Gravity.CENTER;
        imageView.setLayoutParams(layoutParams);
        Log.e("zzw_loading", vp == null ? "vp==null" : "vp!=null");

        vp.addView(imageView);
        animationDrawable= (AnimationDrawable) imageView.getBackground();
//        animationDrawable = (AnimationDrawable) imageView.getDrawable();
        animationDrawable.start();
        Log.e("zzw_loading", "animationDrawable.start!");

    }


    public void stopanime() {
        //销毁动画
        animationDrawable.stop();
        animationDrawable = null;
        if (imageView != null) {
            vp.removeView(imageView);
        }
    }

    ;

    /**
     * 初始化数据（跳转携带，或者缓存中）
     */
    protected abstract void initData();


    @Override
    public void onDestroy() {
        super.onDestroy();
        if (imageView != null) {
            vp.removeView(imageView);
            vp = null;
        }
        if (animationDrawable != null) {
            animationDrawable = null;
        }

        if (progressDialog != null) {
            progressDialog.dismissDialog();
            progressDialog = null;
        }
        isViewInitiated = false;
        isVisibleToUser = false;
        isDataInitiated = false;
        this.view = null;
    }


    /**
     * 显示进度框
     */
    public synchronized void showProgressDialog() {
        if (progressDialog == null) {
            progressDialog = new AppProgressDialog();
        }
        progressDialog.show(getActivity());
    }

    /**
     * 隐藏进度框
     */
    public void dismissProgressDialog() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismissDialog();
        }
    }

}

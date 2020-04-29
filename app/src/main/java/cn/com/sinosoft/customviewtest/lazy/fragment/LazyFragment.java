package cn.com.sinosoft.customviewtest.lazy.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

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

    View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(getLayoutResId(), container, false);
        initView();
        isViewInitiated = true;
        return view;

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
     * 返回当前页面可见状态，不可见就直接销毁页面的话，这个方法感觉有点多余？？
     * 默认false
     *
     * @return
     */
    public boolean isVisibleToUser() {
        return isVisibleToUser;
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
                lazyLoadDate();


            } else {
                //不可见

            }

        }
    }

    /**
     * 懒加载请求的数据
     */
    public abstract void lazyLoadDate() ;

    /**
     * 初始化数据（跳转携带，或者缓存中）
     */
    protected abstract void initData();


    @Override
    public void onDestroy() {
        super.onDestroy();
        isViewInitiated = false;
        isVisibleToUser = false;
    }
}

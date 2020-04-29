package cn.com.sinosoft.customviewtest.lazy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import cn.com.sinosoft.customviewtest.R;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import cn.com.sinosoft.customviewtest.lazy.adapter.FragmentAdapter;
import cn.com.sinosoft.customviewtest.lazy.fragment.AFragment;
import cn.com.sinosoft.customviewtest.lazy.fragment.BFragment;
import cn.com.sinosoft.customviewtest.lazy.fragment.CFragment;
import cn.com.sinosoft.customviewtest.lazy.fragment.DFragment;
import cn.com.sinosoft.customviewtest.lazy.bean.TabEntity;

/**
 * viewpager +fragenmt是一种常用主页界面的写法，viewpager因为其预加载机制，会占用较多内存，
 * 当页面所需存储的元素过多，会造成oom
 * 本身这种机制，如果处理不好，就会造成网路资源滥用和界面的过度绘制
 * 微信主界面就是懒加载
 * 关于vp.setOffscreenPageLimit(0)没用，是因为传入的值小于1都会默认为1
 * viewpager源码中的方法，populate(int newCurrentItem) 很重要，通过这个方法和
 * 设置的setOffscreenPageLimit来计算出需要缓存的页面和需要销毁的页面
 *
 */
public class Main2Activity extends AppCompatActivity implements ViewPager.OnPageChangeListener {

    public static void naveToLazy(Context context){
        Intent intent=new Intent();
        intent.setClass(context,Main2Activity.class);
        context.startActivity(intent);
    }

    private ViewPager viewPager;
    private TabLayout tableLayout;

    private String[] nameStrs={"A页","B页","C页","D页"};
    private ArrayList<Fragment> fragments;
    private ArrayList<TabEntity> tabs=new ArrayList<>();
    private FragmentAdapter fragmentAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        initview();
    }

    private void initview() {
        viewPager=findViewById(R.id.viewpager);
        tableLayout=findViewById(R.id.tablayout);
        tabs.clear();
        for (int i = 0; i < nameStrs.length; i++) {
            TabEntity tab=new TabEntity(nameStrs[i],i) ;
            tabs.add(tab);
        }
        fragments=new ArrayList<>();
            fragments.add(AFragment.newInstance());
            fragments.add(BFragment.newInstance());
            fragments.add(CFragment.newInstance());
            fragments.add(DFragment.newInstance());
        fragmentAdapter=new FragmentAdapter(getSupportFragmentManager(),fragments);
        fragmentAdapter.setTabTitle(tabs);
        viewPager.setAdapter(fragmentAdapter);
        //将ViewPager与TabLayout绑定
        tableLayout.setupWithViewPager(viewPager);
        viewPager.addOnPageChangeListener(this);

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}

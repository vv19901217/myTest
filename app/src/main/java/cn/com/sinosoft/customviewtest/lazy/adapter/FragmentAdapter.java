package cn.com.sinosoft.customviewtest.lazy.adapter;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import cn.com.sinosoft.customviewtest.lazy.bean.TabEntity;

/**
 * viewpager的适配器
 */
public class FragmentAdapter extends FragmentPagerAdapter {

    private List<Fragment> fragments;

    public FragmentAdapter(@NonNull FragmentManager fm,List<Fragment> fragments) {
        super(fm);
        this.fragments=fragments;
    }
    private ArrayList<TabEntity> tabTitle;
    public void setTabTitle(ArrayList<TabEntity> tabTitle){
        this.tabTitle=tabTitle;

    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitle.get(position).getTitle();
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}

package cn.com.sinosoft.customviewtest.lazy.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cn.com.sinosoft.customviewtest.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class DFragment extends Fragment {

    // Required empty public constructor
    public static DFragment newInstance() {
        DFragment fragment = new DFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_d, container, false);
    }
}

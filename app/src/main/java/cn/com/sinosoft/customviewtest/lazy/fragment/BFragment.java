package cn.com.sinosoft.customviewtest.lazy.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import cn.com.sinosoft.customviewtest.R;
import cn.com.sinosoft.customviewtest.lazy.Constants;
import cn.com.sinosoft.customviewtest.lazy.adapter.DataBindAdapter;
import cn.com.sinosoft.customviewtest.lazy.bean.DateBean;
import cn.sinosoft.myokhttp.MyOkHttp;
import cn.sinosoft.myokhttp.response.JsonResponseHandler;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BFragment extends Fragment {

    RecyclerView recyclerView;
    DataBindAdapter adapter;
    List<DateBean> dateBeanList=new ArrayList<>();
    // Required empty public constructor
    public static BFragment newInstance() {
        BFragment fragment = new BFragment();
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_b, container, false);
        initview(view);
        return view;
    }
    /**
     * 网络请求
     */
    private void getData() {
        Map<String, String> params = new HashMap<String, String>();
        MyOkHttp.get().post(Constants.URL + Constants.method_b, params, new JsonResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                try {

                    dateBeanList.clear();
                    JSONObject jsonObject=new JSONObject(response);
                    Log.e("zzw_success+333",dateBeanList.toString());

                    JSONArray jsonArray=jsonObject.optJSONArray("list");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        Log.e("zzw_success+jsonarray",jsonArray.length()+"");

                        JSONObject jsonObject1=jsonArray.optJSONObject(i);
                        DateBean bean=new DateBean();
                        bean.setName(jsonObject1.optString("name"));
                        bean.setAge(jsonObject1.optInt("age"));
                        bean.setSex(jsonObject1.optString("sex"));
                        bean.setImgurl(jsonObject1.optString("headimg"));
                        dateBeanList.add(bean);

                    }
                    adapter.refresh(dateBeanList);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(int statusCode, String error_msg) {
                Log.e("zzw_error",statusCode+error_msg);

            }
        });

    }

    private void initview(View view) {

        adapter=new DataBindAdapter(dateBeanList,getActivity());

        recyclerView=view.findViewById(R.id.datalistview);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(),RecyclerView.VERTICAL,false);

        recyclerView.setLayoutManager(
                layoutManager
        );
        if (adapter==null){
            Log.e("zzw_error","nullnullnull");

        }else {
            recyclerView.setAdapter(adapter);

        }

        getData();

    }
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
//        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser){

        }else {

        }
    }
}

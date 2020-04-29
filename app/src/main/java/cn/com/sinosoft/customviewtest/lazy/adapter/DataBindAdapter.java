package cn.com.sinosoft.customviewtest.lazy.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import cn.com.sinosoft.customviewtest.lazy.bean.DateBean;
import cn.com.sinosoft.customviewtest.R;

public class DataBindAdapter extends RecyclerView.Adapter {

    private List<DateBean> datalist;
    private Context context;
    public static final int VIEW_TYPE_ITEM = 1;
    public static final int VIEW_TYPE_EMPTY = 0;
    LayoutInflater inflater;

    public DataBindAdapter(List<DateBean> datalist, Context context) {
        Log.e("zzw_error","7");

        this.datalist = datalist;
        this.context = context;
        inflater=LayoutInflater.from(context);

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        if (getItemViewType(viewType) == VIEW_TYPE_ITEM) {
            v = inflater.inflate(R.layout.item_lazyitem, parent, false);
        } else {
            v = inflater.inflate(R.layout.item_empty, parent, false);
        }
        Log.e("zze=adapter", "onCreateViewHolder" + datalist.size());

        return new VH(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Log.e("zze=adapter", "onBindViewHolder" + datalist.size());

        if (getItemViewType(position) == VIEW_TYPE_ITEM) {
            ((VH) holder).nameview.setText(String.format(context.getResources().getString(R.string.name_string), datalist.get(position).getName()));
            ((VH) holder).ageview.setText(String.format(context.getResources().getString(R.string.age_string), datalist.get(position).getAge()));
            ((VH) holder).sexview.setText(String.format(context.getResources().getString(R.string.sex_string), datalist.get(position).getSex()));
            setImg(((VH) holder).imgurl, datalist.get(position).getImgurl(), R.mipmap.noimg);

        }
    }

    private void setImg(ImageView view, String imgurl, int default_image) {
        RequestOptions requestOptions = new RequestOptions();
        requestOptions
                .error(default_image);
        Glide.with(context).load(imgurl)
                .apply(requestOptions.circleCrop())
                .into(view);

    }

    @Override
    public int getItemCount() {
        Log.e("zze_adapter", datalist.size() + "=RESULT");

        return datalist.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (datalist.size() == 0) {
            return VIEW_TYPE_EMPTY;
        } else {
            return VIEW_TYPE_ITEM;
        }
    }

    public void refresh(List<DateBean> datalist) {
        Log.e("zze_adapter", datalist.size() + "=houlai");
        this.datalist = datalist;
        notifyDataSetChanged();
    }

    static class VH extends RecyclerView.ViewHolder {

        ImageView imgurl;
        TextView nameview;
        TextView ageview;
        TextView sexview;

        public VH(@NonNull View itemView) {
            super(itemView);
            imgurl = itemView.findViewById(R.id.imgview);
            nameview = itemView.findViewById(R.id.nameview);
            ageview = itemView.findViewById(R.id.ageview);
            sexview = itemView.findViewById(R.id.sexview);        }


    }

}

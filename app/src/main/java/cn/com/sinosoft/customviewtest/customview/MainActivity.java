package cn.com.sinosoft.customviewtest.customview;

import androidx.appcompat.app.AppCompatActivity;
import cn.com.sinosoft.customviewtest.R;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    public static void naveToCV(Context context){
        Intent intent=new Intent();
        intent.setClass(context,MainActivity.class);
        context.startActivity(intent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}

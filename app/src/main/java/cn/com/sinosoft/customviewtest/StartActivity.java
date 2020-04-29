package cn.com.sinosoft.customviewtest;

import androidx.appcompat.app.AppCompatActivity;
import cn.com.sinosoft.customviewtest.customview.MainActivity;
import cn.com.sinosoft.customviewtest.lazy.Main2Activity;

import android.os.Bundle;
import android.view.View;

public class StartActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
    }

    public void goLazy(View view){
        Main2Activity.naveToLazy(this);

    }
    public void gocv(View view){
        MainActivity.naveToCV(this);
    }
}

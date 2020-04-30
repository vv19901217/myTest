package cn.com.sinosoft.customviewtest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import cn.com.sinosoft.customviewtest.customview.MainActivity;
import cn.com.sinosoft.customviewtest.lazy.Main2Activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;

public class StartActivity extends AppCompatActivity {



    private final int mRequestCode = 100;//权限请求码

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        if (!checkBYPermission()){
            requestPermission();
        }
    }
    /**
     * 检测权限
     *
     * @return
     */
    public boolean checkBYPermission() {
        String[] permissions = new String[]{
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE
        };
        if (ContextCompat.checkSelfPermission(this, permissions[0]) !=
                PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(this, permissions[1]) !=
                PackageManager.PERMISSION_GRANTED) {
            return false;
        } else {
            return true;
        }

    }
    /**
     * 请求权限
     */
    public void requestPermission() {
        String[] permissions = new String[]{
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE
        };
        ActivityCompat.requestPermissions(this, permissions, mRequestCode);

    }

    public void goLazy(View view){
        Main2Activity.naveToLazy(this);
    }
    public void gocv(View view){
        MainActivity.naveToCV(this);
    }
}

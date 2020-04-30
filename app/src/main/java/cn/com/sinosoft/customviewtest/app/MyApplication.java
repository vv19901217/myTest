package cn.com.sinosoft.customviewtest.app;

import android.app.Application;

import cn.com.sinosoft.customviewtest.ExceptionHandler;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        ExceptionHandler.getInstance().initConfig(this);
    }
}

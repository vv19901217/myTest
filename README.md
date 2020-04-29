# myTest
&emsp;&emsp;自定义view学习和viewpager+fragment懒加载问题


## 自定义viewGroup
&emsp;&emsp;MyViewGroup 继承ViewGroup，主要重写onMeasure和onLayout方法，自定义实现一个流式布局<br>
其他参见注释

## 自定义view
&emsp;&emsp;没写
## 懒加载
&emsp;&emsp;主界面还是用tablayout+viewpager来实现常用的导航栏+布局
## myokhttp
&emsp;&emsp;以前抄的谁的封装？真的忘记了·····<br>
    implementation 'com.squareup.okhttp3:okhttp:3.4.1'<br>
    implementation 'com.google.code.gson:gson:2.7'<br>
    implementation 'com.squareup.okio:okio:1.9.0'<br>
## 接口
&emsp;&emsp;使用的http://mock-api.com<br>
&emsp;&emsp;模拟post接口请求数据

# 问题：
E/RecyclerView: No adapter attached; skipping layout
recyclerview初始化了，设置layoutmanager了，网络请求有数据了，获取的size 也不是0，setAdapter了，Addapter检查了，但还总是报这个异常，导致不加载数据
纠结闹心


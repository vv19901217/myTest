# myTest
&emsp;&emsp;自定义view学习和viewpager+fragment懒加载问题


## 自定义viewGroup
&emsp;&emsp;MyViewGroup 继承ViewGroup，主要重写onMeasure和onLayout方法，自定义实现一个流式布局<br>
其他参见注释

## 自定义view
&emsp;&emsp;没写
## 懒加载（写的有点问题）
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
debug：adapter是null·····log发现在json解析时有问题！
解决：接口模拟的json写了个中文逗号·····尴尬极了····

# 插件化（暂时懒得写·····）
&emsp;&emsp;android 插件化····**还没开始写**

## 一些说明
&emsp;&emsp;关于插件化的理解，区别于组件化，模块化<br>
&emsp;&emsp;什么是插件化：简单理解，免安装apk
## 插件化的好处：
* 在功能逐步迭代的情况下，能有效的缩减apk的体积
* 协同开发耦合度高，成本过高！能有效降低
* 方法数目超过65535  
* 应用之间的相互调用
## 类加载器




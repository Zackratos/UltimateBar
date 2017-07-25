# UltimateBar
透明状态栏导航栏、沉浸式状态栏导航栏的终极解决方案


## 特点：

1.四种效果，自定义颜色的状态栏和导航栏，半透明状态栏和导航栏，
沉浸式状态栏和导航栏，隐藏状态栏和导航栏；<br/><br/>
2.可以自定义状态栏和导航栏的颜色和透明度；<br/><br/>
3.KITKAT(Android 4.4)和LOLLIPOP(Android 5.0)上显示效果高度统一。<br/><br/>



## 使用方法：


首先添加依赖：

```xml
compile 'org.zackratos:ultimatebar:1.0.3'
```

<br/><br/>

### 1.自定义颜色的状态栏和导航栏

在 onCreate() 方法中：<br/><br/>

```java
UltimateBar ultimateBar = new UltimateBar(this);
ultimateBar.setColorBar(ContextCompat.getColor(this, R.color.DeepSkyBlue));
```

<br/><br/>
<img src="Screenshots/KITKAT_0.png" width="300px"/>
<img src="Screenshots/LOLLIPOP_0.png" width="300px"/>


<br/><br/>

如果需要设置不透明度：<br/><br/>

```java
UltimateBar ultimateBar = new UltimateBar(this);
ultimateBar.setColorBar(ContextCompat.getColor(this, R.color.SpringGreen), 50);
```

<br/><br/>
<img src="Screenshots/KITKAT_1.png" width="300px"/>
<img src="Screenshots/LOLLIPOP_1.png" width="300px"/>


<br/><br/><br/><br/>

### 2.半透明状态栏和导航栏

在 onCreate() 方法中：<br/><br/>

```java
UltimateBar ultimateBar = new UltimateBar(this);
ultimateBar.setTransparentBar(Color.BLUE, 50);
```

<br/><br/>
<img src="Screenshots/KITKAT_3.png" width="300px"/>
<img src="Screenshots/LOLLIPOP_3.png" width="300px"/>
<img src="Screenshots/KITKAT_4.png" width="300px"/>
<img src="Screenshots/LOLLIPOP_4.png" width="300px"/>


<br/><br/><br/><br/>

### 3.沉浸式状态栏和导航栏：

在 onCreate() 方法中：<br/><br/>

```java
UltimateBar ultimateBar = new UltimateBar(this);
ultimateBar.setImmersionBar();
```

<br/><br/>
<img src="Screenshots/KITKAT_2.png" width="300px"/>
<img src="Screenshots/LOLLIPOP_2.png" width="300px"/>


<br/><br/><br/><br/>



### 4.隐藏状态栏和导航栏：

在 onWindowFocusChanged() 方法中：<br/><br/>

```java
@Override
public void onWindowFocusChanged(boolean hasFocus) {
    super.onWindowFocusChanged(hasFocus);
    if (hasFocus) {
        UltimateBar ultimateBar = new UltimateBar(this);
        ultimateBar.setHintBar();
    }
}
```

<br/><br/>
<img src="Screenshots/LOLLIPOP_5.png" width="300px"/>
<img src="Screenshots/LOLLIPOP_6.png" width="300px"/>


<br/><br/><br/><br/>


### 5.在 DrawerLayout 中设置自定义颜色的状态栏和导航栏：

首先需要设置 DrawerLayout 下面的主局部中添加 android:fitsSystemWindows="true"：

```xml
<android.support.v4.widget.DrawerLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:id="@+id/drawer_layout"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:fitsSystemWindows="true"
        android:orientation="vertical">
    </LinearLayout>

    <FrameLayout
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:background="@color/SpringGreen"
        android:layout_gravity="left"/>

</android.support.v4.widget.DrawerLayout>
```

注意是 DrawerLayout 下面的主布局，DrawerLayout 本身和抽屉布局都不能添加。

然后在 onCreate() 方法中：<br/><br/>

```java
UltimateBar ultimateBar = new UltimateBar(this);
ultimateBar.setColorBarForDrawer(ContextCompat.getColor(this, R.color.DeepSkyBlue));
```

<br/><br/>

<img src="Screenshots/KITKAT_7.png" width=300px/>  <img src="Screenshots/LOLLIPOP_7.png" width=300px/>

如果需要设置不透明度：<br/><br/>

```java
UltimateBar ultimateBar = new UltimateBar(this);
ultimateBar.setColorBarForDrawer(ContextCompat.getColor(this, R.color.DeepSkyBlue), 50);
```



<br/><br/><br/><br/>



## 更新日志

### v1.0.3
1.在 Android 4.4 中使用自定义颜色的状态栏和导航栏的时候，如果没有导航栏，不设置导航栏。


### v1.0.2
1.增加 DrawerLayout 使用的自定义颜色的状态栏和导航栏。

### v1.0.1
1.判断当状态栏不存在时，不对状态栏进行设置；<br/><br/>
2.自定义颜色的状态栏和导航栏中，当加深程度为 0 时，直接设置为原颜色，即不加深颜色；<br/><br/>
3.半透明的状态栏和导航栏中，当不透明度为 0 时，直接设为完全透明，即不进行不透明度计算；<br/><br/>
4.修改包名。


### v1.0.0
1.四种效果，自定义颜色的状态栏和导航栏，半透明状态栏和导航栏，
沉浸式状态栏和导航栏，隐藏状态栏和导航栏；<br/><br/>
2.可以自定义状态栏和导航栏的颜色和透明度；<br/><br/>
3.KITKAT(Android 4.4)和LOLLIPOP(Android 5.0)上显示效果高度统一。<br/><br/>



## License

```
Copyright 2017 Zackratos

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
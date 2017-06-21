# UltimateBar
Transparent statusbar and navigationbar

## v1.0.0

### 特点：<br/>

1.三种样式，自定义颜色的状态栏和导航栏，沉浸式状态栏和导航栏，隐藏状态栏和导航栏；<br/><br/>
2.可以自定义状态栏和导航栏的颜色和透明度；<br/><br/>
3.KITKAT(Android 4.4)和LOLLIPOP(Android 5.0)上显示效果高度统一。<br/><br/>



### 使用方法：

<!--<figure>
    <img src="Screenshots/KITKAT_0.png" width="300px"/>
        <figcaption>
            KITKAT
        </figcaption>
</figure>

<figure>
    <img src="Screenshots/LOLLIPOP_0.png" width="300px"/>
        <figcaption>
            LOLLIPOP
        </figcaption>
</figure>-->

首先添加依赖：

```xml
compile 'org.zackratos:ultimatebar:1.0.0'
```

<br/><br/>

#### 1.自定义颜色的状态栏和导航栏

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

#### 2.半透明状态栏和导航栏

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

#### 3.沉浸式状态栏和导航栏：

在 onCreate() 方法中：<br/><br/>

```java
UltimateBar ultimateBar = new UltimateBar(this);
ultimateBar.setImmersionBar();
```

<br/><br/>
<img src="Screenshots/KITKAT_2.png" width="300px"/>
<img src="Screenshots/LOLLIPOP_2.png" width="300px"/>


<br/><br/><br/><br/>



#### 4.隐藏状态栏和导航栏：

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
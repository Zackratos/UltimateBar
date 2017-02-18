# UltimateBar
Transparent statusbar and navigationbar

## v1.0

###特点：

1.三种样式，自定义颜色的状态栏和导航栏，沉浸式状态栏和导航栏，隐藏状态栏和导航栏；<br/>
2.可以自定义状态栏和导航栏的颜色和透明度；<br/>
3.KITKAT(Android 4.4)和LOLLIPOP(Android 5.0)上显示效果高度统一。<br/>

###使用方法：

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

<br/>
<br/>


让需要使用沉浸或透明状态栏的 Activity 继承自 UltimateBarActivity，然后重写 initBar() 方法

<br/>
<br/>


<img src="Screenshots/KITKAT_0.png" width="400px"/>
<img src="Screenshots/LOLLIPOP_0.png" width="400px"/>

```java
    @Override
    protected void initBar() {
        setColorBar(ContextCompat.getColor(this, R.color.DeepSkyBlue));
    }
```

<br/>
<br/>
<br/>
<br/>

<img src="Screenshots/KITKAT_1.png" width="400px"/>
<img src="Screenshots/LOLLIPOP_1.png" width="400px"/>

```java
    @Override
    protected void initBar() {
        setColorBar(ContextCompat.getColor(this, R.color.SpringGreen), 50);
    }
```

<br/>
<br/>
<br/>
<br/>

<img src="Screenshots/KITKAT_2.png" width="400px"/>
<img src="Screenshots/LOLLIPOP_2.png" width="400px"/>

```java
    @Override
    protected void initBar() {
        setImmersionBar();
    }
```

<br/>
<br/>
<br/>
<br/>

<img src="Screenshots/KITKAT_3.png" width="400px"/>
<img src="Screenshots/LOLLIPOP_3.png" width="400px"/>

```java
    @Override
    protected void initBar() {
        setTransparentBar(Color.BLUE, 50);
    }
```

<br/>
<br/>
<br/>
<br/>


<img src="Screenshots/KITKAT_4.png" width="400px"/>
<img src="Screenshots/LOLLIPOP_4.png" width="400px"/>

```java
    @Override
    protected void initBar() {
        setTransparentBar(Color.GREEN, 50);
    }
```

<br/>
<br/>
<br/>
<br/>

<img src="Screenshots/LOLLIPOP_5.png" width="400px"/>
<img src="Screenshots/LOLLIPOP_6.png" width="400px"/>

```java
    @Override
    protected void initBar() {
        setHintBar();
    }
```

<br/>
<br/>
<br/>
<br/>
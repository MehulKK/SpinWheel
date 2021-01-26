# MWheel Android

[![Kotlin Version](https://img.shields.io/badge/Kotlin-1.4.30-blue.svg)](https://kotlinlang.org)
[![Platform](https://img.shields.io/badge/Platform-Android-green.svg?style=flat)](https://www.android.com/)
[![API](https://img.shields.io/badge/API-16%2B-brightgreen.svg?style=flat)](https://android-arsenal.com/api?level=16#l16)

## Demo

![alt text](https://github.com/MehulKK/SpinWheel/blob/master/samples/spin.gif)



### Gradle Dependency
* Add the JitPack repository to your project's build.gradle file

```
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```

* Add the dependency in your app's build.gradle file

```
dependencies {
	implementation 'com.github.MehulKK:SpinWheel:1.0.2'
}
```

##### Configuration

## All Attributes
------------------------

| Attribute | Description | Default |
| --- | --- | --- |
| `app:background_color` | Choose the background color | `Color.parseColor('#141A1E')` |
| `app:arrow_image` | Choose wheel image arrow | `R.drawablw.ic_group_1459` |
| `app:image_padding` | Change item image padding | `0` |

## Usage
```
<com.macv.mwheel.MSpinWheel
        android:id="@+id/lwv"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:layout_centerInParent="true"
        LuckyWheel:background_color="#141A1E"/>
        ```

## Initialise
![alt text](https://github.com/MehulKK/SpinWheel/blob/master/samples/initialise.png)

## Listener
![alt text](https://github.com/MehulKK/SpinWheel/blob/master/samples/listener.png)

## Code
```
class MainActivity : AppCompatActivity() {
    private lateinit var lw: MSpinWheel
    private lateinit var wheelItems: ArrayList<WheelItem>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        generateWheelItems()
        lw = findViewById(R.id.lwv)
        lw.addWheelItems(wheelItems)
        lw.setWheelListener(object : WheelListener {
            override fun onOpen() {
                Toast.makeText(this@MainActivity, "Open ", Toast.LENGTH_LONG).show()
            }
            override fun onClose() {

            }
            override fun onReward(pos: Int) {
                Toast.makeText(this@MainActivity, "Target Reached " + wheelItems[pos].displayText, Toast.LENGTH_LONG).show()
            }

            override fun onStart() {
                Toast.makeText(this@MainActivity, "Start ", Toast.LENGTH_LONG).show()
            }

            override fun onError(s: String) {
                Toast.makeText(this@MainActivity, s, Toast.LENGTH_LONG).show()
            }
        })
        val start = findViewById<Button>(R.id.start)
        start.setOnClickListener { lw.rotateWheelTo(Random().nextInt(wheelItems.size)) }
    }

    private fun generateWheelItems() {
        wheelItems = ArrayList()
        wheelItems.add(WheelItem(Color.parseColor("#141A1E"), "1 $", 1, "USD"))
        wheelItems.add(WheelItem(Color.parseColor("#040D14"), "2 $", 2, "USD"))
        wheelItems.add(WheelItem(Color.parseColor("#141A1E"), "3 $", 3, "USD"))
        wheelItems.add(WheelItem(Color.parseColor("#040D14"), "4 $", 4, "USD"))
        wheelItems.add(WheelItem(Color.parseColor("#141A1E"), "5 $", 5, "USD"))
        wheelItems.add(WheelItem(Color.parseColor("#040D14"), "6 $", 6, "USD"))
        wheelItems.add(WheelItem(Color.parseColor("#141A1E"), "7 $", 7, "USD"))
        wheelItems.add(WheelItem(Color.parseColor("#040D14"), "8 $", 8, "USD"))
        wheelItems.add(WheelItem(Color.parseColor("#141A1E"), "9 $", 9, "USD"))
        wheelItems.add(WheelItem(Color.parseColor("#040D14"), "10 $", 10, "USD"))
        wheelItems.add(WheelItem(Color.parseColor("#141A1E"), "11 $", 11, "USD"))
        wheelItems.add(WheelItem(Color.parseColor("#040D14"), "12 $", 12, "USD"))
        wheelItems.add(WheelItem(Color.parseColor("#141A1E"), "13 $", 13, "USD"))
        wheelItems.add(WheelItem(Color.parseColor("#040D14"), "14 $", 14, "USD"))
        wheelItems.add(WheelItem(Color.parseColor("#141A1E"), "15 $", 15, "USD"))
        wheelItems.add(WheelItem(Color.parseColor("#040D14"), "16 $", 16, "USD"))
        wheelItems.add(WheelItem(Color.parseColor("#141A1E"), "17 $", 17, "USD"))
        wheelItems.add(WheelItem(Color.parseColor("#040D14"), "18 $", 18, "USD"))
        wheelItems.add(WheelItem(Color.parseColor("#141A1E"), "19 $", 19, "USD"))
        wheelItems.add(WheelItem(Color.parseColor("#040D14"), "20 $", 20, "USD"))
    }
}
```
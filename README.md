# MWheel Android


[![Kotlin Version](https://img.shields.io/badge/Kotlin-1.4.30-blue.svg)](https://kotlinlang.org)
[![Platform](https://img.shields.io/badge/Platform-Android-green.svg?style=flat)](https://www.android.com/)
[![API](https://img.shields.io/badge/API-16%2B-brightgreen.svg?style=flat)](https://android-arsenal.com/api?level=16#l16)

## SpinWheel

![alt text](https://github.com/MehulKK/SpinWheel/blob/master/samples/updated.gif)



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
	implementation 'com.github.MehulKK:SpinWheel:1.0.28'
}
```

# Configuration

## All Attributes
------------------------

| Attribute | Description | Default |
| --- | --- | --- |
| `app:background_color` | Choose the background color | `Color.parseColor('#141A1E')` |
| `app:arrow_image` | Choose wheel image arrow | `R.drawablw.ic_group_1459` |

## Usage
------------------------
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

## Category Component

![alt text](https://github.com/MehulKK/SpinWheel/blob/master/samples/category.gif)

# Configuration

## All Attributes
------------------------

| Attribute | Description | Default |
| --- | --- | --- |
| `app:showFooter` | Show footer or not | `false` |

## Usage
------------------------
```
<com.macv.category.CategoryComponent
            android:id="@+id/category"
            android:layout_width="match_parent"
            tools:listitem="@layout/list_item"
            app:showFooter="true"
            app:layoutManager="androidx.recyclerview.widget.LinearLayoutManager"
            android:layout_height="wrap_content" />
```

## Code snippet

```
category.setDynamicList(getCategoryList())      // set category list items

// Footer close callback
category.setFooterCloseListener(object : FooterDismissListener{
    override fun onClose() {
        Toast.makeText(this@CategoryComponentActivity, "Footer close", Toast.LENGTH_LONG).show()
    }
})

private fun getCategoryList(): ArrayList<CategoryItem> {
    val list = ArrayList<CategoryItem>()
    list.add(CategoryItem(id = 1, emoji = "https://dl.dropboxusercontent.com/s/grmetj2iixvz253/Group%201320.png", name = "Restaurant", avgPercent = "26%", yourPercent = "36%", monthlyOverSpendDollar = "76"))
    list.add(CategoryItem(id = 2, emoji = "https://dl.dropboxusercontent.com/s/rx8rh1uxhxrawx9/Group%201669.png", name = "Clothing", avgPercent = "27%", yourPercent = "37%", monthlyOverSpendDollar = "76"))
    list.add(CategoryItem(id = 3, emoji = "https://dl.dropboxusercontent.com/s/igoyutvhcf5hgdr/Group%201670.png", name = "Subscription", avgPercent = "26%", yourPercent = "38%", monthlyOverSpendDollar = "76"))
    return list
}
```

## Achievement Component

![alt text](https://github.com/MehulKK/SpinWheel/blob/master/samples/achievement.gif)

# Configuration

## Usage
------------------------
```
<com.macv.achivement.AchievementComponent
    android:id="@+id/achievement"
    android:layout_width="match_parent"
    tools:listitem="@layout/achievement_list_item"
    app:layoutManager="androidx.recyclerview.widget.LinearLayoutManager"
    android:layout_height="wrap_content" />
```

## Code snippet

```
achievement.setDynamicList(getAchievementList())      // set achievement list items
achievement.setMaxValueListener(object : AchievementMaxListener{    // Achievement max click callback
    override fun onClick(achievementItem: AchievementItem) {
        Toast.makeText(this@AchievementComponentActivity, achievementItem.action+" Item Click", Toast.LENGTH_LONG).show()
    }
})


private fun getAchievementList(): ArrayList<AchievementItem> {
        val list = ArrayList<AchievementItem>()
        list.add(AchievementItem(id = 1, image = null, emoji = "\uD83D\uDCFA", points = 100,
            action = "Watch 30 ads + \uD83C\uDFC6 100 ", max = 40, value = 15, outlineColor = "#FF0000", barColor = "#FDAEB6", backgroundColor = "#2A3137", textColor = "#FFFFFF"))
        list.add(AchievementItem(id = 2, image = R.drawable.ic_close, emoji = "\uD83D\uDCFA", points = 200,
            action = "Scan 20 Reciepts + \uD83C\uDFC6 200 ", max = 30, value = 30, outlineColor = "#00FF00", barColor = "#FDAEB6", backgroundColor = "", textColor = "#FFFFFF"))
        return list
}
```

## Countdown Component

![alt text](https://github.com/MehulKK/SpinWheel/blob/countdown_compoenent/samples/countdowncomponent.png)

# Configuration

## Usage
------------------------
```
<com.macv.countdown.CountdownView
        android:id="@+id/countDown"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_gravity="center_horizontal"
        android:layout_marginTop="20dp"
        app:isTimeTextBold="true"
        app:suffixGravity="bottom"
        app:isShowDay="false"
        app:isShowHour="false"
        app:suffixTextColor="#000000"
        app:suffixTextSize="12sp"
        app:subTextColor="#00000"
        app:timeTextColor="#000000"
        app:timeTextSize="22sp"/>
```

## Code snippet

```
        val time4 = 2.toLong() * 24 * 60 * 60 * 1000
        countDown.start(time4)      //mTimeStamp
        countDown.setMaxUnit("DAY")     //MaxUnit

        // Finish Callback
        countDown.setOnCountdownEndListener(object : CountdownView.OnCountdownEndListener {
            override fun onEnd(cv: CountdownView?) {
                Toast.makeText(this@CountDownComponentActivity, "Finish", Toast.LENGTH_LONG);
            }
        })
```


## SweepStakes Component

![alt text](https://github.com/MehulKK/SpinWheel/blob/countdown_compoenent/samples/sweepsakescomponent.png)

# Configuration

## Usage
------------------------
```
<com.macv.sweepstakes.SweepStakesCard
        android:id="@+id/sweepStakesCard"
        app:title="Hello"
        app:description="Good Morning"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"/>
```

## Code snippet

```
        val sweepStakesItem = SweepStakesItem(id = 1, title = "Enter to Win \$1000",
                                    description = "One \$500 winner · Five \$100 winners.",
                                    pointsPerEntry = 50, entires = 5, timestamp = (2 * 60 * 60 * 1000), maxUnit = "HOUR")
        sweepStakesCard.setSweepStakesItem(sweepStakesItem)
```

## Spin the Wheel Card Component

![alt text](https://github.com/MehulKK/SpinWheel/blob/countdown_compoenent/samples/spin_the_card_component.png)

# Configuration

## Usage
------------------------
```
<com.macv.spinwheel.SpinWheelCardComponent
    android:id="@+id/spinWheelCard"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"/>
```

## Code snippet

```
        val spinWheelCardComponentItem = SpinWheelCardComponentItem(id = 1, maxRewardPoints = 50,pointsPerSpin = 500)
                spinWheelCard.setSpinWheelCardItem(spinWheelCardComponentItem)
                spinWheelCard.setCallBack(object : SpinWheelCardCallBack{
                    override fun onSpinWheelCardClick() {
                        Toast.makeText(this@SpinWheelCardComponentActivity, "Item Click", Toast.LENGTH_LONG).show()
                    }
                })
```

## Points Item Card Component

![alt text](https://github.com/MehulKK/SpinWheel/blob/countdown_compoenent/samples/points_card_horizontal.png)

## All Attributes
------------------------

| Attribute | Description | Default |
| --- | --- | --- |
| `app:layoutOrientation` | Choose Orientation | `HORIZONTAL` |


# Configuration

## Usage
------------------------
```
<com.macv.pointsitemcard.PointsItemCardComponent
    android:id="@+id/pointItemCard"
    android:layout_width="match_parent"
    tools:listitem="@layout/item_points_vertical"
    app:layoutOrientation="VERTICAL"
    app:layoutManager="androidx.recyclerview.widget.LinearLayoutManager"
    android:layout_height="wrap_content" />
```

## Code snippet

```
        pointItemCard.setDynamicList(getPointsItemList(), object : PointItemCallback {      // set Points List and get callback
            override fun onPointItemClick(mPosition: Int, pointsItem: PointsItem) {
                Toast.makeText(this@PointItemCardComponentActivity, pointsItem.title, Toast.LENGTH_SHORT).show()
            }
        })
```

## Todo Card Component

![alt text](https://github.com/MehulKK/SpinWheel/blob/countdown_compoenent/samples/todo_card.png)

# Configuration

## Usage
------------------------
```
<com.macv.todocard.ToDoCardComponent
    android:id="@+id/toDoCard"
    android:layout_width="match_parent"
    android:layout_height="wrap_content" />
```

## Code snippet

```
        toDoCard.setDynamicList(getPointsItemList(), object : ToDoCallback {        // set ToDo List and get callback
            override fun onToDoClick(mPos: Int, toDoCardItem: ToDoCardItem) {
                Toast.makeText(this@ToDoCardComponentActivity, toDoCardItem.name, Toast.LENGTH_SHORT).show()
            }
        })
```
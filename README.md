# DayView <img src="https://raw.githubusercontent.com/migellal/DayView/master/app/src/main/res/drawable-mdpi/ic_calendar_blank_black_48dp.png" width="24">

This library shows the specific day in a calendar board view.

## Screenshoot - only library

<img src="https://raw.githubusercontent.com/migellal/DayView/master/app/src/main/res/raw/only_lib.png">

## Screenshots - from demo

<img src="https://raw.githubusercontent.com/migellal/DayView/master/app/src/main/res/raw/horizontal.png" width="600"> <img src="https://raw.githubusercontent.com/migellal/DayView/master/app/src/main/res/raw/vertical.png" width="190">

## Features

This simple Kotlin library can show specific date in calendar board view, in the correct format, according to user's language settings. The view is flexible, that means it can be fat or thin - controlled easily through API.

## API

| XML                 | Java                     | Kotlin              | Info                                                 |
| :------------------:|:------------------------:|:-------------------:|:----------------------------------------------------:|
| barColor            | setBarColor()            | barColor            | most important, default primaryColor                 |
| borderColor         | setBorderColor()         | borderColor         | almost invisible, 1px width                          |
| cardBackgroundColor | setCardBackgroundColor() | cardBackgroundColor | background inside board, default white               |
| textColor           | setTextColor()           | textColor           | default black                                        |
|                     | setDate()                | date                | should show month in user lang, default current date |

## Examples

#### Java

        DayView dayView = (DayView) findViewById(R.id.dayView);
        dayView.setBarColor(ContextCompat.getColor(this, R.color.colorPrimary));
        dayView.setBorderColor(ContextCompat.getColor(this, R.color.colorPrimary));
        dayView.setCardBackgroundColor(Color.WHITE);
        dayView.setTextColor(Color.BLACK);
        dayView.setDate(new Date(2017, 10, 15));
        
#### Kotlin

            val dayView = findViewById(R.id.dayView) as DayView
            dayView.barColor = ContextCompat.getColor(this, R.color.colorPrimary)
            dayView.borderColor = ContextCompat.getColor(this, R.color.colorPrimary)
            dayView.cardBackgroundColor = Color.WHITE
            dayView.textColor = Color.BLACK
            dayView.date = Date(year, month, day)
            
#### XML

    <pl.digitalzombielab.dayview.DayView
        android:id="@+id/dayView"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        app:barColor="@color/colorPrimary"
        app:borderColor="@color/colorPrimary"
        app:cardBackgroundColor="@android:color/white"
        app:textColor="@android:color/black" />

## Download

You can download [demo from PlayStore](https://play.google.com/store/apps/details?id=pl.digitalzombielab.dayviewdemo), download demo.apk [directly from repo](https://github.com/migellal/DayView/blob/master/app/app-release.apk), and of course use library:

**_compile 'com.github.migellal:dayview:0.0.1'_**


License
--------

    Copyright 2017 migellal, Inc.

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.

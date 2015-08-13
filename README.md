# AutoLoadListView
An Android customed ListView that could auto load more data
自定义ListView，可以自动加载更多。
## Usage

### Step 1

Use the customed view in xml layout file like that

```xml
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".MainActivity">

    <android.support.v4.widget.SwipeRefreshLayout
        android:id="@+id/swipe"
        android:layout_width="match_parent"
        android:layout_height="match_parent">

        <com.lance.autoloadlistview.AutoLoadListView
            android:id="@+id/lv_data"
            android:layout_width="match_parent"
            android:layout_height="match_parent" />

    </android.support.v4.widget.SwipeRefreshLayout>

</RelativeLayout>
```

### Step 2

Find view and set listener for load more.The Activity or the Fragment should implements AutoLoadListView.IonLoadMore.

```Java
 AutoLoadListView mLvData = (AutoLoadListView) findViewById(R.id.lv_data);
 mLvData.setOnLoadmoreListener(this,mContext);
```

### Step 3

Set the adapter.When the AutoLoadListView scroll to the bottom,it would call the method ```onLoadMore();```

```Java
 mAdapter = new ArrayAdapter(mContext,android.R.layout.simple_list_item_1,android.R.id.text1,data);
 mLvData.setAdapter(mAdapter);
```

![sina](http://ww3.sinaimg.cn/mw690/8f8f5f45gw1ev143m98uhj20k00zkwfh.jpg "示例1")
![sina](http://ww2.sinaimg.cn/mw690/8f8f5f45gw1ev143mqamnj20k00zkt9n.jpg "示例2")
![sina](http://ww2.sinaimg.cn/mw690/8f8f5f45gw1ev143naedsj20k00zkt9n.jpg "示例3")

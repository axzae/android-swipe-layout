package com.daimajia.swipedemo

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AbsListView
import android.widget.AdapterView
import android.widget.AdapterView.OnItemLongClickListener
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ListView
import android.widget.Toast
import com.daimajia.swipe.SwipeLayout
import com.daimajia.swipe.util.Attributes
import com.daimajia.swipedemo.adapter.ArraySwipeAdapterSample
import com.daimajia.swipedemo.adapter.ListViewAdapter

class ListViewExample : Activity() {
    private var mListView: ListView? = null
    private var mAdapter: ListViewAdapter? = null
    private val mContext: Context = this
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.listview)
        mListView = findViewById<View>(R.id.listview) as ListView
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            val actionBar = actionBar
            if (actionBar != null) {
                actionBar.title = "ListView"
            }
        }
        /**
         * The following comment is the sample usage of ArraySwipeAdapter.
         */
//        val adapterData = arrayOf(
//            "Activity", "Service", "Content Provider", "Intent", "BroadcastReceiver", "ADT", "Sqlite3", "HttpClient",
//            "DDMS", "Android Studio", "Fragment", "Loader", "Activity", "Service", "Content Provider", "Intent",
//            "BroadcastReceiver", "ADT", "Sqlite3", "HttpClient", "Activity", "Service", "Content Provider", "Intent",
//            "BroadcastReceiver", "ADT", "Sqlite3", "HttpClient"
//        )
//        mListView.setAdapter(ArraySwipeAdapterSample<String>(this, R.layout.listview_item, R.id.position, adapterData))

        mAdapter = ListViewAdapter(this)
        mListView!!.adapter = mAdapter
        mAdapter!!.mode = Attributes.Mode.Single
        mListView!!.onItemClickListener =
            AdapterView.OnItemClickListener { parent, view, position, id -> (mListView!!.getChildAt(position - mListView!!.firstVisiblePosition) as SwipeLayout).open(true) }
        mListView!!.setOnTouchListener { v, event ->
            Log.e("ListView", "OnTouch")
            false
        }
        mListView!!.onItemLongClickListener = OnItemLongClickListener { parent, view, position, id ->
            Toast.makeText(mContext, "OnItemLongClickListener", Toast.LENGTH_SHORT).show()
            true
        }
        mListView!!.setOnScrollListener(
            object : AbsListView.OnScrollListener {
                override fun onScrollStateChanged(view: AbsListView, scrollState: Int) {
                    Log.e("ListView", "onScrollStateChanged")
                }

                override fun onScroll(view: AbsListView, firstVisibleItem: Int, visibleItemCount: Int, totalItemCount: Int) = Unit
            },
        )
        mListView!!.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View, position: Int, id: Long) {
                Log.e("ListView", "onItemSelected:$position")
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                Log.e("ListView", "onNothingSelected:")
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.my, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId
        if (id == R.id.action_listview) {
            startActivity(Intent(this, ListViewExample::class.java))
            finish()
            return true
        } else if (id == R.id.action_gridview) {
            startActivity(Intent(this, GridViewExample::class.java))
            finish()
            return true
        } else if (id == R.id.action_recycler) {
            startActivity(Intent(this, RecyclerViewExample::class.java))
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}

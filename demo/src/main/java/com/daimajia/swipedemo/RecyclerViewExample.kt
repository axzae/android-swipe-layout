package com.daimajia.swipedemo

import android.app.Activity
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.daimajia.swipe.util.Attributes
import com.daimajia.swipedemo.adapter.RecyclerViewAdapter
import com.daimajia.swipedemo.adapter.util.DividerItemDecoration
import jp.wasabeef.recyclerview.animators.FadeInLeftAnimator
import java.util.Arrays

class RecyclerViewExample : Activity() {
    /**
     * RecyclerView: The new recycler view replaces the list view. Its more modular and therefore we
     * must implement some of the functionality ourselves and attach it to our recyclerview.
     *
     *
     * 1) Position items on the screen: This is done with LayoutManagers
     * 2) Animate & Decorate views: This is done with ItemAnimators & ItemDecorators
     * 3) Handle any touch events apart from scrolling: This is now done in our adapter's ViewHolder
     */
    private var recyclerView: RecyclerView? = null
    private var mAdapter: RecyclerView.Adapter<*>? = null
    private var mDataSet: ArrayList<String>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.recyclerview)
        recyclerView = findViewById<View>(R.id.recycler_view) as RecyclerView
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            val actionBar = actionBar
            if (actionBar != null) {
                actionBar.title = "RecyclerView"
            }
        }

        // Layout Managers:
        recyclerView!!.layoutManager = LinearLayoutManager(this)

        // Item Decorator:
        recyclerView!!.addItemDecoration(DividerItemDecoration(resources.getDrawable(R.drawable.divider)))
        recyclerView!!.itemAnimator = FadeInLeftAnimator()

        // Adapter:
        val adapterData = arrayOf(
            "Alabama",
            "Alaska",
            "Arizona",
            "Arkansas",
            "California",
            "Colorado",
            "Connecticut",
            "Delaware",
            "Florida",
            "Georgia",
            "Hawaii",
            "Idaho",
            "Illinois",
            "Indiana",
            "Iowa",
            "Kansas",
            "Kentucky",
            "Louisiana",
            "Maine",
            "Maryland",
            "Massachusetts",
            "Michigan",
            "Minnesota",
            "Mississippi",
            "Missouri",
            "Montana",
            "Nebraska",
            "Nevada",
            "New Hampshire",
            "New Jersey",
            "New Mexico",
            "New York",
            "North Carolina",
            "North Dakota",
            "Ohio",
            "Oklahoma",
            "Oregon",
            "Pennsylvania",
            "Rhode Island",
            "South Carolina",
            "South Dakota",
            "Tennessee",
            "Texas",
            "Utah",
            "Vermont",
            "Virginia",
            "Washington",
            "West Virginia",
            "Wisconsin",
            "Wyoming",
        )
        mDataSet = ArrayList(Arrays.asList(*adapterData))
        mAdapter = RecyclerViewAdapter(this, mDataSet!!)
        (mAdapter as RecyclerViewAdapter).mode = Attributes.Mode.Single
        recyclerView!!.adapter = mAdapter

        /* Listeners */recyclerView!!.setOnScrollListener(onScrollListener)
    }

    /**
     * Substitute for our onScrollListener for RecyclerView
     */
    var onScrollListener: RecyclerView.OnScrollListener = object : RecyclerView.OnScrollListener() {
        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
            Log.e("ListView", "onScrollStateChanged")
        }

        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            // Could hide open views here if you wanted. //
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
        }
        return super.onOptionsItemSelected(item)
    }
}

package com.daimajia.swipedemo

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemLongClickListener
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.GridView
import com.daimajia.swipe.util.Attributes
import com.daimajia.swipedemo.adapter.GridViewAdapter

class GridViewExample : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.gridview)
        val gridView = findViewById<View>(R.id.gridview) as GridView
        val adapter = GridViewAdapter(this)
        adapter.mode = Attributes.Mode.Multiple
        gridView.adapter = adapter
        gridView.isSelected = false
        gridView.onItemLongClickListener = OnItemLongClickListener { parent, view, position, id ->
            Log.e("onItemLongClick", "onItemLongClick:$position")
            false
        }
        gridView.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id -> Log.e("onItemClick", "onItemClick:$position") }
        gridView.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View, position: Int, id: Long) {
                Log.e("onItemSelected", "onItemSelected:$position")
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
    }
}

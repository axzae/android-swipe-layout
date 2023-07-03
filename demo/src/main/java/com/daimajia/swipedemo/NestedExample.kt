package com.daimajia.swipedemo

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.daimajia.swipe.SwipeLayout

class NestedExample : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.complicate_layout)
        val swipeLayout = findViewById<View>(R.id.test_swipe_swipe) as SwipeLayout
        swipeLayout.setOnDoubleClickListener { layout, surface -> Toast.makeText(applicationContext, "DoubleClick", Toast.LENGTH_SHORT).show() }
        swipeLayout.findViewById<View>(R.id.trash).setOnClickListener { Toast.makeText(applicationContext, "Click", Toast.LENGTH_SHORT).show() }
    }
}

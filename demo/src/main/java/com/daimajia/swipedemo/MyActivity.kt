package com.daimajia.swipedemo

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.daimajia.swipe.SwipeLayout
import com.daimajia.swipe.SwipeLayout.DragEdge
import com.nineoldandroids.view.ViewHelper

class MyActivity : Activity() {
    private var sample1: SwipeLayout? = null
    private var sample2: SwipeLayout? = null
    private var sample3: SwipeLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main)

//        SwipeLayout swipeLayout = (SwipeLayout)findViewById(R.id.godfather)
//        swipeLayout.setDragEdge(SwipeLayout.DragEdge.Bottom); // Set in XML

        // sample1
        sample1 = findViewById<View>(R.id.sample1) as SwipeLayout
        sample1!!.showMode = SwipeLayout.ShowMode.PullOut
        val starBottView = sample1!!.findViewById<View>(R.id.starbott)
        sample1!!.addDrag(DragEdge.Left, sample1!!.findViewById(R.id.bottom_wrapper))
        sample1!!.addDrag(DragEdge.Right, sample1!!.findViewById(R.id.bottom_wrapper_2))
        sample1!!.addDrag(DragEdge.Top, starBottView)
        sample1!!.addDrag(DragEdge.Bottom, starBottView)
        sample1!!.addRevealListener(R.id.delete) { child, edge, fraction, distance -> }
        sample1!!.surfaceView.setOnClickListener {
            Toast.makeText(this@MyActivity, "Click on surface", Toast.LENGTH_SHORT).show()
            Log.d(MyActivity::class.java.name, "click on surface")
        }
        sample1!!.surfaceView.setOnLongClickListener {
            Toast.makeText(this@MyActivity, "longClick on surface", Toast.LENGTH_SHORT).show()
            Log.d(MyActivity::class.java.name, "longClick on surface")
            true
        }
        sample1!!.findViewById<View>(R.id.star2).setOnClickListener { Toast.makeText(this@MyActivity, "Star", Toast.LENGTH_SHORT).show() }
        sample1!!.findViewById<View>(R.id.trash2).setOnClickListener { Toast.makeText(this@MyActivity, "Trash Bin", Toast.LENGTH_SHORT).show() }
        sample1!!.findViewById<View>(R.id.magnifier2).setOnClickListener { Toast.makeText(this@MyActivity, "Magnifier", Toast.LENGTH_SHORT).show() }
        sample1!!.addRevealListener(R.id.starbott) { child, edge, fraction, distance ->
            val star = child.findViewById<View>(R.id.star)
            val d = (child.height / 2 - star.height / 2).toFloat()
            ViewHelper.setTranslationY(star, d * fraction)
            ViewHelper.setScaleX(star, fraction + 0.6f)
            ViewHelper.setScaleY(star, fraction + 0.6f)
        }

        // sample2
        sample2 = findViewById<View>(R.id.sample2) as SwipeLayout
        sample2!!.showMode = SwipeLayout.ShowMode.LayDown
        sample2!!.addDrag(DragEdge.Right, sample2!!.findViewWithTag("Bottom2"))
        //        sample2.setShowMode(SwipeLayout.ShowMode.PullOut);
        sample2!!.findViewById<View>(R.id.star).setOnClickListener { Toast.makeText(this@MyActivity, "Star", Toast.LENGTH_SHORT).show() }
        sample2!!.findViewById<View>(R.id.trash).setOnClickListener { Toast.makeText(this@MyActivity, "Trash Bin", Toast.LENGTH_SHORT).show() }
        sample2!!.findViewById<View>(R.id.magnifier).setOnClickListener { Toast.makeText(this@MyActivity, "Magnifier", Toast.LENGTH_SHORT).show() }
        sample2!!.findViewById<View>(R.id.click).setOnClickListener { Toast.makeText(this@MyActivity, "Yo", Toast.LENGTH_SHORT).show() }
        sample2!!.surfaceView.setOnClickListener { Toast.makeText(this@MyActivity, "Click on surface", Toast.LENGTH_SHORT).show() }

        // sample3
        sample3 = findViewById<View>(R.id.sample3) as SwipeLayout
        sample3!!.addDrag(DragEdge.Top, sample3!!.findViewWithTag("Bottom3"))
        sample3!!.addRevealListener(R.id.bottom_wrapper_child1) { child, edge, fraction, distance ->
            val star = child.findViewById<View>(R.id.star)
            val d = (child.height / 2 - star.height / 2).toFloat()
            ViewHelper.setTranslationY(star, d * fraction)
            ViewHelper.setScaleX(star, fraction + 0.6f)
            ViewHelper.setScaleY(star, fraction + 0.6f)
            val c = evaluate(fraction, Color.parseColor("#dddddd"), Color.parseColor("#4C535B")) as Int
            child.setBackgroundColor(c)
        }
        sample3!!.findViewById<View>(R.id.bottom_wrapper_child1).setOnClickListener { Toast.makeText(this@MyActivity, "Yo!", Toast.LENGTH_SHORT).show() }
        sample3!!.surfaceView.setOnClickListener { Toast.makeText(this@MyActivity, "Click on surface", Toast.LENGTH_SHORT).show() }
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
            return true
        } else if (id == R.id.action_gridview) {
            startActivity(Intent(this, GridViewExample::class.java))
            return true
        } else if (id == R.id.action_nested) {
            startActivity(Intent(this, NestedExample::class.java))
            return true
        } else if (id == R.id.action_recycler) {
            startActivity(Intent(this, RecyclerViewExample::class.java))
        }
        return super.onOptionsItemSelected(item)
    }

    /*
    Color transition method.
     */
    fun evaluate(fraction: Float, startValue: Any, endValue: Any): Any {
        val startInt = startValue as Int
        val startA = startInt shr 24 and 0xff
        val startR = startInt shr 16 and 0xff
        val startG = startInt shr 8 and 0xff
        val startB = startInt and 0xff
        val endInt = endValue as Int
        val endA = endInt shr 24 and 0xff
        val endR = endInt shr 16 and 0xff
        val endG = endInt shr 8 and 0xff
        val endB = endInt and 0xff
        return (startA + (fraction * (endA - startA)).toInt() shl 24) or
            (startR + (fraction * (endR - startR)).toInt() shl 16) or
            (startG + (fraction * (endG - startG)).toInt() shl 8) or
            (startB + (fraction * (endB - startB)).toInt())
    }
}

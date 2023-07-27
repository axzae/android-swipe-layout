package com.daimajia.swipedemo

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.daimajia.swipe.SwipeLayout
import com.daimajia.swipe.SwipeLayout.DragEdge
import com.daimajia.swipedemo.databinding.ActivityMainBinding
import com.nineoldandroids.view.ViewHelper

class MyActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        binding.swipeRefreshLayout.setOnRefreshListener {
            binding.swipeRefreshLayout.isRefreshing = false
        }

        // Sample 1
        binding.sample1.layoutSwipe.showMode = SwipeLayout.ShowMode.PullOut
        val starBottView = binding.sample1.layoutStarbott
        binding.sample1.layoutSwipe.addDrag(DragEdge.Left, binding.sample1.bottomWrapper)
        binding.sample1.layoutSwipe.addDrag(DragEdge.Right, binding.sample1.bottomWrapper2)
        binding.sample1.layoutSwipe.addDrag(DragEdge.Top, starBottView)
        binding.sample1.layoutSwipe.addDrag(DragEdge.Bottom, starBottView)
        binding.sample1.layoutSwipe.addRevealListener(R.id.text_delete) { _, _, fraction, distance -> }
        binding.sample1.layoutSwipe.surfaceView.setOnClickListener {
            "Sample1: Click on surface".showToast()
        }
        binding.sample1.layoutSwipe.surfaceView.setOnLongClickListener {
            "Sample1: LongClick on surface".showToast()
            true
        }
        binding.sample1.imageStar2.setOnClickListener { "Sample1: Star".showToast() }
        binding.sample1.imageTrash2.setOnClickListener { "Sample1: Trash Bin".showToast() }
        binding.sample1.imageMagnifier2.setOnClickListener { "Sample1: Magnifier".showToast() }
        binding.sample1.layoutSwipe.addRevealListener(R.id.layout_starbott) { child, _, fraction, _ ->
            val star = child.findViewById<View>(R.id.image_star)
            val d = (child.height / 2 - star.height / 2).toFloat()
            ViewHelper.setTranslationY(star, d * fraction)
            ViewHelper.setScaleX(star, fraction + 0.6f)
            ViewHelper.setScaleY(star, fraction + 0.6f)
        }

        // Sample 2
        binding.sample2.layoutSwipe.showMode = SwipeLayout.ShowMode.LayDown
        binding.sample2.layoutSwipe.addDrag(DragEdge.Right, binding.sample2.layoutMenu)
        binding.sample2.imageStar.setOnClickListener { "Sample2: Star".showToast() }
        binding.sample2.imageTrash.setOnClickListener { "Sample2: Trash Bin".showToast() }
        binding.sample2.imageMagnifier.setOnClickListener { "Sample2: Magnifier".showToast() }
        binding.sample2.buttonClick.setOnClickListener { "Sample2: Yo".showToast() }
        binding.sample2.layoutSwipe.surfaceView.setOnClickListener { "Sample2: Click on surface".showToast() }

        // Sample 3
        binding.sample3.layoutSwipe.addDrag(DragEdge.Top, binding.sample3.layoutMenu)
        binding.sample3.layoutSwipe.addRevealListener(R.id.bottom_wrapper_child1) { child, _, fraction, _ ->
            val star = child.findViewById<View>(R.id.image_star)
            val d = (child.height / 2 - star.height / 2).toFloat()
            ViewHelper.setTranslationY(star, d * fraction)
            ViewHelper.setScaleX(star, fraction + 0.6f)
            ViewHelper.setScaleY(star, fraction + 0.6f)
            val c = evaluate(fraction, Color.parseColor("#DDDDDD"), Color.parseColor("#788494")) as Int
            child.setBackgroundColor(c)
        }
        binding.sample3.bottomWrapperChild1.setOnClickListener { "Sample3: Yo!".showToast() }
        binding.sample3.layoutSwipe.surfaceView.setOnClickListener { "Sample3: Click on surface".showToast() }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.my, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_listview -> {
                startActivity(Intent(this, ListViewExample::class.java))
                true
            }

            R.id.action_gridview -> {
                startActivity(Intent(this, GridViewExample::class.java))
                true
            }

            R.id.action_nested -> {
                startActivity(Intent(this, NestedExample::class.java))
                true
            }

            R.id.action_recycler -> {
                startActivity(Intent(this, RecyclerViewExample::class.java))
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    private var toast: Toast? = null
    private fun String.showToast(duration: Int = Toast.LENGTH_SHORT) {
        toast?.cancel()
        toast = Toast.makeText(this@MyActivity, this, duration).apply {
            show()
        }
        Log.d("YouQi", "Showing Toast: $this")
    }

    /**
     *  Color transition method.
     */
    private fun evaluate(fraction: Float, startValue: Any, endValue: Any): Any {
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

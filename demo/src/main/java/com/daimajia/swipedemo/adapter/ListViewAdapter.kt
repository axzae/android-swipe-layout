package com.daimajia.swipedemo.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.daimajia.androidanimations.library.Techniques
import com.daimajia.androidanimations.library.YoYo
import com.daimajia.swipe.SimpleSwipeListener
import com.daimajia.swipe.SwipeLayout
import com.daimajia.swipe.adapters.BaseSwipeAdapter
import com.daimajia.swipedemo.R

class ListViewAdapter(private val mContext: Context) : BaseSwipeAdapter() {
    override fun getSwipeLayoutResourceId(position: Int): Int {
        return R.id.swipe
    }

    override fun generateView(position: Int, parent: ViewGroup): View {
        val v = LayoutInflater.from(mContext).inflate(R.layout.listview_item, null)
        val swipeLayout = v.findViewById<View>(getSwipeLayoutResourceId(position)) as SwipeLayout
        swipeLayout.addSwipeListener(object : SimpleSwipeListener() {
            override fun onOpen(layout: SwipeLayout) {
                YoYo.with(Techniques.Tada).duration(500).delay(100).playOn(layout.findViewById(R.id.trash))
            }
        },)
        swipeLayout.setOnDoubleClickListener { layout, surface -> Toast.makeText(mContext, "DoubleClick", Toast.LENGTH_SHORT).show() }
        v.findViewById<View>(R.id.delete).setOnClickListener { Toast.makeText(mContext, "click delete", Toast.LENGTH_SHORT).show() }
        return v
    }

    override fun fillValues(position: Int, convertView: View) {
        val t = convertView.findViewById<View>(R.id.position) as TextView
        t.text = (position + 1).toString() + "."
    }

    override fun getCount(): Int {
        return 50
    }

    override fun getItem(position: Int): Any? {
        return null
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }
}

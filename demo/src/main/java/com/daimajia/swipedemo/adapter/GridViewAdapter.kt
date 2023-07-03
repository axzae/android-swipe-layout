package com.daimajia.swipedemo.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.daimajia.swipe.adapters.BaseSwipeAdapter
import com.daimajia.swipedemo.R

class GridViewAdapter(private val mContext: Context) : BaseSwipeAdapter() {
    override fun getSwipeLayoutResourceId(position: Int): Int {
        return R.id.swipe
    }

    override fun generateView(position: Int, parent: ViewGroup): View {
        return LayoutInflater.from(mContext).inflate(R.layout.grid_item, null)
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

package com.daimajia.swipedemo.adapter.util

import android.content.Context
import android.view.GestureDetector
import android.view.GestureDetector.SimpleOnGestureListener
import android.view.MotionEvent
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class RecyclerItemClickListener(context: Context?, private val mListener: OnItemClickListener?) : RecyclerView.OnItemTouchListener {
    interface OnItemClickListener {
        fun onItemClick(view: View?, position: Int)
    }

    var mGestureDetector: GestureDetector

    init {
        mGestureDetector = GestureDetector(
            context,
            object : SimpleOnGestureListener() {
                override fun onSingleTapUp(e: MotionEvent): Boolean {
                    return true
                }
            },
        )
    }

    override fun onInterceptTouchEvent(view: RecyclerView, e: MotionEvent): Boolean {
        val childView = view.findChildViewUnder(e.x, e.y)
        if (childView != null && mListener != null && mGestureDetector.onTouchEvent(e)) {
            mListener.onItemClick(childView, view.getChildPosition(childView))
        }
        return false
    }

    override fun onTouchEvent(view: RecyclerView, motionEvent: MotionEvent) {}
    override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {}
}

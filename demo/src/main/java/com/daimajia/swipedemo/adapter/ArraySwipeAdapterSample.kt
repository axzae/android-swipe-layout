package com.daimajia.swipedemo.adapter

import android.content.Context
import com.daimajia.swipe.adapters.ArraySwipeAdapter
import com.daimajia.swipedemo.R

/**
 * Sample usage of ArraySwipeAdapter.
 * @param <T>
</T> */
class ArraySwipeAdapterSample<T> : ArraySwipeAdapter<Any?> {
    constructor(context: Context, resource: Int) : super(context, resource)
    constructor(context: Context, resource: Int, textViewResourceId: Int) : super(context, resource, textViewResourceId)
    constructor(context: Context, resource: Int, objects: Array<Any?>) : super(context, resource, objects)
    constructor(context: Context, resource: Int, textViewResourceId: Int, objects: Array<Any?>) : super(context, resource, textViewResourceId, objects)
    constructor(context: Context, resource: Int, objects: List<*>) : super(context, resource, objects)
    constructor(context: Context, resource: Int, textViewResourceId: Int, objects: List<*>) : super(context, resource, textViewResourceId, objects)

    override fun getSwipeLayoutResourceId(position: Int): Int {
        return R.id.swipe
    }
}

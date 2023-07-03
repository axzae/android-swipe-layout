package com.daimajia.swipedemo.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView.ViewHolder
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.daimajia.androidanimations.library.Techniques
import com.daimajia.androidanimations.library.YoYo
import com.daimajia.swipe.SimpleSwipeListener
import com.daimajia.swipe.SwipeLayout
import com.daimajia.swipe.adapters.RecyclerSwipeAdapter
import com.daimajia.swipedemo.R
import com.daimajia.swipedemo.adapter.RecyclerViewAdapter.SimpleViewHolder

class RecyclerViewAdapter(
    private val mContext: Context,
    private val mDataset: ArrayList<String>,
) : RecyclerSwipeAdapter<SimpleViewHolder>() {
    class SimpleViewHolder(itemView: View) : ViewHolder(itemView) {
        var swipeLayout: SwipeLayout
        var textViewPos: TextView
        var textViewData: TextView
        var buttonDelete: Button

        init {
            swipeLayout = itemView.findViewById<View>(R.id.swipe) as SwipeLayout
            textViewPos = itemView.findViewById<View>(R.id.position) as TextView
            textViewData = itemView.findViewById<View>(R.id.text_data) as TextView
            buttonDelete = itemView.findViewById<View>(R.id.delete) as Button
            itemView.setOnClickListener(object : View.OnClickListener {
                override fun onClick(view: View) {
                    Log.d(javaClass.simpleName, "onItemSelected: " + textViewData.text.toString())
                    Toast.makeText(view.context, "onItemSelected: " + textViewData.text.toString(), Toast.LENGTH_SHORT).show()
                }
            },)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SimpleViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_item, parent, false)
        return SimpleViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: SimpleViewHolder, position: Int) {
        val item = mDataset[position]
        viewHolder.swipeLayout.showMode = SwipeLayout.ShowMode.LayDown
        viewHolder.swipeLayout.addSwipeListener(object : SimpleSwipeListener() {
            override fun onOpen(layout: SwipeLayout) {
                YoYo.with(Techniques.Tada).duration(500).delay(100).playOn(layout.findViewById(R.id.trash))
            }
        },)
        viewHolder.swipeLayout.setOnDoubleClickListener { layout, surface -> Toast.makeText(mContext, "DoubleClick", Toast.LENGTH_SHORT).show() }
        viewHolder.buttonDelete.setOnClickListener { view ->
            mItemManger.removeShownLayouts(viewHolder.swipeLayout)
            mDataset.removeAt(position)
            notifyItemRemoved(position)
            notifyItemRangeChanged(position, mDataset.size)
            mItemManger.closeAllItems()
            Toast.makeText(view.context, "Deleted " + viewHolder.textViewData.text.toString() + "!", Toast.LENGTH_SHORT).show()
        }
        viewHolder.textViewPos.text = (position + 1).toString() + "."
        viewHolder.textViewData.text = item
        mItemManger.bind(viewHolder.itemView, position)
    }

    override fun getItemCount(): Int {
        return mDataset.size
    }

    override fun getSwipeLayoutResourceId(position: Int): Int {
        return R.id.swipe
    }
}

package com.franco.convidados.view.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.franco.convidados.R
import com.franco.convidados.service.model.GuestModel
import com.franco.convidados.view.viewholder.GuestViewHolder

class GuestAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    private var mGuestList : List<GuestModel> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val item = LayoutInflater.from(parent.context).inflate(R.layout.row_guest, parent, false)
        return GuestViewHolder(item)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as GuestViewHolder).bind(mGuestList[position])
    }

    override fun getItemCount(): Int {
        return mGuestList.count()
    }
    @SuppressLint("NotifyDataSetChanged")
    fun updateGuests(list: List<GuestModel>) {
        mGuestList = list
        notifyDataSetChanged()
    }

}
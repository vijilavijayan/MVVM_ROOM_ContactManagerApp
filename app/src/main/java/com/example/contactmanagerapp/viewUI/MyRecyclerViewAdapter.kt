package com.example.contactmanagerapp.viewUI

import android.view.LayoutInflater
import android.view.View.OnClickListener
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.contactmanagerapp.R
import com.example.contactmanagerapp.databinding.CarditemBinding
import com.example.contactmanagerapp.room.User

/**
 * @author Vijila P
 */
class MyRecyclerViewAdapter (private val usersList: List<User>,private val clickListener: (User)->Unit):
    RecyclerView.Adapter<MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
       val layoutInflater = LayoutInflater.from(parent.context)
        val binding:CarditemBinding = DataBindingUtil.inflate(layoutInflater, R.layout.carditem,parent,false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return usersList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(usersList[position],clickListener)
    }
}
class MyViewHolder(val binding: CarditemBinding):RecyclerView.ViewHolder(binding.root){

    fun bind(user: User,clickListener: (User)->Unit){
        binding.textView.text = user.name
        binding.textView2.text = user.email

        binding.listItemLayout.setOnClickListener{
            clickListener(user)
        }
    }
}
package com.example.exchangeratesapp.RecyclerView

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.exchangeratesapp.databinding.FragmentOneElementBinding
import com.example.exchangeratesapp.model.JsonModel.Valute.myValGeneralValute

class RecAdapter(private val adapterList: List<myValGeneralValute>) :
    RecyclerView.Adapter<RecAdapter.MyViewHolder>() {
    class MyViewHolder(val binding: FragmentOneElementBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            FragmentOneElementBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.binding.nameID.text = adapterList[position].charCode
        holder.binding.nameRUID.text = adapterList[position].name
         holder.binding.courseID.text = adapterList[position].value
         holder.binding.courseChangeID.text =
             adapterList[position].previous
    }

    override fun getItemCount(): Int = adapterList.lastIndex

}
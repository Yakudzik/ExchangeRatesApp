package com.example.exchangeratesapp.RecyclerView

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
 import com.example.exchangeratesapp.databinding.FragmentOneConverterElementBinding
import com.example.exchangeratesapp.model.JsonModel.Valute.mySimpleValute

class ConverterRecAdapter(private val dataList: List<mySimpleValute>) :
    RecyclerView.Adapter<ConverterRecAdapter.MyViewHolder>() {
    class MyViewHolder(val binding: FragmentOneConverterElementBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            FragmentOneConverterElementBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.binding.nameID.text = dataList[position].charCode
        holder.binding.nameRUConverterElementID.text = dataList[position].name
        holder.binding.courseConverterElementID.text =
        if(dataList[position].newlValue.isEmpty())
            dataList[position].originalValue
        else dataList[position].newlValue
    }
    override fun getItemCount(): Int = dataList.size
}
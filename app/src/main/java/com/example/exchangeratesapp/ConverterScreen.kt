package com.example.exchangeratesapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Editable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.exchangeratesapp.RecyclerView.ConverterRecAdapter
import com.example.exchangeratesapp.RecyclerView.RecAdapter
import com.example.exchangeratesapp.databinding.FragmentConverterScreenBinding
import com.example.exchangeratesapp.databinding.FragmentGeneralScreenBinding
import com.example.exchangeratesapp.model.CurrencyViewModelClass
import com.example.exchangeratesapp.model.JsonModel

class ConverterScreen : Fragment() {
    private lateinit var binding: FragmentConverterScreenBinding
    private val model: CurrencyViewModelClass by activityViewModels()
    lateinit var adapter: ConverterRecAdapter


    @SuppressLint("NotifyDataSetChanged")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentConverterScreenBinding.inflate(inflater, container, false)


        return binding.root
    }
    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        model.convertedCurrencyList.observe(viewLifecycleOwner, { mySimpleValute ->
            adapter = ConverterRecAdapter(mySimpleValute)
            val recyclerView = binding.converterScreenRecyclerID
            recyclerView.adapter = adapter
            recyclerView.layoutManager = LinearLayoutManager(context)
            adapter.notifyDataSetChanged()
        })

        binding.calculateValuteButtonID.setOnClickListener {
            if (binding.inputValuteEdittextID.text.isNotEmpty()) {
                val e =
                    Editable.Factory.getInstance().newEditable(binding.inputValuteEdittextID.text)
                model.calculateCourse(e.toString().toDouble())
            } else
                Toast.makeText(
                    requireContext(),
                    "введите число для конвертации",
                    Toast.LENGTH_SHORT
                ).show()
            adapter.notifyDataSetChanged()
        }



    }
}
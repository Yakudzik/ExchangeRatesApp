package com.example.exchangeratesapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.exchangeratesapp.RecyclerView.RecAdapter
import com.example.exchangeratesapp.databinding.FragmentGeneralScreenBinding
import com.example.exchangeratesapp.model.CurrencyViewModelClass


class GeneralScreen : Fragment() {
    private lateinit var binding: FragmentGeneralScreenBinding
    private val model: CurrencyViewModelClass by activityViewModels()
    lateinit var adapter: RecAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentGeneralScreenBinding.inflate(inflater, container, false)

        return binding.root
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        model.testCurrencyListData.observe(viewLifecycleOwner, { myValGeneralValute ->
            adapter = RecAdapter(myValGeneralValute)
            val recyclerView = binding.generalScreenRecyclerID
            recyclerView.adapter = adapter
            recyclerView.layoutManager = LinearLayoutManager(context)
            adapter.notifyDataSetChanged()
        })
    }

}
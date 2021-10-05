package com.example.exchangeratesapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.exchangeratesapp.Retrofit.ApiResponse
import com.example.exchangeratesapp.databinding.ActivityMainBinding
import com.example.exchangeratesapp.model.CurrencyViewModelClass

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    lateinit var navController: NavController
    private val model: CurrencyViewModelClass by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        navController = findNavController(R.id.fragmentContainer4navGraph)

        binding.bottomNavigationMenu.setupWithNavController(navController)

        model.testAwakeData()
    }
}
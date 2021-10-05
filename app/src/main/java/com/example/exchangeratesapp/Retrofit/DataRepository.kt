package com.example.exchangeratesapp.Retrofit

import retrofit2.Retrofit

class DataRepository(private val retrofit: CourseApi) {
    fun getDateFromRepository() =retrofit.searchMainData()
}
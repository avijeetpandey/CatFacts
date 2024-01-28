package com.example.catfacts

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.catfacts.api.ApiClient
import com.example.catfacts.models.CatFacts
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    // function to fetch the facts from the internet
    private fun fetchCatFactsData() {
        val call = ApiClient.apiService.getAllCatFacts()

        call.enqueue(object : Callback<ArrayList<CatFacts>> {
            override fun onResponse(
                call: Call<ArrayList<CatFacts>>,
                response: Response<ArrayList<CatFacts>>
            ) {
                if(response.isSuccessful) {
                    val catFacts = response.body()
                } else {
                    Log.i("Oops","OOps")
                }
            }

            override fun onFailure(call: Call<ArrayList<CatFacts>>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }
}
package com.example.catfacts

import CatFactsAdapter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.catfacts.api.ApiClient
import com.example.catfacts.models.CatFacts
import com.example.catfacts.viewModel.CatFactsViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private var recyclerView: RecyclerView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById<RecyclerView>(R.id.recyclerView)

        recyclerView?.layoutManager = LinearLayoutManager(this)

        fetchCatFactsData()
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
                    val dataSet = ArrayList<CatFactsViewModel>()
                    catFacts?.forEach {
                        it?.text?.let {
                            dataSet.add(CatFactsViewModel(it))
                        }
                    }
                    val adapter = CatFactsAdapter(dataSet = dataSet)
                    recyclerView?.adapter = adapter
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
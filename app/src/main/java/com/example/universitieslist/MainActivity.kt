package com.example.universitieslist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

open class MainActivity : AppCompatActivity(),LinkClicked {
    private lateinit var universityAdapter:UniversityListAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var retrofit:Retrofit
    private val handler = Handler()
    private val refreshIntervalMillis: Long = 10000
    private val shouldRefreshData = true
    private lateinit var dataRefreshRunnable:Runnable
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.clg_list)
        recyclerView.layoutManager = LinearLayoutManager(this)
        universityAdapter = UniversityListAdapter(emptyList(), this,this)
        recyclerView.adapter = universityAdapter



         dataRefreshRunnable = object : Runnable {
            override fun run() {
                fetchDataFromApi()

                handler.postDelayed(this, refreshIntervalMillis)

            }
        }




    }



    private fun startDataRefresh() {
        if (shouldRefreshData) {
            handler.post(dataRefreshRunnable)
        }

    }

    private fun stopDataRefresh() {
        handler.removeCallbacks(dataRefreshRunnable)
    }

    private fun fetchDataFromApi() {
        retrofit = Retrofit.Builder()
            .baseUrl("http://universities.hipolabs.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()


        val apiService = retrofit.create(UniversityApiInterface::class.java)

        // API REQUEST

        apiService.getUniversities().enqueue(object :Callback<List<University>>{
            override fun onResponse(
                call: Call<List<University>>,
                response: Response<List<University>>
            ) {
                if (response.isSuccessful){
                    val universities = response.body()

                    if (universities !=null){
                        universityAdapter = UniversityListAdapter(universities, this@MainActivity,this@MainActivity)
                        recyclerView.adapter = universityAdapter
                        Toast.makeText(this@MainActivity,"Data is refresh",Toast.LENGTH_SHORT).show()

                    }
                }
            }

            override fun onFailure(call: Call<List<University>>, t: Throwable) {
                Log.e("API Error", "Failed to fetch data: ${t.message}")
                Toast.makeText(this@MainActivity,"Failed to fetch data: ${t.message}",Toast.LENGTH_SHORT).show()

            }

        })
    }

    override fun onResume() {
        super.onResume()
        startDataRefresh()
    }

    override fun onPause() {
        super.onPause()
        stopDataRefresh()
    }

    override fun onItemClicked(url: String) {
        stopDataRefresh()
        val intent = Intent(this,WebView::class.java)
        intent.putExtra("url",url)
        startActivity(intent)

    }


}
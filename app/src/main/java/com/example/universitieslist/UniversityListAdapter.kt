package com.example.universitieslist

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView



class UniversityListAdapter(private val universities:List<University>,private val context: Context,private val listener:LinkClicked): RecyclerView.Adapter<ListViewHolder>() {

    private lateinit var list:ArrayList<String>
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_colleges, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val currentItem = universities[position]
        holder.university.text = currentItem.name
        holder.country.text = currentItem.country
        list = ArrayList()
        list.clear()
        list = currentItem.web_pages as ArrayList<String>
        holder.link1.visibility = View.GONE
        holder.link2.visibility = View.GONE
        holder.link3.visibility = View.GONE
        holder.link4.visibility = View.GONE
        for (i in 0 until currentItem.web_pages!!.size) {
            if (i == 0) {
                holder.link1.visibility = View.VISIBLE
                holder.link1.text = list[i]
            }
            if (i == 1) {
                holder.link2.visibility = View.VISIBLE
                holder.link2.text = list[1]
            }
            if (i == 2) {
                holder.link3.visibility = View.VISIBLE
                holder.link3.text = list[2]
            }
            if (i==3){
                holder.link4.visibility = View.VISIBLE
                holder.link4.text = list[3]
            }
        }

        holder.link1.setOnClickListener {
           val websiteUrls = holder.link1.text.toString()
            listener.onItemClicked(websiteUrls)
//            if (websiteUrls.isNotEmpty()) {
//                openWebsiteInWebView(websiteUrls)
//            }
        }
        holder.link2.setOnClickListener {
            val websiteUrls = holder.link2.text.toString()
            listener.onItemClicked(websiteUrls)
//            if (websiteUrls.isNotEmpty()) {
//                openWebsiteInWebView(websiteUrls)
//            }
        }
        holder.link3.setOnClickListener {
            val websiteUrls = holder.link3.text.toString()
            listener.onItemClicked(websiteUrls)
//            if (websiteUrls.isNotEmpty()) {
//                openWebsiteInWebView(websiteUrls)
//            }
        }
        holder.link4.setOnClickListener {
            val websiteUrls = holder.link4.text.toString()
            listener.onItemClicked(websiteUrls)
//            if (websiteUrls.isNotEmpty()) {
//                openWebsiteInWebView(websiteUrls)
//            }
        }
    }




    private fun openWebsiteInWebView(websiteUrls: String) {
        val intent = Intent(context, WebView::class.java)
        intent.putExtra("url", websiteUrls)
        context.startActivity(intent)
    }

    override fun getItemCount(): Int {
        return universities.size
    }

}
class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val university: TextView = itemView.findViewById(R.id.university_name)
        val country: TextView = itemView.findViewById(R.id.country_name)
        val link1: TextView = itemView.findViewById(R.id.link1)
        val link2: TextView = itemView.findViewById(R.id.link2)
        val link3: TextView = itemView.findViewById(R.id.link3)
        val link4: TextView = itemView.findViewById(R.id.link4)
}

interface LinkClicked{
    fun onItemClicked(url:String)
}

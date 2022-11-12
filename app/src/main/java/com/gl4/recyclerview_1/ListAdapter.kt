package com.gl4.recyclerview_1

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.*
import kotlin.collections.ArrayList

class ListAdapter(private val data: ArrayList<Student>) :
    RecyclerView.Adapter<ListAdapter.ViewHolder>(), Filterable
{
    var dataFilterList : ArrayList<Student>
    init {
        dataFilterList = data
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image: ImageView
        val text: TextView
        init {
            image = itemView.findViewById(R.id.image)
            text = itemView.findViewById(R.id.text)
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.student_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.text.text = dataFilterList[position].nom + " " + dataFilterList[position].prenom
        if (dataFilterList[position].genre == "M") holder.image.setImageResource(R.drawable.man) else
            holder.image.setImageResource(R.drawable.woman)
    }
    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                dataFilterList = if (charSearch.isEmpty()) {
                    data
                } else {
                    var resultList = arrayListOf<Student>()
                    for (student in data) {
                        if ("${student.nom} ${student.prenom}".lowercase(Locale.ROOT)
                                .contains(charSearch.lowercase(Locale.ROOT))
                        ) {
                            resultList.add(student)
                        }
                    }
                    resultList
                }
                val filterResults = FilterResults()
                filterResults.values = dataFilterList
                return filterResults
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                dataFilterList = results?.values as ArrayList<Student>
                notifyDataSetChanged()
            }
        }
    }

    override fun getItemCount(): Int {
        return dataFilterList.size
    }
}


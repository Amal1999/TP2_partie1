package com.gl4.recyclerview_1

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    val spinner: Spinner by lazy { findViewById(R.id.spinner) }
    val recyclerView: RecyclerView by lazy { findViewById(R.id.recyclerView) }
    val editText: EditText by lazy { findViewById(R.id.edittext) }
    var matieres = listOf<String>("No selection", "Cours", "TP")
    var students = arrayListOf<Student>(
        Student("Sammari", "Amal", "F"),
        Student("Sammari", "Med1", "M"),
        Student("Achour", "Ines", "F"),
        Student("Sammari", "Med2", "M"),
        Student("Sammari", "Med3", "M"),
    )
    var tpStudents = arrayListOf<Student>(
        Student("Sammari", "Med1", "M"),
        Student("Achour", "Ines", "F"),
        Student("Sammari", "Med3", "M"),
    )
    var coursStudents = arrayListOf<Student>(
        Student("Sammari", "Amal", "F"),
        Student("Sammari", "Med2", "M"),
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val recyclerAdapter = ListAdapter(students)

        spinner.adapter =
            ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, matieres)

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                adapterView: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                if (position == 1)
                    students = coursStudents
                else if (position == 2)
                    students = tpStudents
            }

            override fun onNothingSelected(adapterView: AdapterView<*>?) {
            }
        }

        recyclerView.apply {
            this.adapter = recyclerAdapter
            this.layoutManager = LinearLayoutManager(this.context)
        }

        editText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                recyclerAdapter.filter.filter(s)
                Log.i("helloooo ", recyclerAdapter.dataFilterList.size.toString())
            }
        })


    }

}

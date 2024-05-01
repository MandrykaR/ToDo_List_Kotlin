package com.example.myapplication

import TaskAdapter
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    private lateinit var editText: EditText
    private lateinit var addButton: Button
    private lateinit var listView: ListView
    private lateinit var tasks: ArrayList<HashMap<String, String>>
    private lateinit var adapter: TaskAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editText = findViewById(R.id.user_data)
        addButton = findViewById(R.id.button)
        listView = findViewById(R.id.listView)

        tasks = ArrayList()
        adapter = TaskAdapter(this, tasks)
        listView.adapter = adapter

        addButton.setOnClickListener {
            val task = editText.text.toString()
            if (task.isNotEmpty()) {
                val map = HashMap<String, String>()
                map["task"] = task
                tasks.add(map)
                adapter.notifyDataSetChanged()
                editText.text.clear()
            }
        }

        listView.setOnItemLongClickListener { parent, view, position, id ->
            tasks.removeAt(position)
            adapter.notifyDataSetChanged()
            true
        }
    }
}

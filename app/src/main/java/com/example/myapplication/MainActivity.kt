package com.example.myapplication

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer

class MainActivity : AppCompatActivity() {

    private lateinit var editText: EditText
    private lateinit var addButton: Button
    private lateinit var listView: ListView
    private val taskViewModel: TaskViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editText = findViewById(R.id.user_data)
        addButton = findViewById(R.id.button)
        listView = findViewById(R.id.listView)

        // Создаем адаптер с пустым списком задач и функцией удаления
        val adapter = TaskAdapter(this, emptyList()) { task ->
            taskViewModel.removeTask(task)
        }
        listView.adapter = adapter

        // Наблюдаем за изменениями в списке задач и обновляем адаптер
        taskViewModel.tasks.observe(this, Observer { tasks ->
            adapter.setTasks(tasks)
        })

        addButton.setOnClickListener {
            val taskDescription = editText.text.toString()
            if (taskDescription.isNotEmpty()) {
                // Создаем новую задачу с уникальным идентификатором
                val task = Task(System.currentTimeMillis(), taskDescription)
                taskViewModel.addTask(task)
                editText.text.clear()
            }
        }
    }
}

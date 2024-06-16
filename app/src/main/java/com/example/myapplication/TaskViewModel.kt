package com.example.myapplication

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class TaskViewModel : ViewModel() {

    private val _tasks = MutableLiveData<List<Task>>()
    val tasks: LiveData<List<Task>> get() = _tasks

    init {
        _tasks.value = mutableListOf()
    }

    fun addTask(task: Task) {
        GlobalScope.launch(Dispatchers.Main) {
            val currentTasks = _tasks.value?.toMutableList() ?: mutableListOf()
            currentTasks.add(task)
            _tasks.value = currentTasks
        }
    }

    fun removeTask(task: Task) {
        GlobalScope.launch(Dispatchers.Main) {
            val currentTasks = _tasks.value?.toMutableList() ?: mutableListOf()
            currentTasks.remove(task)
            _tasks.value = currentTasks
        }
    }
}

package com.example.myapplication

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [28])  //  SDK
class TaskAdapterTest {
    private val context: Context = ApplicationProvider.getApplicationContext()

    @Test
    fun getCount_returnsCorrectCount() {
        val tasks = listOf(Task(1, "Task 1"), Task(2, "Task 2"))
        val adapter = TaskAdapter(context, tasks) {}

        assertEquals(2, adapter.count)
    }

    @Test
    fun getItem_returnsCorrectTask() {
        val tasks = listOf(Task(1, "Task 1"), Task(2, "Task 2"))
        val adapter = TaskAdapter(context, tasks) {}

        assertEquals(tasks[0], adapter.getItem(0))
        assertEquals(tasks[1], adapter.getItem(1))
    }

    @Test
    fun setTasks_updatesTaskList() {
        val initialTasks = listOf(Task(1, "Task 1"), Task(2, "Task 2"))
        val adapter = TaskAdapter(context, initialTasks) {}

        val newTasks = listOf(Task(3, "Task 3"), Task(4, "Task 4"))
        adapter.setTasks(newTasks)

        assertEquals(2, adapter.count)
        assertEquals(newTasks[0], adapter.getItem(0))
        assertEquals(newTasks[1], adapter.getItem(1))
    }
}

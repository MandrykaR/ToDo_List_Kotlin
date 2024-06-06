package com.example.myapplication

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class TaskViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private lateinit var viewModel: TaskViewModel

    @Before
    fun setup() {
        viewModel = TaskViewModel()
    }

    @Test
    fun `test adding task`() {
        val task = Task(1, "Test Task")
        viewModel.addTask(task)
        val tasks = viewModel.tasks.value
        assertEquals(1, tasks?.size)
        assertEquals(task, tasks?.get(0))
    }

    @Test
    fun `test removing task`() {
        val task = Task(1, "Test Task")
        viewModel.addTask(task)
        viewModel.removeTask(task)
        val tasks = viewModel.tasks.value
        assertEquals(0, tasks?.size)
    }
}

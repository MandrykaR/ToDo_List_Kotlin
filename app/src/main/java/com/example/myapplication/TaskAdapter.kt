package com.example.myapplication

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageButton
import android.widget.TextView

class TaskAdapter(
    private val context: Context,
    private var tasks: List<Task>,
    private val removeTask: (Task) -> Unit
) : BaseAdapter() {

    override fun getCount(): Int = tasks.size

    override fun getItem(position: Int): Any = tasks[position]

    override fun getItemId(position: Int): Long = position.toLong()

    fun setTasks(newTasks: List<Task>) {
        tasks = newTasks
        notifyDataSetChanged()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View
        val viewHolder: ViewHolder

        if (convertView == null) {
            view = LayoutInflater.from(context).inflate(R.layout.list_item_task, parent, false)
            viewHolder = ViewHolder(view)
            view.tag = viewHolder
        } else {
            view = convertView
            viewHolder = convertView.tag as ViewHolder
        }

        val task = getItem(position) as Task
        viewHolder.textView.text = task.description

        // Установка обработчика нажатия на кнопку удаления
        viewHolder.deleteButton.setOnClickListener {
            removeTask(task)
        }

        return view
    }

    private class ViewHolder(view: View) {
        val textView: TextView = view.findViewById(R.id.taskTextView)
        val deleteButton: ImageButton = view.findViewById(R.id.deleteButton)
    }
}

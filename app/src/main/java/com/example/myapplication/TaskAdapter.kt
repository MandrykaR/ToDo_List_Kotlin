import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageButton
import android.widget.TextView
import com.example.myapplication.R

class TaskAdapter(private val context: Context, private val tasks: ArrayList<HashMap<String, String>>) : BaseAdapter() {

    override fun getCount(): Int {
        return tasks.size
    }

    override fun getItem(position: Int): Any {
        return tasks[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var itemView = convertView
        if (itemView == null) {
            val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            itemView = inflater.inflate(R.layout.list_item_task, parent, false)
        }

        val taskTextView = itemView!!.findViewById<TextView>(R.id.taskTextView)
        val deleteButton = itemView.findViewById<ImageButton>(R.id.deleteButton)

        val task = tasks[position]

        taskTextView.text = task["task"]

        deleteButton.setOnClickListener {
            tasks.removeAt(position)
            notifyDataSetChanged()
        }

        return itemView
    }
}

package org.potados.todo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.todo_item.view.*

class TodoRecyclerAdapter : RecyclerView.Adapter<TodoRecyclerAdapter.TodoViewHolder>() {

    var items: List<TodoItem> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.todo_item, parent, false)

        return TodoViewHolder(view)
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val item = items[position]

        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class TodoViewHolder(private val view: View): RecyclerView.ViewHolder(view) {

        fun bind(item: TodoItem) {
            view.thing_to_do.text = item.thingToDo
            view.is_done.isChecked = item.isDone
        }
    }
}
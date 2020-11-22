package org.potados.todo

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.todo_item.view.*

class TodoRecyclerAdapter : RecyclerView.Adapter<TodoRecyclerAdapter.TodoViewHolder>() {

    var items: List<TodoItem> = listOf()
        set(value) {
            val wasEmpty = field.isEmpty()
            field = value

            if (wasEmpty) {
                Log.d("TodoRecycler", "Force update recyclerview!")
                notifyDataSetChanged()
            }
        }

    var onToggleDone: (TodoItem, Boolean) -> Unit = { _, _ ->}

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

    inner class TodoViewHolder(private val view: View): RecyclerView.ViewHolder(view) {

        fun bind(item: TodoItem) {
            view.thing_to_do.text = item.thingToDo
            view.is_done.isChecked = item.isDone

            Log.d("TodoViewHolder:bind", "${item.thingToDo}: ${item.isDone}")

            view.is_done.setOnCheckedChangeListener { _, isChecked ->
                Log.d("TodoViewHolder:setOnCheckedChangeListener", "!!")

                onToggleDone(item, isChecked)
            }
        }
    }
}
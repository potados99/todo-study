package org.potados.todo

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.todo_item.view.*

class TodoRecyclerAdapter : RecyclerView.Adapter<TodoRecyclerAdapter.TodoViewHolder>() {

    // 어댑터가 표시할 요소들(할 일들).
    var items: List<TodoItem> = listOf()
        set(value) {
            val wasEmpty = field.isEmpty()
            field = value

            if (wasEmpty) {
                Log.d("TodoRecycler", "Force update recyclerview!")
                notifyDataSetChanged()
            }
        }

    // 할 일 체크박스가 눌렸을 때에 호출될 함수.
    var onToggleDone: (TodoItem, Boolean) -> Unit = { _, _ ->}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        // 뷰 생성
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.todo_item, parent, false)

        // 뷰 홀더 생성
        return TodoViewHolder(view)
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        // 아이템을 구해서
        val item = items[position]

        // 뷰 홀더와 bind
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class TodoViewHolder(private val view: View): RecyclerView.ViewHolder(view) {

        fun bind(item: TodoItem) {
            // 각각 텍스트와 체크박스 값을 설정해줌.
            view.thing_to_do.text = item.thingToDo
            view.is_done.isChecked = item.isDone

            Log.d("TodoViewHolder:bind", "${item.thingToDo}: ${item.isDone}")

            // 사용자에 의해 체크박스 값이 바뀔 때마다
            view.is_done.setOnCheckedChangeListener { _, isChecked ->
                Log.d("TodoViewHolder:setOnCheckedChangeListener", "!!")

                // onToggleDone 함수를 호출해줌.
                onToggleDone(item, isChecked)
            }
        }
    }
}
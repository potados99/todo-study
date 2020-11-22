package org.potados.todo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Debug
import android.util.Log
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import org.potados.todo.data.TodoRepository

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()
    }

    override fun onResume() {
        super.onResume()

        viewModel.addDummyData()
    }

    private fun initView() {
        val adapter = TodoRecyclerAdapter()
        todo_list.adapter = adapter
        todo_list.layoutManager = LinearLayoutManager(this)

        viewModel.allTodo.observe(this) {
            Log.d("MainActivity", "Got all todo")
            adapter.items = it
        }
    }
}
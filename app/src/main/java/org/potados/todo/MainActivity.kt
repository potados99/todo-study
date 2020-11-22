package org.potados.todo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Debug
import android.util.Log
import android.view.Menu
import androidx.activity.viewModels
import androidx.core.view.iterator
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import org.potados.todo.data.TodoRepository

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()
    private val todoAdapter = TodoRecyclerAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)

        return true
    }

    override fun onResume() {
        super.onResume()

        viewModel.addDummyData()
    }

    private fun initView() {
        with(todo_list) {
            adapter = todoAdapter

            viewModel.allTodo.observe(this@MainActivity) {
                todoAdapter.items = it
            }
        }

    }
}
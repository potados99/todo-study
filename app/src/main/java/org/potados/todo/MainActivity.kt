package org.potados.todo

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Debug
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
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

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_new_todo -> {
                onAddButtonClick()
                true
            }
            else -> false
        }
    }

    @SuppressLint("RestrictedApi")
    private fun onAddButtonClick() {
        val textInput = EditText(this).apply {
            hint = "할 일을 입력해 주세요"
        }

        AlertDialog.Builder(this)
            .setTitle("할 일 추가")
            .setMessage("할 일을 입력해 주세요")
            .setView(textInput, 60, 0, 60, 0)
            .setPositiveButton("추가") { dialog, _ ->
                onAddTodoItem(textInput.text.toString())
                dialog.dismiss() // Not gonna dismiss automatically
            }
            .setNegativeButton("취소") { _, _ ->
                // Do nothing
            }
            .show()
    }

    private fun onAddTodoItem(thingToDo: String) {
        viewModel.addTodo(thingToDo)
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
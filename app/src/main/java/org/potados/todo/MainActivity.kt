package org.potados.todo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()
    }

    private fun initView() {
        val adapter = TodoRecyclerAdapter()
        todo_list.adapter = adapter
        todo_list.layoutManager = LinearLayoutManager(this)

        adapter.items = listOf(
            TodoItem("장보러가기", false),
            TodoItem("밥먹기", true)
        )
    }
}
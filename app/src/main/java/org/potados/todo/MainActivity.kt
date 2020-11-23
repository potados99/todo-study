package org.potados.todo

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()
    private val todoAdapter = TodoRecyclerAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 뷰 초기화
        initView()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // 상단 툴바 메뉴 추가
        menuInflater.inflate(R.menu.menu_main, menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_new_todo -> {
                // 할 일 추가 버튼이 눌렸을 때에 onAddButtonClick 호출.
                onAddButtonClick()
                true
            }
            else -> false
        }
    }

    @SuppressLint("RestrictedApi")
    private fun onAddButtonClick() {
        // 다이얼로그를 띄워서 사용자에게 할 일을 입력받음.

        // 다이얼로그 속에 포함될, 사용자의 입력을 받기 위한 EditText.
        val textInput = EditText(this).apply {
            hint = "할 일을 입력해 주세요"
        }

        // 다이얼로그
        AlertDialog.Builder(this)
            .setTitle("할 일 추가")
            .setMessage("할 일을 입력해 주세요")
            .setView(textInput, 60, 0, 60, 0)
            .setPositiveButton("추가") { dialog, _ ->
                // 작성을 마치고 추가 버튼을 눌렀을 때에,
                // 작석된 내용을 바탕으로 onAddButtonClick 호출.
                onAddTodoItem(textInput.text.toString())
                dialog.dismiss() // 다이얼로그 종료
            }
            .setNegativeButton("취소") { _, _ ->
                // 아무 일도 안 함
            }
            .show()
    }

    private fun onAddTodoItem(thingToDo: String) {
        // 할 일을 추가하도록 ViewModel에 지시
        viewModel.addTodo(thingToDo)
    }

    private fun initView() {
        with(todo_list) {
            // 리사이클러 뷰 어댑서 설정
            adapter = todoAdapter

            // ViewModel로부터 모든 할 일을 가져와,
            // 향후 변화가 생길 때마다 그 값(할 일들)을 어댑터에 대입해줌.
            viewModel.allTodo.observe(this@MainActivity) {
                todoAdapter.items = it
            }
        }

        with(todoAdapter) {
            // 각 할 일들의 체크박스가 눌리면 ViewModel의 toggleDone을 호출.
            onToggleDone = viewModel::toggleDone
        }

    }
}
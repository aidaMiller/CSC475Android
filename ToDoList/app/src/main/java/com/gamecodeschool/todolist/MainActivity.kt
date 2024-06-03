package com.gamecodeschool.todolist

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private val todoViewModel: TodoViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val fab = findViewById<FloatingActionButton>(R.id.fab)

        val adapter = TodoAdapter(listOf(), todoViewModel)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        todoViewModel.allTodos.observe(this, Observer { todos ->
            todos?.let { adapter.notifyDataSetChanged() }
        })

        fab.setOnClickListener {
            val dialog = AddTodoDialogFragment()
            dialog.show(supportFragmentManager, "AddTodoDialog")
        }
    }
}
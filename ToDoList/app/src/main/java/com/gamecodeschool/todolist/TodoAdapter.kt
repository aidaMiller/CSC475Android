package com.gamecodeschool.todolist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TodoAdapter(private val todos: List<Todo>, private val viewModel: TodoViewModel) :
    RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_todo, parent, false)
        return TodoViewHolder(view)
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val current = todos[position]
        holder.bind(current)
    }

    override fun getItemCount() = todos.size

    inner class TodoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val checkBox: CheckBox = itemView.findViewById(R.id.checkBox)
        private val textView: TextView = itemView.findViewById(R.id.textView)

        fun bind(todo: Todo) {
            textView.text = todo.task
            checkBox.isChecked = todo.isCompleted

            checkBox.setOnCheckedChangeListener { _, isChecked ->
                val updatedTodo = Todo(todo.id, todo.task, isChecked)
                viewModel.update(updatedTodo)
            }

            itemView.setOnLongClickListener {
                viewModel.delete(todo)
                true
            }
        }
    }
}
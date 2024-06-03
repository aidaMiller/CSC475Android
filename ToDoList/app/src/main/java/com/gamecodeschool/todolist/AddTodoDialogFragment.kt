package com.gamecodeschool.todolist


import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels

class AddTodoDialogFragment : DialogFragment() {

    private val todoViewModel: TodoViewModel by activityViewModels()

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            val inflater = requireActivity().layoutInflater
            val view:View = inflater.inflate(R.layout.dialog_add_todo, null)
            val editTextTask = view.findViewById<EditText>(R.id.editTextTask)

            builder.setView(view)
                builder.setPositiveButton(R.string.add, DialogInterface.OnClickListener { _, _ ->
                    val task = editTextTask.text.toString()
                    if (task.isNotEmpty()) {
                        val todo = Todo(task = task)
                        todoViewModel.insert(todo)
                    }
                })
                .setNegativeButton(R.string.cancel,DialogInterface.OnClickListener { dialog, id ->
                    dialog.cancel()
                })

            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}
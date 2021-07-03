package com.hfad.todoapp.ui


import androidx.fragment.app.DialogFragment
import com.hfad.todoapp.DeleteConfirmationDialogFragment
import com.hfad.todoapp.database.entities.Task

interface AddDialogListener {
    fun onDoneButtonClicked(newTask: Task, dialog: DialogFragment)
    fun onDeleteClicked(task: Task, dialog: DeleteConfirmationDialogFragment )

}
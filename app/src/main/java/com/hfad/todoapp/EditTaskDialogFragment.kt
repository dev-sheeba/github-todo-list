package com.hfad.todoapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.hfad.todoapp.database.entities.Task
import com.hfad.todoapp.databinding.FragmentEditTaskDialogBinding

class EditTaskDialogFragment(val task: Task, private val listener: OnEditTaskListener) : BottomSheetDialogFragment() {

    interface OnEditTaskListener{
        fun onEditDoneClick(item: Task, dialog: EditTaskDialogFragment)
    }

    private lateinit var binding: FragmentEditTaskDialogBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentEditTaskDialogBinding.inflate(layoutInflater, container, false)

        binding.apply {
            editDialogTask.setText(task.taskName)

            editDialogDoneBtn.setOnClickListener {
                val taskTitle = binding.editDialogTask.text
                if (taskTitle.isBlank()) {
                    Toast.makeText(requireContext(), "Cannot be empty", Toast.LENGTH_SHORT).show()
                } else {
                    val newTask = task.copy(taskName = taskTitle.toString()).also { it.id = task.id }
                    listener.onEditDoneClick(newTask, this@EditTaskDialogFragment)
                }

            }



        }
        return binding.root
    }
}
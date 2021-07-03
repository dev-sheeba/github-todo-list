package com.hfad.todoapp

import android.content.Context
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.hfad.todoapp.database.entities.Task
import com.hfad.todoapp.databinding.FragmentDialogBinding
import com.hfad.todoapp.ui.AddDialogListener


open class TaskDialogFragment : DialogFragment() {

    private lateinit var binding: FragmentDialogBinding
    lateinit var listener: AddDialogListener

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout to use as dialog or embedded fragment

        binding = FragmentDialogBinding.inflate(layoutInflater, container, false)
        binding.onDoneClicked.setOnClickListener {
            val checkBox = binding.dialogCheckBox.isChecked
            val taskTitle = binding.dialogTaskTitle.text.toString()
            if (taskTitle.isNullOrEmpty()) {
                Toast.makeText(context, "Please enter a Task", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val item = Task(taskTitle, checkBox)
            listener.onDoneButtonClicked(item,this)
        }
        return binding.root
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            listener = parentFragment as AddDialogListener
        } catch (e: ClassCastException) {
            throw ClassCastException((parentFragment.toString() + "must implement AddDialogListener"))
        }
    }
}



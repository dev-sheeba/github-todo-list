package com.hfad.todoapp

import android.os.Bundle
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.hfad.todoapp.database.entities.Task
import com.hfad.todoapp.databinding.FragmentDeleteConfirmationDialogBinding


class DeleteConfirmationDialogFragment(val listener: OnDeleteConfirmListener, val task: Task ) : BottomSheetDialogFragment() {

    interface OnDeleteConfirmListener {
        fun onDeleteConfirm(task: Task, dialog: DeleteConfirmationDialogFragment)
        fun onCancel(dialog: DeleteConfirmationDialogFragment)
    }

    private lateinit var binding: FragmentDeleteConfirmationDialogBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDeleteConfirmationDialogBinding.inflate(layoutInflater, container, false)
        setStyle(DialogFragment.STYLE_NORMAL, R.style.Theme_MaterialComponents_BottomSheetDialog)
        binding.deleteConfirmation.setOnClickListener {
            listener.onDeleteConfirm(task, this)
        }

        binding.cancelButton.setOnLongClickListener {
            listener.onCancel(this)
            true
        }
        return binding.root
    }




}
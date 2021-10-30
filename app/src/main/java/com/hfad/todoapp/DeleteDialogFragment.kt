package com.hfad.todoapp

import android.os.Bundle
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hfad.todoapp.database.entities.Task
import com.hfad.todoapp.databinding.FragmentDeleteDialogBinding


class DeleteDialogFragment(val listener: DeleteConfirmationDialogFragment.OnDeleteConfirmListener, val task: Task)
    : BottomSheetDialogFragment() {


    private lateinit var binding: FragmentDeleteDialogBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDeleteDialogBinding.inflate(layoutInflater, container, false)
        binding.deleteDialog.setOnClickListener {
            this.dismiss()
            val dialog = DeleteConfirmationDialogFragment(listener, task)
            dialog.show(parentFragmentManager, "DeleteConfirmationDialogFragment")
        }

        return binding.root
    }

}
package com.hfad.todoapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.hfad.todoapp.databinding.FragmentEditDialogBinding

class EditDialogFragment : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentEditDialogBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentEditDialogBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

}
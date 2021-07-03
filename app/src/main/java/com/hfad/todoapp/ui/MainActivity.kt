package com.hfad.todoapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.FrameLayout
import com.hfad.todoapp.TaskFragment
import com.hfad.todoapp.R
import com.hfad.todoapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var fragmentContainer: FrameLayout? = null
    private var homeFragment = TaskFragment()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        fragmentContainer = binding.fragmentContainer

        supportFragmentManager
                .beginTransaction()
                .add(R.id.fragment_container, homeFragment)
                .commit()
    }
}
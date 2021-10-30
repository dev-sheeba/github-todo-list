package com.hfad.todoapp.ui

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hfad.todoapp.database.entities.Task
import com.hfad.todoapp.database.repositories.TaskRepository
import kotlinx.coroutines.launch

class EditTaskViewModel(private val repository: TaskRepository, private val state: SavedStateHandle
) : ViewModel() {

//    val task = state.get<Task>("task")
//
//
//    var taskName = state.get<String>("taskName") ?: task?.taskName ?: ""
//    set(value) {
//        field = value
//        state.set("taskName", value)
//    }
//
//    fun onClick() {
//        if (task != null) {
//            val updatedTask = task.copy(taskName = taskName)
//            updateTask(updatedTask)
//        } else {
//            val newTask = Task(taskName, false)
//            createTask(newTask)
//        }
//    }

     fun updateTask(task: Task) = viewModelScope.launch {
         repository.update(task)
     }

     fun createTask(task: Task) = viewModelScope.launch {
        repository.insert(task)
    }
}
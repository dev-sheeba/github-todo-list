package com.hfad.todoapp.ui

import androidx.lifecycle.*
import com.hfad.todoapp.database.entities.Task
import com.hfad.todoapp.database.repositories.TaskRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class TaskViewModel(private val repository: TaskRepository) : ViewModel() {

    //ask why val and not fun
    fun allTasks(): LiveData<List<Task>> = repository.allTasks.asLiveData()

    fun insert(task: Task) = viewModelScope.launch {
        repository.insert(task)
    }

    fun update(task: Task) = viewModelScope.launch {
        repository.update(task)
    }

    fun delete(task: Task) = viewModelScope.launch {
        repository.delete(task)
    }

    fun deleteAll() = viewModelScope.launch {
        repository.deleteAll()
    }

    fun searchDatabase(searchQuery: String): LiveData<List<Task>> =
        repository.searchDatabase(searchQuery).asLiveData()

    fun onTaskSelected(task: Task) {

    }

    fun onTaskIsCheckedChanged(task: Task, isChecked: Boolean) = viewModelScope.launch {
        val newItem = task.copy(isCompleted = isChecked)
            .also { it.id = task.id }
        update(newItem)
    }


    class TaskViewModelFactory(private val repository: TaskRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(TaskViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return TaskViewModel(repository) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }

    }
}
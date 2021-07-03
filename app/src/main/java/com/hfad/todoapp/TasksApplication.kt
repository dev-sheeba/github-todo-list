package com.hfad.todoapp

import android.app.Application
import com.hfad.todoapp.database.db.TaskRoomDatabase
import com.hfad.todoapp.database.repositories.TaskRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class TasksApplication: Application() {

    private val applicationScope = CoroutineScope(SupervisorJob())

    val database by lazy { TaskRoomDatabase.getDatabase(this, applicationScope) }

    val repository by lazy { TaskRepository(database.taskDao()) }
}
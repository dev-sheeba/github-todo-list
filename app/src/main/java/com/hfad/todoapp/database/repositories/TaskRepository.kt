package com.hfad.todoapp.database.repositories

import androidx.annotation.WorkerThread
import com.hfad.todoapp.database.entities.Task
import com.hfad.todoapp.database.db.TaskDao
import com.hfad.todoapp.database.db.TaskRoomDatabase
import kotlinx.coroutines.flow.Flow

class TaskRepository(private val taskDao: TaskDao) {

    val allTasks: Flow<List<Task>> = taskDao.getAll()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(task: Task) = taskDao.insertAll(task)
    @WorkerThread
    suspend fun update(task: Task) = taskDao.updateTask(task)

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun delete(task: Task) = taskDao.delete(task)

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun deleteAll() = taskDao.deleteAll()

    fun searchDatabase(searchQuery: String): Flow<List<Task>> = taskDao.searchDatabase(searchQuery)

}

//suspend fun delete(task: Task) = db.taskDao().delete(task)
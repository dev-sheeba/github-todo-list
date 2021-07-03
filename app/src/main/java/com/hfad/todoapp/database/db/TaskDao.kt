package com.hfad.todoapp.database.db

import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import com.hfad.todoapp.database.entities.Task
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

@Dao
interface TaskDao {
    @Query("SELECT * FROM task_table ORDER BY is_completed ASC, id DESC")
    fun getAll(): Flow<List<Task>>

    @Query("SELECT * FROM task_table ORDER BY id ASC")
    fun readData(): Flow<List<Task>>
    @Insert
    suspend fun insertAll(task: Task)

    @Update(onConflict = REPLACE)
    suspend fun updateTask(task: Task)

    @Delete
    suspend fun delete(task: Task)

    @Query("DELETE FROM task_table")
    suspend fun deleteAll()

    @Query("SELECT * FROM task_table WHERE task_name LIKE :searchQuery")
    fun searchDatabase(searchQuery: String) : Flow<List<Task>>
}
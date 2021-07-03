package com.hfad.todoapp.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "task_table")
data class Task(

    @ColumnInfo(name = "task_name") val taskName: String?,
    @ColumnInfo(name = "is_completed") val isCompleted: Boolean
) {
    @PrimaryKey(autoGenerate = true) var id: Long? = null
}


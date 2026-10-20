package com.example.tasks.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "task_table")
data class Task(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "task_id")
    var taskId: Long = 0,
    @ColumnInfo(name = "task_name")
    var taskName: String = "",
    @ColumnInfo(name = "task_done")
    var taskDone: Boolean = false,
)

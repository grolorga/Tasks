package com.example.tasks.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface TaskDao {
    @Insert
    suspend fun insert(task: Task): Long

    @Update
    suspend fun update(task: Task)

    @Delete
    suspend fun delete(task: Task)

    @Query("SELECT * FROM task_table WHERE task_id = :taskId")
    fun get(taskId: Long): LiveData<Task?>

    @Query("SELECT * FROM task_table ORDER BY task_id DESC")
    fun getAll(): LiveData<List<Task>>
}

package com.example.tasks.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.example.tasks.data.Task
import com.example.tasks.data.TaskDao
import kotlinx.coroutines.launch

class TasksViewModel(private val taskDao: TaskDao) : ViewModel() {
    val newTaskName = MutableLiveData("")
    val tasks: LiveData<List<Task>> = taskDao.getAll()

    val tasksString: LiveData<String> = tasks.map(::formatTasks)

    fun addTask() {
        val taskName = newTaskName.value.orEmpty().trim()
        if (taskName.isEmpty()) return

        viewModelScope.launch {
            taskDao.insert(Task(taskName = taskName))
            newTaskName.value = ""
        }
    }

    private fun formatTasks(tasks: List<Task>): String =
        tasks.joinToString(separator = "\n\n", transform = ::formatTask)

    private fun formatTask(task: Task): String =
        "ID: ${task.taskId}\nName: ${task.taskName}\nComplete: ${task.taskDone}"
}

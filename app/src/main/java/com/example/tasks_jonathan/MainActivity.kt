package com.example.tasks_jonathan

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tasks_jonathan.databinding.ActivityMainBinding
import com.example.tasks_jonathan.utils.SnackbarHelper

class MainActivity : AppCompatActivity(), ClickDetectorInterface {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: TodoAdapter
    private lateinit var snackbarHelper: SnackbarHelper
    private var tasksList:MutableList<Task> = mutableListOf()

    private lateinit var btnAddTask: Button
    private lateinit var btnUpdateTask: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // init custom snackbar helper
        snackbarHelper = SnackbarHelper(binding.root)

        // getting mock data
        this.tasksList = Mock.TODO_LIST.toMutableList()

        // creating an instance of the custom adapter
        this.adapter = TodoAdapter(this, this.tasksList, this)
        this.binding.rvContainer.adapter = this.adapter

        // configuring the RecyclerView with a LinearLayoutManager
        this.binding.rvContainer.layoutManager = LinearLayoutManager(this)

        // adding a line between each item in the list of the
        this.binding.rvContainer.addItemDecoration(
            DividerItemDecoration(
                this,
                LinearLayoutManager.VERTICAL
            )
        )

        // initializing buttons references to global variables
        btnAddTask = findViewById(R.id.btnAddTask)
        btnUpdateTask = findViewById(R.id.btnUpdateTask)

        // initializing default form values and properties
        resetForm()

        // click handlers
        this.binding.btnAddTask.setOnClickListener {
            addNewTask(
                this.binding.etNewTask.text.toString(),
                this.binding.swIsHighPriority.isChecked
            )
        }

        this.binding.btnUpdateTask.setOnClickListener {
            updateTask()
        }
    }

    private fun addNewTask(newTask: String, isHighPriority: Boolean) {
        val newTask: Task = Task(newTask, isHighPriority)
        this.tasksList.add(newTask)
        this.adapter.notifyDataSetChanged()
        this.snackbarHelper.showSnackbar("Task added.")
        resetForm()
    }

    private fun updateTask() {
        // to update task, se values in form
    }

    private fun resetForm() {
        binding.etNewTask.setText("")
        binding.swIsHighPriority.isChecked = false
        // buttons btnAddTask and btnUpdateTask are enabled and disabled by default respectively
        btnAddTask.isEnabled = true
        btnUpdateTask.isEnabled = false
    }

    override fun onDeleteClicked(position: Int) {
        this.snackbarHelper.showSnackbar("Task deleted.")
    }

    override fun onEditClicked(position: Int) {
        this.snackbarHelper.showSnackbar("Task updated.")
    }
}
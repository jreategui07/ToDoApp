package com.example.tasks_jonathan

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tasks_jonathan.databinding.TodoRowLayoutBinding

class TodoAdapter(
    private val context: Context,
    private val taskList: MutableList<Task>,
    val clickInterface:ClickDetectorInterface
): RecyclerView.Adapter<TodoAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: TodoRowLayoutBinding) : RecyclerView.ViewHolder (binding.root) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = TodoRowLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return taskList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val task: Task = this.taskList[position]

        // loading task name
        holder.binding.tvTodo.text = task.name

        //loading isHighPriority img if apply
        if (task.isHighPriority) {
            Glide.with(context)
                .load(R.drawable.ic_notification_important)
                .into(holder.binding.ivNotificationImportant)
        } else {
            holder.binding.ivNotificationImportant.setImageDrawable(null)
        }

        // click handlers
        holder.binding.ivDelete.setOnClickListener {
            clickInterface.onDeleteClicked(position)
        }

        holder.binding.ivEdit.setOnClickListener {
            clickInterface.onEditClicked(position)
        }
    }
}
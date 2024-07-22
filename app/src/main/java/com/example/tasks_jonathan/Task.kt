package com.example.tasks_jonathan

class Task(var name: String, var isHighPriority: Boolean) {
    override fun toString(): String {
        return "Task(name='$name', isHighPriority=$isHighPriority)"
    }
}
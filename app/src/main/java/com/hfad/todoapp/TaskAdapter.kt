package com.hfad.todoapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.hfad.todoapp.database.entities.Task
import com.hfad.todoapp.databinding.HomeRecyclerviewItemBinding

class TaskAdapter(private val listener: OnTaskActionListener) :
    ListAdapter<Task, TaskAdapter.TaskViewHolder>(ListsComparator()){

    interface OnTaskActionListener {
        fun onItemClick(task: Task)
        fun onItemLongClick(task: Task)
        fun onCheckBoxClick(task: Task, isChecked: Boolean)
    }

    inner class TaskViewHolder(private val binding: HomeRecyclerviewItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(task: Task) {
            binding.apply {
                homeRvItemTv.text = task.taskName
                homeRvItemTv.paint.isStrikeThruText = task.isCompleted
                homeRvItemCb.isChecked = task.isCompleted
                homeRvItemCb.setOnClickListener {
                    listener.onCheckBoxClick(task, homeRvItemCb.isChecked)
                }
                binding.root.setOnLongClickListener {
                    listener.onItemLongClick(task)
                    true
                }

                binding.root.setOnClickListener {
                    listener.onItemClick(task)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {

        val binding = HomeRecyclerviewItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )
        return TaskViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }


    class ListsComparator : DiffUtil.ItemCallback<Task>() {
        override fun areItemsTheSame(oldItem: Task, newItem: Task): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Task, newItem: Task): Boolean {
            return oldItem == newItem
        }
    }



}


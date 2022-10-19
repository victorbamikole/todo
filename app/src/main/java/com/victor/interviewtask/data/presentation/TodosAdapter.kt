package com.victor.interviewtask.data.presentation

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.victor.interviewtask.data.model.TodosItem
import com.victor.interviewtask.data.utils.onItemClickListener
import com.victor.interviewtask.databinding.RecyclerItemBinding

class TodosAdapter(private val apps: List<TodosItem>):RecyclerView.Adapter<TodosAdapter.AppHolder>() {

    private lateinit var context: Context
    private lateinit var mListener: onItemClickListener

    fun setOnItemClickListener(listener: onItemClickListener) {
        mListener = listener
    }

    private val differCallBack = object : DiffUtil.ItemCallback<TodosItem>() {
        override fun areItemsTheSame(oldItem: TodosItem, newItem: TodosItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: TodosItem, newItem: TodosItem): Boolean {
            return oldItem.title == newItem.title
        }
    }


    private val differ = AsyncListDiffer(this, differCallBack)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodosAdapter.AppHolder {
        val binding =
            RecyclerItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        context = parent.context
        return AppHolder(binding, mListener)
    }


    override fun onBindViewHolder(holder: TodosAdapter.AppHolder, position: Int) {
        holder.bind(apps[position])
    }

    override fun getItemCount(): Int {
        return apps.size
    }

    inner class AppHolder(binding: RecyclerItemBinding, listener: onItemClickListener) :
        RecyclerView.ViewHolder(binding.root) {
        private val id: TextView = binding.id
        private val title: TextView = binding.title


        private val item = binding.root

        init {

            item.setOnClickListener {
                listener.allTodoItemClicked(adapterPosition)
            }
        }

        fun bind(toDo: TodosItem) {
            id.text = toDo.id.toString()
            title.text = toDo.title

        }
    }

}
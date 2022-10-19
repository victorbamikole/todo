package com.victor.interviewtask.data.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.victor.interviewtask.R
import com.victor.interviewtask.data.utils.onItemClickListener
import com.victor.interviewtask.data.viewmodel.TodosViewModel
import com.victor.interviewtask.databinding.FragmentTodosBinding
import java.net.SocketTimeoutException

class TodosFragment : Fragment() {

    private var _binding: FragmentTodosBinding? = null
    private val binding get() = _binding!!

    private lateinit var recyclerAdapter: TodosAdapter
    private lateinit var recyclerView: RecyclerView
    private val viewModel: TodosViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentTodosBinding.inflate(layoutInflater, container, false)

        initViewModel()

        recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(activity)


        return binding.root
    }

    private fun initViewModel() {
        viewModel.makeApiCall()

        viewModel.getRecyclerListObserver().observe(viewLifecycleOwner){
            if (it != null) {
                try {
                    recyclerAdapter = TodosAdapter(it)//it.apps
                    recyclerView.adapter = recyclerAdapter
                }catch (e: Exception){

                }

                recyclerAdapter.setOnItemClickListener(object : onItemClickListener {
                    override fun allTodoItemClicked(position: Int) {
                        val id = it[position].id

                        findNavController().navigate(
                            TodosFragmentDirections.actionTodosFragmentToDetailsFragment(id)
                        )
                    }
                })

            } else {
                Toast.makeText(activity, "error in getting data", Toast.LENGTH_SHORT).show()
            }

        }

    }



}


package com.victor.interviewtask.data.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.victor.interviewtask.data.viewmodel.DetailsViewModel
import com.victor.interviewtask.databinding.FragmentDetailsBinding

class DetailsFragment : Fragment() {

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!

    private val viewModel: DetailsViewModel by viewModels()

    private val args: DetailsFragmentArgs by navArgs()

    private var toDoId: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentDetailsBinding.inflate(layoutInflater, container, false)

        toDoId = args.user

        initViewModel()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModel()
    }

    private fun initViewModel() {
        viewModel.makeApiCall(toDoId)

        viewModel.getDetails().observe(viewLifecycleOwner){
            if (it != null) {
                binding.userId.text  = it.userId.toString()
                binding.id.text  = it.id.toString()
                binding.titleDetails.text  = it.title
                binding.status.text  = it.completed.toString()

            } else {
                Toast.makeText(activity, "error in getting data", Toast.LENGTH_SHORT).show()
            }

        }

    }

}
package com.example.menuappxml.ui.home

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.menuappxml.R
import com.example.menuappxml.adapter.MenuAdapter
import com.example.menuappxml.ui.common.ResultState
import com.example.menuappxml.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {

    private val viewModel: HomeViewModel by viewModels()
    private lateinit var adapter: MenuAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        adapter = MenuAdapter {
            findNavController().navigate(
                R.id.action_homeFragment_to_detailFragment,
                bundleOf("id" to it.id)
            )
        }

        val recycler = view.findViewById<RecyclerView>(R.id.recyclerView)
        val progress = view.findViewById<ProgressBar>(R.id.progress)

        recycler.layoutManager = LinearLayoutManager(requireContext())
        recycler.adapter = adapter

        viewModel.state.observe(viewLifecycleOwner) { state ->
            when (state) {
                is ResultState.Loading -> {
                    progress.visibility = View.VISIBLE
                }
                is ResultState.Success -> {
                    progress.visibility = View.GONE
                    adapter.submitList(state.data)
                }
                is ResultState.Error -> {
                    progress.visibility = View.GONE
                }
            }
        }
    }
}
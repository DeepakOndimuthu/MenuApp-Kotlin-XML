package com.example.menuappxml.ui.detail

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.example.menuappxml.R
import com.example.menuappxml.ui.common.DetailUiState
import com.example.menuappxml.viewmodel.DetailViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment(R.layout.fragment_detail) {

    private val viewModel: DetailViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val image = view.findViewById<ImageView>(R.id.image)
        val title = view.findViewById<TextView>(R.id.title)
        val desc = view.findViewById<TextView>(R.id.description)
        val progress = view.findViewById<ProgressBar>(R.id.progress)
        val button = view.findViewById<Button>(R.id.contactBtn)

        val id = arguments?.getInt("id") ?: return
        viewModel.loadItem(id)

        viewModel.state.observe(viewLifecycleOwner) { state ->
            when (state) {

                is DetailUiState.Loading -> {
                    progress.visibility = View.VISIBLE
                }

                is DetailUiState.Success -> {
                    progress.visibility = View.GONE

                    val item = state.item
                    title.text = item.title
                    desc.text = item.description

                    Glide.with(this)
                        .load(item.imageUrl)
                        .into(image)
                }

                is DetailUiState.Error -> {
                    progress.visibility = View.GONE
                    Toast.makeText(
                        requireContext(),
                        state.message,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }

        button.setOnClickListener {
            Toast.makeText(
                requireContext(),
                "Message sent",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}
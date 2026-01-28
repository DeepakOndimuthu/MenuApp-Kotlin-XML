package com.example.menuappxml.ui.other

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.menuappxml.R

class SimpleFragment : Fragment(R.layout.fragment_simple) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val title = view.findViewById<TextView>(R.id.title)

        // Get title from navigation destination label
        title.text = findNavController()
            .currentDestination
            ?.label
            ?.toString()
            ?: "Page"
    }
}
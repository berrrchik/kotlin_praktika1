package com.example.kotlin_prak1

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.kotlin_prak.MainActivity

class FragmentThree : Fragment(R.layout.fragment_three) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<Button>(R.id.btnBack).setOnClickListener {
            (activity as MainActivity).supportFragmentManager.popBackStack()
        }
    }
}
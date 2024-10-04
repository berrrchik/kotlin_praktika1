package com.example.kotlin_prak1

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.kotlin_prak.MainActivity

class FragmentOne : Fragment(R.layout.fragment_one) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<Button>(R.id.btnNext).setOnClickListener {
            (activity as MainActivity).navigateToFragment(FragmentTwo())
        }
    }
}

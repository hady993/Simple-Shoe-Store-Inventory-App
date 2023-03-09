package com.udacity.shoestore.screens.instructions

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentInstructionsBinding

/**
 * Step5.1: Creating Fragments is mandatory before using binding for inflation!
 * A simple [Fragment] subclass.
 * Use the [InstructionsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class InstructionsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Step5.2: Inflate the layout for this fragment using binding.
        val binding : FragmentInstructionsBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_instructions, container, false
        ) // end binding()

        // Step6.5: Create onClickListener in kotlin code to do the navigation action (using the viewBinding).
        binding.shoeListButtonFragmentInstructions.setOnClickListener {
            // Step6.6: Find the Navigation Controller.
            // Step6.7: Navigate with your action.
            this.findNavController().navigate(InstructionsFragmentDirections.actionInstructionsFragmentToShoeListFragment())
        } // end OnClickListener()

        return binding.root
    } // end onCreateView()

} // end class
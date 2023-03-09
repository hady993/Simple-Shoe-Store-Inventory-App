package com.udacity.shoestore.screens.shoescreens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeDetailsBinding
import com.udacity.shoestore.models.Shoe

/**
 * Step5.1: Creating Fragments is mandatory before using binding for inflation!
 * A simple [Fragment] subclass.
 * Use the [ShoeDetailsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ShoeDetailsFragment : Fragment() {

    /**
     * Step11.1: Create an object of the ShoeViewModel class.
     * We used by activityViewModels, because our viewModel is shared between the ShoeListFragment,
       and the ShoeDetailsFragment!
     * My viewModel must be private val to work with activityViewModels() = This was a struggling error!!
     */
    private val viewModel : ShoeViewModel by activityViewModels()

    // Step11.2: Initialize the shoe data with null variables!
    private val shoeDetails = Shoe("", "", "", "")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Step5.2: Inflate the layout for this fragment using binding.
        val binding : FragmentShoeDetailsBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_shoe_details, container, false
        ) // end binding()

        /**
         * Step14.16: Get the shoeViewModel that in your xml file, and initialize it with our viewModel.
         * Step14.17: Establish this class as a lifeCycleOwner.
         * Step14.18: Get the shoeDetails that in your xml file, and initialize it with our shoeDetails.
         */
        // Make a relationship between the xml and the logic.
        // Set the viewmodel for databinding - this allows the bound layout access to all of the
        // data in the ViewModel.
        binding.shoeViewModel = viewModel

        // This is instead of the LifecycleObserver.
        // Specify the current activity as the lifecycle owner of the binding.
        // This is used so that the binding can observe LiveData updates.
        binding.setLifecycleOwner(this)

        // Make a relationship between the xml and the logic.
        // Set the viewmodel for databinding - this allows the bound layout access to all of the
        // data in the ViewModel.
        binding.shoeDetails = shoeDetails

        /**
         * Step11.4: After pressing the cancel button, navigate back to the shoe list.
         */
        binding.cancelButtonFragmentShoeDetails.setOnClickListener {
            navigateBackToShoeList()
        } // end OnClickListener

        /**
         * Step11.4: Establish the class observer for our ShoeDetailsFragment class!
         * We will observe the addingShoeState value to pass its items to our lifecycle owner!
         * We will use viewModel.shoeList.observe(owner, observer) method!
         * If the adding state is ADD not CANCEL, we will navigate back to the shoe list,
           and completing our adding process!
         */
        viewModel.addingShoeState.observe(viewLifecycleOwner, Observer { addingState ->
            // If the adding state is ADD, navigate back and complete your adding process!
            when(addingState) {
                ShoeViewModel.AddingShoeState.ADD -> {
                    navigateBackToShoeList()
                    viewModel.onEventAddShoeComplete()
                } // end ADD
                ShoeViewModel.AddingShoeState.ERROR -> {
                    Toast.makeText(context, "Enter the shoe details!", Toast.LENGTH_SHORT).show()
                    viewModel.onEventAddShoeComplete()
                } // end ERROR
            } // end when()
        }) // end observe()

        return binding.root
    } // end onCreateView()

    /**
     * Step11.3: Use this function to avoid redundancy of code lines, to make your code clean!
     * We use it to navigate back to the shoe list fragment via navigateUp().
     */
    private fun navigateBackToShoeList() {
        this.findNavController().navigateUp()
    } // end navigateBackToShoeList()

} // end class
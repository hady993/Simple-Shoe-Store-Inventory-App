package com.udacity.shoestore.screens.shoescreens

import android.os.Bundle
import android.view.*
import android.view.ViewGroup.LayoutParams
import android.widget.TextView
import androidx.core.view.updateLayoutParams
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeListBinding
import com.udacity.shoestore.models.Shoe

/**
 * Step5.1: Creating Fragments is mandatory before using binding for inflation!
 * A simple [Fragment] subclass.
 * Use the [ShoeListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ShoeListFragment : Fragment() {

    /**
     * Step10.5: Create an object of the ShoeViewModel class.
     * We used by activityViewModels, because our viewModel is shared between the ShoeListFragment,
       and the ShoeDetailsFragment!
     * My viewModel must be private val to work with activityViewModels() = This was a struggling error!!
     */
    private val viewModel : ShoeViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Step5.2: Inflate the layout for this fragment using binding.
        val binding : FragmentShoeListBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_shoe_list, container, false
        ) // end binding()

        // Step6.5: Create onClickListener in kotlin code to do the navigation action (using the viewBinding).
        binding.addShoeButtonFragmentShoeList.setOnClickListener {
            // Step6.6: Find the Navigation Controller.
            // Step6.7: Navigate with your action.
            this.findNavController().navigate(ShoeListFragmentDirections.actionShoeListFragmentToShoeDetailsFragment())
        } // end OnClickListener

        /**
         * Step10.5: Establish the class observer for our ShoeListFragment class!
         * We will observe the shoeList value to pass its items to our lifecycle owner!
         * We will use viewModel.shoeList.observe(owner, observer) method!
         */
        viewModel.shoeList.observe(viewLifecycleOwner, Observer {
            // Find every item in the shoeList to view it added as a view to our itemLinearLayout!
            for(item in viewModel.shoeList.value!!) {

                // Step14.16: Get the item's layout using the view binding.
                val ourContainer = binding.itemLayoutFragmentShoeList

                // Step14.17: Create a new text view to use it to add a new shoe to our linear layout.
                val shoeDetails = TextView(context)

                // Step14.18: Set the text view's parameters using LayoutParams.
                shoeDetails.layoutParams =
                    LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)

                // Step14.19: Center align text in the TextView.
                shoeDetails.textAlignment = View.TEXT_ALIGNMENT_CENTER

                // Step14.20: Make a string that contains our shoe's details using buildString,
                // and set it to our text view.
                shoeDetails.text = buildString {
                    append("\n\n\n\nShoe Name: ")
                    append(item.name)
                    append("\nShoe Size: ")
                    append(item.size.toString())
                    append("\nCompany: ")
                    append(item.company)
                    append("\nDescription: ")
                    append(item.description)
                    append("\n\n___________________________________________________")
                } // end buildString

                // Step14.21: Add our text view to the linear layout using addView().
                ourContainer.addView(shoeDetails)

            } // end for()
        }) // end observe()

        // Step13.2: To allow the onCreateOptionsMenu to be called!
        setHasOptionsMenu(true)

        return binding.root
    } // end onCreateView()

    /**
     * Step13.3: Now we can override the onCreateOptionsMenu method, because it will be called!
     * This method sets the menu item to invisible if the activity doesn't resolve!
     * After Step13.4 the remaining steps are in MainActivity!
     */
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        /** Step13.4: Let's inflate our logout menu! */
        inflater.inflate(R.menu.menu_logout, menu)
        super.onCreateOptionsMenu(menu, inflater)
    } // end onCreateOptionsMenu()

} // end class
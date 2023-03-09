package com.udacity.shoestore.screens.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentLoginBinding

/**
 * Step5.1: Creating Fragments is mandatory before using binding for inflation!
 * A simple [Fragment] subclass.
 */
class LoginFragment : Fragment() {

    // Step10.3: Associate the UI Controller and the ViewModel.
    private lateinit var viewModel: LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Step5.2: Inflate the layout for this fragment using binding.
        val binding : FragmentLoginBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_login, container, false
        ) // end binding()

        // Step10.3: Initialize the viewModel using ViewModelProvider (after the association).
        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

        // Step6.5: Create onClickListener in kotlin code to do the navigation action (using the viewBinding).
        binding.registerButtonFragmentLogin.setOnClickListener {
            // Step10.5: Calling the registration method from the viewModel.
            viewModel.onRegister(binding.emailEditFragmentLogin.text.toString(),
                binding.passwordEditFragmentLogin.text.toString())
        } // end OnClickListener()

        // Step6.5: Create onClickListener in kotlin code to do the navigation action (using the viewBinding).
        binding.loginButtonFragmentLogin.setOnClickListener {
            // Step10.5: Calling the logging method from the viewModel.
            viewModel.onLogin(binding.emailEditFragmentLogin.text.toString(),
                binding.passwordEditFragmentLogin.text.toString())
        } // end OnClickListener()

        // Step10.6: To observe the LifecycleOwner and take the action according to the logging state.
        viewModel.loggingState.observe(viewLifecycleOwner, Observer { logState ->
            // Observing the type of state.
            when (logState!!) {

                // The logging state is LOGIN.
                LoginViewModel.LoggingState.LOGIN -> {
                    // Navigate to the welcome fragment.
                    navigateToWelcomeFragment()

                    // Clear the inputs.
                    binding.emailEditFragmentLogin.text.clear()
                    binding.passwordEditFragmentLogin.text.clear()

                    // Step10.7: Notify that the login has completed, and reset the logging state!
                    viewModel.onEventLoginComplete()
                } // end LOGIN

                // The logging state is NULL.
                LoginViewModel.LoggingState.NULL -> {
                    // Notify that the email or password are invalid, or any field is empty!
                    Toast.makeText(context, "Please enter a valid email or password!", Toast.LENGTH_SHORT).show()
                } // end NULL

                // The logging state is NOTREG.
                LoginViewModel.LoggingState.NOTREG -> {
                    // Notify that the account is not registered!
                    Toast.makeText(context, "Register first!", Toast.LENGTH_SHORT).show()
                } // end NOTREG

            } // end when()
        }) // end observe()

        return binding.root
    } // end onCreateView()

    private fun navigateToWelcomeFragment() {
        // Step6.6: Find the Navigation Controller.
        // Step6.7: Navigate with your action.
        this.findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToWelcomeFragment())
    } // end navigateToWelcomeFragment()

} // end class
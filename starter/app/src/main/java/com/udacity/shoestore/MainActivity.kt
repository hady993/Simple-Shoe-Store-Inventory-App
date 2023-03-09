package com.udacity.shoestore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.ui.*
import com.udacity.shoestore.databinding.ActivityMainBinding
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration : AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Step4.3: Setup DataBindingUtil, so add the binding variable.
        // The binding process here is successful because we add the binding layout to the activity_main.xml.
        // After this step, we can use the DataBindingUtil successfully. Horray!
        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)

        Timber.plant(Timber.DebugTree())

        /**
         * Step12: In MainActivity, setup the nav controller with the toolbar and an AppBarConfiguration!
         * Step12.1: Find the navController from myNavHostFragment to support the up Button.
         * Step12.2: Get the toolbar using the binding concept.
         * Step12.3: Support an action bar.
         * Step12.4: Setup the toolbar with the navController using setupActionBarWithNavController().
         * Note: Struggling so much because of the NavigationUI makes app crashing. --> :(  <--
         */
        val navController = this.findNavController(R.id.myNavHostFragment)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        val bindingToolbar = binding.toolbar
        setSupportActionBar(bindingToolbar)
        this.setupActionBarWithNavController(navController, appBarConfiguration)

        /** Step13.5: Handle the logout process, when you are listening the menu clicked! */
        binding.toolbar.setOnMenuItemClickListener { menuItem ->
            // Look if the clicked item is the logout item.
            when (menuItem.itemId) {
                R.id.logoutItem -> {
                    // Handle favorite icon press, and navigating up to logout.
                    navController.navigateUp()
                    true
                } // end loginFragment
                else -> false
            } // end when()
        } // end OnClickListener

    } // end onCreate()

    // Step6.8: To support the navigation up action.
    override fun onSupportNavigateUp(): Boolean {
        // Step6.9: Find the navController at first!
        val navController = this.findNavController(R.id.myNavHostFragment)
        /** Handle the navigateUp, by disabling it if the fragment haven't any up navigation! */
        return navController.navigateUp(appBarConfiguration)
    } // end onSupportNavigateUp()

} // end class

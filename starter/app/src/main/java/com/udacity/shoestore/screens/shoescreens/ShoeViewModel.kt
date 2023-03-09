package com.udacity.shoestore.screens.shoescreens

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe

/**
 * Step10.2: Create a sub_class of the ViewModel parent class.
 */
class ShoeViewModel : ViewModel() {

    /**
     * This enum will represent the different types of adding_shoe state that can occur.
     * These are the three types of adding_shoe state in the shoe_details page.
     */
    enum class AddingShoeState {
        ADD,
        ERROR,
        CANCEL
    } // end AddingShoeState enum

    // The class attributes!
    /**
     * Step10.4: Use the LiveData.
     * The current encapsulated shoeList, addingShoeState, shoeItem LiveData.
     */
    // Internal Version.
    private val _shoeList = MutableLiveData<MutableList<Shoe>>()
    // External Version.
    val shoeList : LiveData<MutableList<Shoe>>
        get() = _shoeList

    // Internal Version.
    private val _addingShoeState = MutableLiveData<AddingShoeState>()
    // External Version.
    val addingShoeState : LiveData<AddingShoeState>
        get() = _addingShoeState


    // The class methods!
    /**
     * To initialize your class' attributes!
     * Initialize with null, default or guidance values!
     */
    init {
        _shoeList.value = mutableListOf()
        addShoe("Sporty", "43", "Nike", "A shoe for doing sports!")
        addShoe("Workout", "44", "Adidas", "A shoe for doing workout!")
        _addingShoeState.value = AddingShoeState.CANCEL
    } // end init

    /**
     * To add a new shoe!
     */
    fun addShoe(name: String, size: String, company: String, description: String) {
        // To check if the input fields are not empty!
        if (!name.isNullOrEmpty() && !size.isNullOrEmpty() && !company.isNullOrEmpty() && !description.isNullOrEmpty()) {
            // Add new shoe.
            _shoeList.value?.add(Shoe(name, size, company, description))
            // Changing addingShoeState to the adding state if there is no error.
            _addingShoeState.value = AddingShoeState.ADD
        } else {
            _addingShoeState.value = AddingShoeState.ERROR
        } // end if() else
    } // end addShoe()

    /**
     * To make sure that the adding shoe action has completed!
     */
    fun onEventAddShoeComplete() {
        _addingShoeState.value = AddingShoeState.CANCEL
    } // end onEventAddShoeComplete()

} // end class
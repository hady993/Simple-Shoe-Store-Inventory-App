package com.udacity.shoestore.screens.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * Step10.2: Create a sub_class of the ViewModel parent class.
 */
class LoginViewModel : ViewModel() {

    /**
     * This enum will represent the different types of logging state that can occur.
     * These are the three types of logging state in the login page.
     */
    enum class LoggingState {
        LOGIN,
        NULL,
        NOTREG,
        NOPE
    } // end LoggingState enum

    // The class attributes!
    /**
     * Step10.4: Use the LiveData.
     * The current encapsulated email, password, loggingState LiveData.
     */
    // Internal Version.
    private val _email = MutableLiveData<String>()
    // External Version isn't mandatory.

    // Internal Version.
    private val _password = MutableLiveData<String?>()
    // External Version isn't mandatory.

    // Internal Version.
    private val _loggingState = MutableLiveData<LoggingState>()
    // External Version.
    val loggingState : LiveData<LoggingState>
        get() = _loggingState


    // The class methods!
    /**
     * To initialize your class' attributes!
     * Initialize with null or default values!
     */
    init {
        _email.value = ""
        _password.value = ""
        _loggingState.value = LoggingState.NOPE
    } // end init

    /**
     * To do the registration action!
     */
    fun onRegister(emailText: String ?= null, passwordText: String ?= null) {
        // If any field is empty, the registration will be failed!
        if (!emailText.isNullOrEmpty() && !passwordText.isNullOrEmpty()) {
            _email.value = emailText
            _password.value = passwordText
            _loggingState.value = LoggingState.LOGIN
        } else {
            _loggingState.value = LoggingState.NULL
        } // end if() else
    } // end onRegister()

    /**
     * To do the logging action!
     */
    fun onLogin(emailText: String ?= null, passwordText: String ?= null) {
        // If any field is empty or invalid, the logging will be failed!
        if (!emailText.isNullOrEmpty() && !passwordText.isNullOrEmpty()) {
            // To compare the email and the password!
            if (emailText == _email.value && passwordText == _password.value) {
                _loggingState.value = LoggingState.LOGIN
            } else {
                _loggingState.value = LoggingState.NOTREG
            } // end if()
        } else {
            _loggingState.value = LoggingState.NULL
        } // end if() else
    } // end onLogin()

    /**
     * To make sure that the logging action has completed!
     */
    fun onEventLoginComplete() {
        _loggingState.value = LoggingState.NOPE
    } // end onEventLoginComplete()

} // end class
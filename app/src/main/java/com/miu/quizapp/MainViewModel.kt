package com.miu.quizapp

import androidx.lifecycle.ViewModel
class MainViewModel : ViewModel() {
    // Initialize the count variable
  private  var initialCountA = 0
  private  var initialCountB = 0
    // Provided getters for the initial count due to private field
    fun getInitialCountA(): Int {
        return initialCountA
    }
    fun getInitialCountB(): Int {
        return initialCountB
    }
    // Variable to increment the PlayerA Count for each click from the UI, It's customized getter method
    val currentCountA: Int
        get() {
            initialCountA++
            return initialCountA
        }
    // Variable to increment the PlayerB Count for each click from the UI, It's customized getter method
    val currentCountB: Int
        get() {
            initialCountB++
            return initialCountB
        }
}

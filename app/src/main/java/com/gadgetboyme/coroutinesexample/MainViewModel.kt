package com.gadgetboyme.coroutinesexample

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.gadgetboyme.coroutinesexample.models.User
import com.gadgetboyme.coroutinesexample.repository.Repository

class MainViewModel : ViewModel(){

    //This will be used as a trigger to initiate the request
    private val _userId: MutableLiveData<String> = MutableLiveData()

    //"Transformations.switchMap" will observe the "_userId" value. If it changes, then it will trigger whatever is inside the function.
    //In this case: Repository.getUser(userId), which returns a User object, as represented with the "LiveData<User>" declaration
    val user : LiveData<User> = Transformations
        .switchMap(_userId){ userId ->
            Repository.getUser(userId)
        }

    //Create a method to set a userId
    fun setUserId(userId : String){
        val update = userId
        //If the current value is different to the passed in value, update the value.
        //If not, return nothing
        //This will help act as a trigger when the value has changed.
        if(_userId.value == update){
            return
        }
        _userId.value = update
    }

    fun cancelJobs(){
        Repository.cancelJobs()
    }

}
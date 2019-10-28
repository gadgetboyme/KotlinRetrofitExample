package com.gadgetboyme.coroutinesexample

import com.gadgetboyme.coroutinesexample.models.User

object ExampleSingleton {

    //Lazy initializer: Only initialize once, and only when it's called the first time.
    //It is null until it is called upon
    val singletonUser : User by lazy{
        User("mullally.adrian@gmail.com", "aido", "image.png" )
    }

}
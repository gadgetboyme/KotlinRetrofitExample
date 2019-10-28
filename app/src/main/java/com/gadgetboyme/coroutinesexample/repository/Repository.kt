package com.gadgetboyme.coroutinesexample.repository

import androidx.lifecycle.LiveData
import com.gadgetboyme.coroutinesexample.api.RetrofitBuilder
import com.gadgetboyme.coroutinesexample.models.User
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main

object Repository{

    var job: CompletableJob? = null

    fun getUser(userId: String): LiveData<User>{
        //Initialise the job
        job = Job()
        return object: LiveData<User>(){
            override fun onActive() {
                super.onActive()
                //If the job is not null, run the code:
                //Give the job a name: theJob.
                //This gives the lambda a name, instead of using "it"
                job?.let{theJob ->
                    //Create a coroutine scope, reference the IO dispatcher and add the job to it. This creates a unique coroutine on a background thread that references "theJob"
                    CoroutineScope(IO + theJob).launch {
                        //get the user from the API
                        val user = RetrofitBuilder.apiService.getUser(userId)

                        //Set the live data value.
                        //You cannot set a LiveData value on a background thread. This must be done from the Main thread
                        //"withContext(Main)" switches over to the main thread, to perform the LiveData update
                        withContext(Main){
                            value = user
                            //Mark the job as complete
                            theJob.complete()
                        }
                    }
                }
            }
        }
    }

    //This would cancel "theJob" if it is called
    fun cancelJobs(){
        job?.cancel()
    }
}
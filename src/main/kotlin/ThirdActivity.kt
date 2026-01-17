package org.example

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

fun main(){

    // so in general coroutines are always started in a specific context
    // and the context will describe in which thread or a coroutine will be started in

    //Coroutine Context tells a coroutine where and how it runs.
    //1️⃣ Dispatcher – Which thread to run on
    //Dispatchers.Main       -> UI Operations (Android)
    //Dispatchers.IO         -> Network, disk, database
    //Dispatchers.Default    -> CPU-heavy work, complex Operations
    //Dispatchers.Unconfined -> Not recommended,

   val job: Job = GlobalScope.launch(Dispatchers.IO) {
       println(Thread.currentThread().name)

        // we shouldn't request network in main thread, but we can only change in the main thread
        // so will start coroutine in IO

        val result = doNetworkCall3()

        // change now the thread to main
        withContext(Dispatchers.Default){
            println(Thread.currentThread().name)
            println(result)

        }

    }

    //if we but delay inside them
    // run blocking will block the main thread
    // global scope wont

    //What runBlocking does
    //👉 It blocks the current thread
    //👉 It waits until all coroutines inside finish
    //👉 It is mainly used in main() or tests
    runBlocking {
        job.join()
    }


}

suspend fun doNetworkCall3(): String{

    delay(3000)
    return ("This is the Answer")
}

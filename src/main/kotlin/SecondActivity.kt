package org.example

import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

fun main()= runBlocking{

    val job = async {


        //👉 Starts immediately
        //👉 Runs in background
        //👉 Does not wait

        //Little Summary
        //Your code is concurrent because both async {} blocks start first.
        //async starts work immediately.
        //await() does NOT start work — it only waits for results.
        //Since both network calls start before any await(), they run at the same time.
        //Total time ≈ 3 seconds, not 6.
        //👉 Rule to remember:
        //Start with async, wait later with await = concurrent ✔

        // Deferred<T> is a special type of coroutine job that produces a result of type T
        //Think of it like a future promise:
        //“I will give you a String when I’m done, but maybe not immediately.”

        // concurrent
        val time = measureTimeMillis {
            val networkCall1: Deferred<String> = async {  doNetworkCall()}
            val networkCall2: Deferred<String>  = async { doNetworkCall2()}

            val call1 = networkCall1.await()
            println(call1)


            val call2 = networkCall2.await()
            println(call2)
        }


        // sequential
        val time2 = measureTimeMillis {
            val networkCall1 = doNetworkCall()
            val networkCall2 =  doNetworkCall2()

            println(networkCall1)


            println(networkCall2)
        }

        println(time)
        println(time2)

    }


       // join() and await() behave the same here.
        job.join() //join() waits for completion only
       // job.await() //await() waits AND returns the result

}



suspend fun doNetworkCall(): String{
    delay(3000)
    return ("This is the Answer 1")
}


suspend fun doNetworkCall2(): String{
    delay(3000)
    return ("This is the Answer 2")
}
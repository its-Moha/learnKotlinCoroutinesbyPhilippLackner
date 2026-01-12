package org.example

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {

    // a function is a sequence of instructions that takes inputs and gives us outputs
    // a thread describes in which context the sequence of instructions should be executed
    // when you launch the app it executes on the main thread

    // Coroutines can be paused and resumed without stopping the whole program.

    //Coroutines are lightweight threads —
    // they let you run multiple small tasks without slowing down your app.
    // They don’t block the program while waiting for long operations to finish.

    // coroutines a job to execute some instructions
    // you can execute many coroutines inside a single thread
    // Coroutines executes inside that thread
    // coroutines are suspendable


    // our first coroutine

    //GlobalScope lives forever
    //It is not tied to your program lifecycle
    //Can cause memory leaks

    // delay will only pause the current coroutine
    // and will not block the whole thread like
    // thread.sleep does


    val job: Job = GlobalScope.launch {


       delay(3000)
        println("coroutine says hello from thread: ${Thread.currentThread().name}")
    }

    println("hello from thread: ${Thread.currentThread().name}")


    // Because main() finishes before the coroutine gets a chance to run.

     // we will use
    // runBlocking keeps the program alive
    // The coroutine gets time to run
    // join() waits until the coroutine finishes

    runBlocking {
        job.join()
    }

    //Key takeaway
    //launch does NOT block the thread
    //If main() finishes, coroutines are canceled



}

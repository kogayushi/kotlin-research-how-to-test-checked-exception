package org.koin.sample

import java.io.IOException

/**
 * Hello Service - interface
 */
interface HelloService {
    @Throws(IOException::class)
    fun hello(): String
}


// service class with injected helloModel instance
/**
 * Hello Service Impl
 * Will use HelloMessageData data
 */
class HelloServiceImpl(private val helloMessageData: HelloMessageData) : HelloService {

    override fun hello() = "Hey, ${helloMessageData.message}"
}

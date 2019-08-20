package org.koin.sample

import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.test.AutoCloseKoinTest
import org.koin.test.inject
import org.koin.test.mock.declareMock
import org.mockito.BDDMockito.given
import java.io.IOException

class HelloAppTest : AutoCloseKoinTest() {

    val model by inject<HelloMessageData>()
    val service by inject<HelloService>()

    @Before
    fun before() {
        startKoin {
            modules(helloModule)
        }
    }

    @Test
    fun tesKoinComponents() {
        val helloApp = HelloApplication()
        helloApp.sayHello()

        assertEquals(service, helloApp.helloService)
        assertEquals("Hey, ${model.message}", service.hello())
    }

    @Test(expected = IOException::class)
    fun throwException() {
        declareMock<HelloService> {
            given(hello()).willThrow(IOException("Something Went Wrong"))
        }
        service.hello()
    }
}

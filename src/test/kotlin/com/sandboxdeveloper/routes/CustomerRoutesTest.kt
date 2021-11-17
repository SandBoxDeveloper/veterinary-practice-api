package com.sandboxdeveloper.routes

import com.sandboxdeveloper.module
import io.ktor.http.*
import io.ktor.server.testing.*
import org.junit.Test
import kotlin.test.assertEquals

class CustomerRoutesTest {

    @Test
    fun testGetSpecificCustomer() {
        withTestApplication({ module() }) {
            handleRequest(HttpMethod.Get,"/customers/{name}").apply {
                assertEquals(
                    HttpStatusCode.OK,
                    response.status()
                )
            }
        }
    }

    @Test
    fun testGetAllCustomers() {
        withTestApplication({ module() }) {
            handleRequest(HttpMethod.Get,"/customers").apply {
                assertEquals(
                    HttpStatusCode.OK,
                    response.status()
                )
            }
        }
    }

    @Test
    fun testDeleteSpecificCustomer() {
        withTestApplication({ module() }) {
            handleRequest(HttpMethod.Delete,"/customers/{id}").apply {
                assertEquals(
                    HttpStatusCode.NotFound,
                    response.status()
                )
            }
        }
    }
}
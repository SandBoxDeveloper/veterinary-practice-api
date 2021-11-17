package com.sandboxdeveloper.routes

import com.sandboxdeveloper.module
import io.ktor.http.*
import io.ktor.server.testing.*
import org.junit.Test
import kotlin.test.assertEquals

class PetRoutesTest {

    @Test
    fun testGetSpecificPet() {
        withTestApplication({ module() }) {
            handleRequest(HttpMethod.Get,"/pets/{name}").apply {
                assertEquals(
                    HttpStatusCode.OK,
                    response.status()
                )
            }
        }
    }

    @Test
    fun testGetAllPets() {
        withTestApplication({ module() }) {
            handleRequest(HttpMethod.Get,"/pets").apply {
                assertEquals(
                    HttpStatusCode.OK,
                    response.status()
                )
            }
        }
    }

    @Test
    fun testDeleteSpecificPet() {
        withTestApplication({ module() }) {
            handleRequest(HttpMethod.Delete,"/pets/{id}").apply {
                assertEquals(
                    HttpStatusCode.NotFound,
                    response.status()
                )
            }
        }
    }

}
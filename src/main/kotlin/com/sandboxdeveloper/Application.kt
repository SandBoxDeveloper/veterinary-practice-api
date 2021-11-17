package com.sandboxdeveloper

import com.sandboxdeveloper.plugins.configureMonitoring
import com.sandboxdeveloper.plugins.configureRouting
import com.sandboxdeveloper.plugins.configureSerialization
import com.sandboxdeveloper.routes.registerCustomersRoute
import com.sandboxdeveloper.routes.registerPetsRoute
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.http.*

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // application.conf references the main function. This annotation prevents the IDE from marking it as unused.
fun Application.module() {
    install(CORS) {
        host("*")
        header(HttpHeaders.ContentType)
    }
    configureRouting()
    configureSerialization()
    configureMonitoring()
    registerCustomersRoute()
    registerPetsRoute()
}

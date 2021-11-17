package com.sandboxdeveloper.routes

import com.sandboxdeveloper.model.Customer
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*

private val customers = mutableListOf<Customer>(Customer("1", "Johnny"))

fun Application.registerCustomersRoute() {
    routing {
        route("/customers") {
            addCustomerRoute()
            getCustomerRoute()
            getCustomersRoute()
            deleteCustomerRoute()
        }
    }
}

fun Route.addCustomerRoute() {
    post {
        val customer = call.receive<Customer>()
        customers.add(customer)
        call.respond(HttpStatusCode.Created, "Customer saved")
    }
}

fun Route.getCustomerRoute() {
    get("{name}") {
        val name = call.parameters["name"]
        val customer = customers.find { it.name == name }
        customer?.let {
            call.respond(HttpStatusCode.Found, it)
        } ?: call.respond(HttpStatusCode.NotFound, "No customer found with name $name")
    }
}

fun Route.getCustomersRoute() {
    get {
        if (customers.isNotEmpty()) {
            call.respond(HttpStatusCode.OK, customers)
        } else {
            call.respond(HttpStatusCode.NotFound, "No customer found")
        }
    }
}

fun Route.deleteCustomerRoute() {
    delete("{id}") {
        val id = call.parameters["id"] ?: return@delete call.respond(
            HttpStatusCode.BadRequest,
            "Customer id required"
        )
        if (customers.removeIf { it.id == id }) {
            call.respond(HttpStatusCode.Accepted, "Customer removed successfully")
        } else {
            call.respond(HttpStatusCode.NotFound, "No customer found with id $id")
        }
    }
}
package com.sandboxdeveloper.routes

import com.sandboxdeveloper.model.Pet
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*

private val pets = mutableListOf<Pet>(
    Pet("1", "Albert", "Rabbit", "1"),
    Pet("2", "Phill", "Rabbit", "2"),
    Pet("3", "Storm", "Rabbit", "3")
)

fun Application.registerPetsRoute() {
    routing {
        route("/pets") {
            addPetRoute()
            getPetRoute()
            getPetsRoute()
            deletePetRoute()
        }
    }
}

fun Route.addPetRoute() {
    post {
        val pet = call.receive<Pet>()
        pets.add(pet)
        call.respond(HttpStatusCode.Created, "Pet saved")
    }
}

fun Route.getPetRoute() {
    get("{name}") {
        val name = call.parameters["name"]
        val pet = pets.find { it.name == name }
        pet?.let {
            call.respond(HttpStatusCode.Found, it)
        } ?: call.respond(HttpStatusCode.NotFound, "No pet found with name $name")

    }
}

fun Route.getPetsRoute() {
    get {
        if (pets.isNotEmpty()) {
            call.respond(HttpStatusCode.OK, pets)
        } else {
            call.respond(HttpStatusCode.NotFound, "No pet found")
        }
    }
}

fun Route.deletePetRoute() {
    delete("{id}") {
        val id = call.parameters["id"] ?: return@delete call.respond(
            HttpStatusCode.BadRequest,
            "Pet id required"
        )
        if (pets.removeIf { it.id == id }) {
            call.respond(HttpStatusCode.Accepted, "Pet removed successfully")
        } else {
            call.respond(HttpStatusCode.NotFound, "No pet found with id $id")
        }
    }
}
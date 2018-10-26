package main.kotlin.vuekot.routes

import com.github.salomonbrys.kotson.jsonArray
import com.github.salomonbrys.kotson.jsonObject
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import io.ktor.application.ApplicationCall
import io.ktor.http.HttpStatusCode
import io.ktor.response.respond
import java.io.InputStreamReader

abstract class BaseRoute {

    companion object {
        val gson = Gson()
        val jsonParser = JsonParser()
    }

    suspend fun <T> ApplicationCall.respondWithJson(obj: T?, statusCode: HttpStatusCode = HttpStatusCode.OK, mapper: (T) -> JsonObject) =
            if (obj == null) respond(HttpStatusCode.NotFound) else respond(statusCode, gson.toJson(mapper(obj)))

    suspend fun <T> ApplicationCall.respondWithJsonResults(objects: List<T>, statusCode: HttpStatusCode = HttpStatusCode.OK, mapper: (T) -> JsonObject) {
        val jsonResults = objects.map { mapper(it) }
        val jsonResponse = jsonArray(jsonResults)
        respond(statusCode, gson.toJson(jsonResponse))
    }

    suspend fun ApplicationCall.respondWithJsonId(id: String?, statusCode: HttpStatusCode = HttpStatusCode.OK) =
            if(id == null) respond(HttpStatusCode.NotFound) else respond(statusCode, gson.toJson(jsonObject("id" to id)))

    suspend fun ApplicationCall.receiveJson() = jsonParser.parse(InputStreamReader(request.receiveContent().inputStream())) as JsonObject

    fun allDefined(vararg obj: Any?): Boolean {
        obj.forEach { if(it == null) return false }
        return true
    }

}
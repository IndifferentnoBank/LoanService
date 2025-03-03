package ru.bezdar.bank.app.common.extentions

import io.ktor.server.application.ApplicationCall
import io.ktor.server.resources.delete
import io.ktor.server.resources.get
import io.ktor.server.resources.patch
import io.ktor.server.resources.post
import io.ktor.server.resources.put
import io.ktor.server.routing.Route
import io.ktor.server.routing.route
import io.ktor.util.pipeline.PipelineContext
import ru.bezdar.bank.app.common.ApiVersion

inline fun <reified T : Any> Route.postWithVersion(
    apiVersion: ApiVersion,
    noinline body: suspend PipelineContext<Unit, ApplicationCall>.(T) -> Unit,
) {
    route(apiVersion.url) { post(body) }
}

inline fun <reified T : Any> Route.getWithVersion(
    apiVersion: ApiVersion,
    noinline body: suspend PipelineContext<Unit, ApplicationCall>.(T) -> Unit,
) {
    route(apiVersion.url) { get(body) }
}

inline fun <reified T : Any> Route.deleteWithVersion(
    apiVersion: ApiVersion,
    noinline body: suspend PipelineContext<Unit, ApplicationCall>.(T) -> Unit,
) {
    route(apiVersion.url) { delete(body) }
}

inline fun <reified T : Any> Route.patchWithVersion(
    apiVersion: ApiVersion,
    noinline body: suspend PipelineContext<Unit, ApplicationCall>.(T) -> Unit,
) {
    route(apiVersion.url) { patch(body) }
}

inline fun <reified T : Any> Route.putWithVersion(
    apiVersion: ApiVersion,
    noinline body: suspend PipelineContext<Unit, ApplicationCall>.(T) -> Unit,
) {
    route(apiVersion.url) { put(body) }
}

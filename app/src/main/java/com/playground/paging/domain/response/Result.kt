package com.playground.paging.domain.response

import java.io.Serializable

data class Result(
    val created: String,
    val episode: List<String>,
    val gender: String,
    val id: Int,
    val image: String,
    val location: Location,
    val name: String,
    val origin: Origin,
    val species: String,
    val status: String,
    val type: String,
    val url: String
) : PageItem, Serializable
{
    override fun getId(): String {
        return id.toString()
    }

}

interface PageItem {
    fun getId(): String
}

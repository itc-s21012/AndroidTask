package jp.ac.it_college.std.s21012.androidtask.service

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface PokemonService {
    @GET("https://pokeapi.co/api/v2/")
    suspend fun getPokemonInfo(
        @Path("pokeId") pokeId: Int
    ): Response<PokemonResponse>
}
data class PokemonResponse(
    val pokemon: List<Poke>
)

data class Poke(
    val id: Int,
    val name: String
)
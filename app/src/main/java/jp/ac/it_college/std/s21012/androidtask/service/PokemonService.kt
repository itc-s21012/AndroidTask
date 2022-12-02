package jp.ac.it_college.std.s21012.androidtask.service

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface PokemonService {
    interface PokemonService {
        @GET("https://pokeapi.co/api/v2/{id}")
        fun getPokemon(
            @Path("id") id: Int
        ): Call<PokemonInfo>
    }
}
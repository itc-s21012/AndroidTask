package jp.ac.it_college.std.s21012.androidtask.service

import jp.ac.it_college.std.s21012.androidtask.json.PokemonGazou
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface PokemonService {
    @GET("https://pokeapi.co/api/v2/{id}")
    fun getPokemon(
        @Path("id") id: Int
        ): Call<PokemonGazou>
    }

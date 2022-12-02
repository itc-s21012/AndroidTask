package jp.ac.it_college.std.s21012.androidtask.json

data class PokemonJson (
    val pokemon: List<Pokemon>
)

data class Pokemon(
    val id: Int,
    val name: String
)

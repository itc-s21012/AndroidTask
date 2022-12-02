package jp.ac.it_college.std.s21012.androidtask

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.UiThread
import androidx.annotation.WorkerThread
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.squareup.picasso.Picasso
import jp.ac.it_college.std.s21012.androidtask.databinding.FragmentQuizBinding
import jp.ac.it_college.std.s21012.androidtask.json.Pokedex
import jp.ac.it_college.std.s21012.androidtask.json.PokedexJson
import jp.ac.it_college.std.s21012.androidtask.json.Pokemon
import jp.ac.it_college.std.s21012.androidtask.json.PokemonGazou
import jp.ac.it_college.std.s21012.androidtask.service.PokemonService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import kotlin.collections.EmptySet.forEach

class QuizFragment : Fragment() {
    private var _binding: FragmentQuizBinding? = null
    private val binding get() = _binding!!
    private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
    private val args: QuizFragmentArgs by navArgs()
    private val BASE_URL = "https://pokeapi.co/api/v2/"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentQuizBinding.inflate(inflater, container, false)
        binding.gen.text = getString(R.string.gen_select, args.num)
        fun gens() {
            PokemonGazou.forEach { g ->
                g.entries.map { e -> e.pokemon_id }.toIntArray()

                args.num
            }
        }

        return binding.root

    }



    @UiThread
    private fun showPokeImg(id: Int) {
        lifecycleScope.launch {
            val info = getPokemonImg(id)
            setPokemonInfo(info)
        }
    }

    @WorkerThread
    private suspend fun getPokemonImg(id: Int): PokemonGazou {
        return withContext(Dispatchers.IO) {
            val retrofit = Retrofit.Builder().apply {
                baseUrl(BASE_URL)
                addConverterFactory(MoshiConverterFactory.create(moshi))
            }.build()

            val service: PokemonService = retrofit.create(PokemonService::class.java)
            try {
                service.(id).execute().body()
                    ?: throw IllegalStateException("ポケモンの情報が取れません")
            } catch (e: Exception) {
                throw IllegalStateException("なにか例外が発生しました。", e)
            }
        }
    }


    @UiThread
    private fun setPokemonInfo(info: PokemonGazou) {
        val IMG_URL = info.sprites.other.officialArtwork.frontDefault
        Picasso.get().load(IMG_URL).into(binding.viPokemon)
        binding.viPokemon.setColorFilter(Color.rgb(0, 0, 0))
    }
}
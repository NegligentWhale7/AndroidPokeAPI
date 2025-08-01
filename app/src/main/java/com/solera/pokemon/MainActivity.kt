package com.solera.pokemon

import android.os.Bundle
import android.widget.SearchView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.solera.pokemon.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: Adapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var offsetVariable = 0
        getCharacters(offsetVariable)

        binding.btAfter.setOnClickListener{
            offsetVariable += 10
            if(offsetVariable >= 1292) offsetVariable = 1292
            getCharacters(offsetVariable)
        }

        binding.btBefore.setOnClickListener{
            offsetVariable -= 10
            if(offsetVariable <= 0) offsetVariable = 0
            getCharacters(offsetVariable)
        }
    }

    private fun getCharacters(offsetVariable: Int){
        RetrofitClient.service.getCharacters(offset = offsetVariable).enqueue(object: Callback<CharacterResponse> {
            override fun onResponse(
                call: Call<CharacterResponse>,
                response: Response<CharacterResponse>
            ) {
                if (response.isSuccessful){
                    val characters = response.body()?.results ?: emptyList()
                    adapter = Adapter(characters)
                    binding.rvPokemon.layoutManager = LinearLayoutManager(this@MainActivity)
                    binding.rvPokemon.adapter = adapter
                    setupSearchView()
                }
            }

            override fun onFailure(call: Call<CharacterResponse>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Error al conectar", Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun setupSearchView(){
        binding.searchBar.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean = false

            override fun onQueryTextChange(newText: String?): Boolean {
                adapter.filter(newText ?: "")
                return true
            }
        })
    }
}
package com.github.rexfilius.rickandmortycharacter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.rexfilius.rickandmortycharacter.api.Results
import com.github.rexfilius.rickandmortycharacter.api.CharacterApi
import com.github.rexfilius.rickandmortycharacter.api.CharacterRepository
import com.github.rexfilius.rickandmortycharacter.databinding.MainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: MainBinding
    private val character = mutableListOf<Results>()

    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(
            this, MainViewModelFactory(
                CharacterRepository(
                    CharacterApi.apiService
                )
            )
        ).get(MainViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.characterLiveData.observe(this, {
            character.addAll(it)
        })

        val mainAdapter = MainAdapter(character)
        binding.mainRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.mainRecyclerView.adapter = mainAdapter
    }
}
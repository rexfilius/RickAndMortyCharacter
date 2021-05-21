package com.github.rexfilius.rickandmortycharacter.characters

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.rexfilius.rickandmortycharacter.api.Results
import com.github.rexfilius.rickandmortycharacter.api.CharacterApi
import com.github.rexfilius.rickandmortycharacter.api.CharacterRepository
import com.github.rexfilius.rickandmortycharacter.databinding.CharacterBinding

class CharacterActivity : AppCompatActivity() {

    lateinit var binding: CharacterBinding
    private val character = mutableListOf<Results>()

    private val viewModel: CharacterViewModel by lazy {
        ViewModelProvider(
            this, CharacterViewModelFactory(
                CharacterRepository(
                    CharacterApi.apiService
                )
            )
        ).get(CharacterViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = CharacterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val characterAdapter = CharacterAdapter(character)
        binding.characterRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.characterRecyclerView.adapter = characterAdapter

        viewModel.characterLiveData.observe(this, {
            character.addAll(it)
            characterAdapter.notifyDataSetChanged()
        })
    }

}

package com.github.rexfilius.rickandmortycharacter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.github.rexfilius.rickandmortycharacter.api.Results
import com.github.rexfilius.rickandmortycharacter.databinding.MainItemBinding

class MainAdapter(private val characterList: List<Results>) :
    RecyclerView.Adapter<MainAdapter.CharacterViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        return CharacterViewHolder(
            MainItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) =
        holder.bind(characterList[position])

    override fun getItemCount(): Int = characterList.size

    inner class CharacterViewHolder(private val binding: MainItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(character: Results) {
            binding.mainItemImage.load(character.characterImage)
            binding.mainItemName.text = character.characterName
            binding.mainItemStatus.text = character.characterStatus
            binding.mainItemSpecies.text = character.characterSpecies
        }

    }

}
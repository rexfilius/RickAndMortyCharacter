package com.github.rexfilius.rickandmortycharacter.characters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.github.rexfilius.rickandmortycharacter.R
import com.github.rexfilius.rickandmortycharacter.api.Results
import com.github.rexfilius.rickandmortycharacter.databinding.CharacterItemBinding

class CharacterAdapter(private val characterList: List<Results>) :
    RecyclerView.Adapter<CharacterAdapter.CharacterViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        return CharacterViewHolder(
            CharacterItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) =
        holder.bind(characterList[position])

    override fun getItemCount(): Int = characterList.size

    inner class CharacterViewHolder(private val binding: CharacterItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(character: Results) {
            binding.characterItemImage.load(character.characterImage)

            binding.characterItemName.text =
                binding.root.context.getString(R.string.item_name, character.characterName)

            binding.characterItemStatus.text =
                binding.root.context.getString(R.string.item_status, character.characterStatus)

            binding.characterItemSpecies.text =
                binding.root.context.getString(R.string.item_species, character.characterSpecies)
        }

    }

}
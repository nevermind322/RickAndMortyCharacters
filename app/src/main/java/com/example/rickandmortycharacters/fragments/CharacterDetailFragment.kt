package com.example.rickandmortycharacters.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.fragment.navArgs
import coil.load
import com.example.rickandmortycharacters.R
import com.example.rickandmortycharacters.databinding.FragmentDetailBinding


class CharacterDetailFragment : Fragment() {

    private lateinit var binding: FragmentDetailBinding

    private val args by navArgs<CharacterDetailFragmentArgs>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View =  FragmentDetailBinding.inflate(inflater, container, false).root


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDetailBinding.bind(view)
        with(binding){
            detailGenderValue.text=args.clickedCharacter.gender
            detailSpeciesValue.text = args.clickedCharacter.species
            detailStatusValue.text = args.clickedCharacter.status
            detailName.text = args.clickedCharacter.name
            detailTypeValue.text = args.clickedCharacter.type
            detailImage.load(args.clickedCharacter.image)
        }



    }

    companion object {
        @JvmStatic
        fun newInstance() = CharacterDetailFragment()
    }
}
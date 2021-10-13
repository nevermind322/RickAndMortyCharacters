package com.example.rickandmortycharacters.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import coil.load
import com.example.rickandmortycharacters.R
import com.example.rickandmortycharacters.viewmodels.CharactersPageViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


class CharacterDetailFragment : Fragment() {

    private val mViewModel by sharedViewModel<CharactersPageViewModel>()

    val clickedCharacter by lazy { CharacterDetailFragmentArgs.fromBundle(requireArguments()).clickedCharacter }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =  inflater.inflate(R.layout.fragment_detail, container, false)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.apply {
            findViewById<TextView>(R.id.detail_sex_value).text = clickedCharacter.gender
            findViewById<TextView>(R.id.detail_type_value).text = clickedCharacter.type
            findViewById<TextView>(R.id.detail_species_value).text = clickedCharacter.species
            findViewById<TextView>(R.id.detail_status_value).text = clickedCharacter.status
            findViewById<TextView>(R.id.detail_name).text = clickedCharacter.name
            findViewById<ImageView>(R.id.detail_image).load( clickedCharacter.image)
        }


    }

    companion object {
        @JvmStatic
        fun newInstance() = CharacterDetailFragment()
    }
}
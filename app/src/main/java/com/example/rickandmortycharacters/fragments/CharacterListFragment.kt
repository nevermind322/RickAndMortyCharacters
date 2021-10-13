package com.example.rickandmortycharacters.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmortycharacters.R
import com.example.rickandmortycharacters.adapters.CharacterAdapter
import com.example.rickandmortycharacters.adapters.CharacterAdapterComparator
import com.example.rickandmortycharacters.models.CharacterInfo
import com.example.rickandmortycharacters.viewmodels.CharactersPageViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


class CharacterListFragment : Fragment(),
    CharacterAdapter.OnCharacterClickListener {


    private val listViewModel by sharedViewModel<CharactersPageViewModel>()

    private val characterAdapterComparator: CharacterAdapterComparator by inject()

    private val adapter = CharacterAdapter(this, characterAdapterComparator)


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_list, container, false)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView: RecyclerView = view.findViewById(R.id.list_characters)
        recyclerView.adapter = adapter

        lifecycleScope.launch {
            listViewModel.flow.collectLatest { adapter.submitData(it) }
        }


        adapter.addLoadStateListener {
            if (it.source.refresh is LoadState.Error || it.source.append is LoadState.Error || it.source.prepend is LoadState.Error) {
                Toast.makeText(
                    this@CharacterListFragment.context,
                    "Network error",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = CharacterListFragment()
    }

    override fun onCharacterClick(characterInfo: CharacterInfo) {
        val direction =
            CharacterListFragmentDirections.actionCharacterListFragmentToCharacterDetailFragment(
                characterInfo
            )
        findNavController().navigate(direction)
    }
}
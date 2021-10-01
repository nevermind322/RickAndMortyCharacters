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


/**
 * A simple [Fragment] subclass.
 * Use the [CharacterListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CharacterListFragment : Fragment(),
    CharacterAdapter.OnCharacterClickListener {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private val listViewModel by sharedViewModel<CharactersPageViewModel>()

    private val characterAdapterComparator : CharacterAdapterComparator by inject()

    private val adapter = CharacterAdapter(this, characterAdapterComparator)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView: RecyclerView = view.findViewById(R.id.list_characters)
        recyclerView.adapter = adapter

        lifecycleScope.launch {
            listViewModel.flow.collectLatest { adapter.submitData(it) }
        }


        adapter.addLoadStateListener {
            if (it.source.refresh is LoadState.Error || it.source.append is LoadState.Error || it.source.prepend is LoadState.Error) {
                Toast.makeText(this@CharacterListFragment.context, "Network error", Toast.LENGTH_LONG).show()
            }
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment CharacterListFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            CharacterListFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
        // TODO: Rename parameter arguments, choose names that match
        // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
        private const val ARG_PARAM1 = "param1"
        private const val ARG_PARAM2 = "param2"
    }

    override fun onCharacterClick(characterInfo: CharacterInfo) {
        listViewModel.clickedCharacter = characterInfo
        Log.e("list", listViewModel.toString())
        activity?.supportFragmentManager?.commit {

            setReorderingAllowed(true)
            replace<CharacterDetailFragment>(R.id.fragment_container)
            addToBackStack(null)

        }
    }
}
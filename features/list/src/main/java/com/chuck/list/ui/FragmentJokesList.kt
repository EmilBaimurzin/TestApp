package com.chuck.list.ui

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.chuck.core.ViewBindingFragment
import com.chuck.list.databinding.FragmentJokesListBinding
import com.chuck.list.domain.JokesAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FragmentJokesList :
    ViewBindingFragment<FragmentJokesListBinding>(FragmentJokesListBinding::inflate) {
    private val viewModel: JokesListViewModel by viewModels()
    private lateinit var jokesAdapter: JokesAdapter
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()

        binding.buttonJoke.setOnClickListener {
            viewModel.getJoke()
        }

        viewModel.list.observe(viewLifecycleOwner) {
            jokesAdapter.items = it.toMutableList()
            jokesAdapter.notifyDataSetChanged()
        }

        viewModel.isLoading.observe(viewLifecycleOwner) {
            binding.buttonJoke.isEnabled = !it
            binding.progressBar.isVisible = it
            binding.jokeText.isVisible = !it
        }
    }

    private fun initAdapter() {
        jokesAdapter = JokesAdapter { joke ->
            val bundle = Bundle()
            bundle.putString("JOKE", joke.joke)
            bundle.putString("IMG", joke.img)
            viewModel.navigateToDetails(findNavController(), bundle)
        }
        with(binding.jokesRV) {
            adapter = jokesAdapter
            layoutManager = LinearLayoutManager(requireContext()).apply {
                orientation = LinearLayoutManager.VERTICAL
            }
            setHasFixedSize(true)
        }
    }
}
package com.hamza.newsapp.ui.Fragments.FaouriteFragment

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.hamza.newsapp.R
import com.hamza.newsapp.data.Model.Article
import com.hamza.newsapp.databinding.FragmentFavArticlesBinding
import com.hamza.newsapp.util.show
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class FavArticlesFragment : Fragment() {

    private var _binding: FragmentFavArticlesBinding? = null
    private val binding get() = _binding!!

    private val viewModel: FavouriteArticlesViewModel by viewModels()
    private lateinit var favList: MutableList<Article>

    @Inject
    lateinit var favAdapter: FavArticlesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentFavArticlesBinding.inflate(layoutInflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)

        favList = mutableListOf()
        setUpRecyclerView()
        observeToFavLiveData()
    }

    private fun observeToFavLiveData() {

        viewModel.getFavNews()?.observe(viewLifecycleOwner) { articles ->
            favAdapter.differ.submitList(articles?.reversed())
        }

        if (viewModel.getFavNews()?.value == null)
            binding.notFoundlayout.show()

    }

    private fun setUpRecyclerView() {
        binding.favRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            binding.favRecyclerView.adapter = favAdapter
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.fav_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {

            R.id.deleteAllArticles -> {
                viewModel.deleteAllFavArticles()
                Toast.makeText(context, "All Favourite articles deleted !", Toast.LENGTH_SHORT)
                    .show()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}


package com.hamza.newsapp.ui.Fragments.FaouriteFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.hamza.newsapp.data.Model.Article
import com.hamza.newsapp.databinding.FragmentFavArticlesBinding
import com.hamza.newsapp.util.gone


class FavArticlesFragment : Fragment() {

    private var _binding: FragmentFavArticlesBinding? = null
    private val binding get() = _binding!!

    private val viewModel: FavouriteArticlesViewModel by viewModels()
    private lateinit var favList: MutableList<Article>

    private lateinit var favAdapter: FavArticlesAdapter

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
        viewModel.getFavNews()?.observe(viewLifecycleOwner, { articles ->

            binding.ProgressBarWishList.gone()
            val filterList = articles
            favAdapter.differ.submitList(filterList?.reversed())
        })
    }


    private fun setUpRecyclerView() {
        binding.favRecyclerView.apply {
            favAdapter = FavArticlesAdapter()
            layoutManager = LinearLayoutManager(context)
            binding.favRecyclerView.adapter = favAdapter
        }
    }
}
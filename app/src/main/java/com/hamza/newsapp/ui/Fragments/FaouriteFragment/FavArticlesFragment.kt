package com.hamza.newsapp.ui.Fragments.FaouriteFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.hamza.newsapp.data.Model.Article
import com.hamza.newsapp.databinding.FragmentFavArticlesBinding


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
        viewModel.getAllArticles()
        setUpRecyclerView()
        observeToFavLiveData()


    }

    private fun observeToFavLiveData() {
        viewModel.getAllArticles().observe(viewLifecycleOwner, Observer { articles ->

            val filterList = articles.data
            favAdapter.differ.submitList(filterList?.reversed())

            filterList?.let { favList.addAll(it?.reversed()) }

            Toast.makeText(context, "hey ${filterList?.get(0)?.author}", Toast.LENGTH_SHORT).show()

        })


        /*
//      list.observe(this, Observer {
//          it?.let {
//              favAdapter.differ.submitList(it)
//          }
//      })*/
    }


    private fun setUpRecyclerView() {
        binding.favRecyclerView.apply {
            favAdapter = FavArticlesAdapter()
            layoutManager = LinearLayoutManager(context)
            binding.favRecyclerView.adapter = favAdapter
        }
    }
}
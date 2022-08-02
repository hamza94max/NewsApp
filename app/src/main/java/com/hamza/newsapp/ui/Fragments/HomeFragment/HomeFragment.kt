package com.hamza.newsapp.ui.Fragments.HomeFragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.faltenreich.skeletonlayout.Skeleton
import com.hamza.newsapp.R
import com.hamza.newsapp.data.RemoteData.RetrofitInstance
import com.hamza.newsapp.databinding.FragmentHomeBinding
import retrofit2.HttpException
import java.io.IOException


class HomeFragment : Fragment() {

    private val TAG = "HomeFragment"

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var newsAdapter: HomeAdapter
    private lateinit var skeleton: Skeleton

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setHasOptionsMenu(true)
        _binding = FragmentHomeBinding.inflate(layoutInflater, container, false)

        skeleton = binding.skeleton

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launchWhenCreated {
            skeleton.showSkeleton()
            handleBreakingNewsResponse()
            skeleton.showOriginal()
        }

        setUpRecyclerView()

        newsAdapter.setOnItemClickListener {
            val bundle = Bundle().apply {
                putSerializable("article", it)
            }

            findNavController().navigate(
                R.id.action_homeFragment_to_articleFragment,
                bundle
            )
        }

    }

    private suspend fun handleBreakingNewsResponse() {

        val response = try {
            RetrofitInstance.api.getNews()
        } catch (e: IOException) {
            skeleton.showSkeleton()
            Log.i(TAG, " IOException : don't have internet connection")
            return
        } catch (e: HttpException) {
            skeleton.showSkeleton()
            Log.i(TAG, "HttpException : unexpected response")
            return
        }

        if (response.isSuccessful && response.body() != null) {
            newsAdapter.differ.submitList(response.body()!!.articles)
        } else {
            Log.i(TAG, "Response not successful")
        }

    }

    private fun setUpRecyclerView() {
        binding.newsRecylcerView.apply {
            newsAdapter = HomeAdapter()
            layoutManager = LinearLayoutManager(context)
            binding.newsRecylcerView.adapter = newsAdapter
        }
    }

}
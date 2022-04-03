package com.hamza.newsapp.ui.Fragments.HomeFragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.hamza.newsapp.data.RemoteData.RetrofitInstance
import com.hamza.newsapp.databinding.FragmentHomeBinding
import retrofit2.HttpException
import java.io.IOException

const val TAG = "Home fragment"

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!


    private lateinit var newsAdapter: HomeAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        setHasOptionsMenu(true)
        _binding = FragmentHomeBinding.inflate(layoutInflater, container, false)





        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launchWhenCreated {
            binding.ProgressBar.isVisible = true
            val response = try {
                RetrofitInstance.api.getTeslaNews()
            } catch (e: IOException) {
                Log.i(TAG, " IOException : don't have internet connection")
                binding.ProgressBar.isVisible = false

                return@launchWhenCreated
            } catch (e: HttpException) {
                Log.i(TAG, "HttpException : unexpected response")
                binding.ProgressBar.isVisible = false
                return@launchWhenCreated
            }

            if (response.isSuccessful && response.body() != null) {
                newsAdapter.differ.submitList(response.body()!!.articles)
            } else {
                Log.i(TAG, "Response not successful")
            }

            binding.ProgressBar.isVisible = false


        }


        setUpRecyclerView()


    }

    private fun setUpRecyclerView() {
        binding.newsRecylcerView.apply {
            newsAdapter = HomeAdapter()
            layoutManager = LinearLayoutManager(context)
            binding.newsRecylcerView.adapter = newsAdapter
        }

    }

}
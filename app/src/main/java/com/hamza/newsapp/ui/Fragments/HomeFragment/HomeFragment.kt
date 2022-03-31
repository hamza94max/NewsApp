package com.hamza.newsapp.ui.Fragments.HomeFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.hamza.newsapp.data.Model.Article
import com.hamza.newsapp.data.Model.Source
import com.hamza.newsapp.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private var list: ArrayList<Article>? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        setHasOptionsMenu(true)
        _binding = FragmentHomeBinding.inflate(layoutInflater, container, false)


        list = arrayListOf(

            Article(
                "author",
                " content",
                " des",
                "publish at ",
                Source(56, "name"),
                "title1",
                "Url",
                "imageUrl"
            ),
            Article(
                "author",
                " content",
                " des",
                "publish at ",
                Source(56, "name"),
                "title2",
                "Url",
                "imageUrl"
            ),
            Article(
                "author",
                " content",
                " des",
                "publish at ",
                Source(56, "name"),
                "title3",
                "Url",
                "imageUrl"
            ), Article(
                "author",
                " content",
                " des",
                "publish at ",
                Source(56, "name"),
                "hamza title4 ",
                "Url",
                "imageUrl"
            )
        )

        setUpRecyclerView()


        return binding.root
    }

    private fun setUpRecyclerView() {
        binding.newsRecylcerView.layoutManager = LinearLayoutManager(context)
        binding.newsRecylcerView.adapter = HomeAdapter(list!!)
    }

}
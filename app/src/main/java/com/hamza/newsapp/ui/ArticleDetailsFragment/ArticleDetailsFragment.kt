package com.hamza.newsapp.ui.ArticleDetailsFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.hamza.newsapp.databinding.FragmentArticleBinding
import com.hamza.newsapp.ui.FavouriteFragment.FavouriteArticlesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ArticleDetailsFragment : Fragment() {

    private var _binding: FragmentArticleBinding? = null
    private val binding get() = _binding!!

    private val favArticleViewModel by viewModels<FavouriteArticlesViewModel>()

    private val args: ArticleDetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentArticleBinding.inflate(layoutInflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val article = args.article

        binding.webView.apply {

            if (article.url.isNotEmpty()) {
                webViewClient = WebViewClient()
                loadUrl(article.url)
            }
        }

        binding.fab.setOnClickListener {
            favArticleViewModel.insertArticle(article)
            Toast.makeText(context, "Added to Favourites ", Toast.LENGTH_SHORT).show()
        }

    }


}


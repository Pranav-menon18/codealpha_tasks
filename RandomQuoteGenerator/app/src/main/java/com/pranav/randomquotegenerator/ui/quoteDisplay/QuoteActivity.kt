package com.pranav.randomquotegenerator.ui.quoteDisplay

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.pranav.randomquotegenerator.databinding.ActivityQuoteBinding
import com.pranav.randomquotegenerator.di.component.DaggerActivityComponent
import com.pranav.randomquotegenerator.ui.base.UiState
import com.pranav.randomquotegenerator.di.module.ActivityModule
import com.pranav.randomquotegenerator.ui.QuoteApplication
import kotlinx.coroutines.launch
import javax.inject.Inject

class QuoteActivity : AppCompatActivity() {
    @Inject
    lateinit var quoteViewModel: QuoteViewModel

    private lateinit var binding: ActivityQuoteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuoteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        injectDependencies()
        binding.quoteText.visibility = View.GONE
        binding.quoteAuthor.visibility = View.GONE
        binding.shareQuoteButton.visibility = View.GONE
        binding.newQuoteButton.setOnClickListener {
            quoteViewModel.getRandomQuote()
            setupObserver()
        }
        binding.shareQuoteButton.setOnClickListener {
            val intent = Intent().apply {
                action = Intent.ACTION_SEND
                type = "text/plain"
                val quote = binding.quoteText.text.toString()
                val author = binding.quoteAuthor.text.toString()
                val shareText = "\"$quote\" - $author"
                putExtra(Intent.EXTRA_TEXT, shareText)
            }
            startActivity(Intent.createChooser(intent, "Please select the app: "))
        }
    }

    private fun setupObserver() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                quoteViewModel.uiState.collect {
                    when (it) {
                        is UiState.Success -> {
                            binding.quoteText.visibility = View.VISIBLE
                            binding.quoteAuthor.visibility = View.VISIBLE
                            binding.shareQuoteButton.visibility = View.VISIBLE
                            binding.quoteText.text = it.data.quote
                            binding.quoteAuthor.text = it.data.author
                        }

                        is UiState.Loading -> {
                            binding.quoteText.visibility = View.GONE
                            binding.quoteAuthor.visibility = View.GONE
                            binding.shareQuoteButton.visibility = View.GONE
                        }

                        is UiState.Error -> {
                            binding.quoteText.visibility = View.GONE
                            binding.quoteAuthor.visibility = View.GONE
                            binding.shareQuoteButton.visibility = View.GONE
                            Toast.makeText(this@QuoteActivity, it.message, Toast.LENGTH_LONG)
                                .show()
                        }
                    }
                }
            }
        }
    }

    private fun injectDependencies() {
        DaggerActivityComponent.builder()
            .applicationComponent((application as QuoteApplication).applicationComponent)
            .activityModule(ActivityModule(this)).build().inject(this)

    }
}
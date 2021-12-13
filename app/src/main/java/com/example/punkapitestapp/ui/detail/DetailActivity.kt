package com.example.punkapitestapp.ui.detail

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import com.example.domain.BeersItem
import com.example.punkapitestapp.R
import com.example.punkapitestapp.databinding.ActivityDetailBinding
import com.example.punkapitestapp.network.ConnectionLiveData
import com.example.punkapitestapp.ui.common.loadImage
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private val viewModel: DetailViewModel by viewModels()

    private lateinit var connectivityLiveData: ConnectionLiveData

    companion object {
        const val BEER_ID = "DetailActivity:id"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setConnectivityObserver()
        getObservers()

    }

    fun getObservers() {
        viewModel.modelLoading.observe(
            this,
            Observer { binding.pb.isVisible = if (it) true else false })
        viewModel.model.observe(this, Observer { getDatas(it[0]) })
    }

    fun getDatas(beer: BeersItem) {

            binding.name.text = beer.name
        binding.year.text = beer.first_brewed
        binding.abv.text = beer.abv.toString()
        binding.tag.text = beer.tagline
        binding.description.text = beer.description

        if (beer.image_url.equals("https://images.punkapi.com/v2/keg.png"))
            binding.image.setImageResource(R.drawable.beer_list_ic)
        else
            binding.image.loadImage(beer.image_url)

    }


    //Internet Connection Check

    private fun setConnectivityObserver() {
        connectivityLiveData = ConnectionLiveData(this)
        connectivityLiveData.observe(this) {
            if (it) {
                binding.itemDetails.isVisible = true
                binding.pb.isVisible = true
                binding.icDisconection.isVisible = false
                getObservers()
            } else {
                binding.itemDetails.isVisible = false
                binding.pb.isVisible = false
                binding.icDisconection.isVisible = true
            }
        }
    }


}
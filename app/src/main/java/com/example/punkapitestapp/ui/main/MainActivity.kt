package com.example.punkapitestapp.ui.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.paging.PagingData
import com.example.domain.BeersItem
import com.example.punkapitestapp.databinding.ActivityMainBinding
import com.example.punkapitestapp.network.ConnectionLiveData
import com.example.punkapitestapp.ui.common.adapterPreference
import com.example.punkapitestapp.ui.common.afterTextChanged
import com.example.punkapitestapp.ui.common.startActivity
import com.example.punkapitestapp.ui.detail.DetailActivity
import com.example.punkapitestapp.ui.paging.ResultsLoadStateAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val adapter =
        BeersAdapter { startActivity<DetailActivity>(DetailActivity.BEER_ID to it.id) }
    private lateinit var connectivityLiveData: ConnectionLiveData
    private val viewModel: MainViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setConnectivityObserver()
        adapterPreference(this, binding.mainBeersList)

    }


    fun initList() {

        val footerAdapter = ResultsLoadStateAdapter { adapter.retry() }
        binding.mainBeersList.adapter = adapter.withLoadStateFooter(footerAdapter)

        lifecycleScope.launchWhenStarted {
            viewModel.apiData("").collectLatest { it.let { getList(it) } }
        }

        binding.editSearch.afterTextChanged {
            lifecycleScope.launchWhenStarted {
                viewModel.apiData(it).collectLatest { it.let { getList(it) } }
            }
        }


        adapter.addLoadStateListener {
            binding.pb.isVisible = it.refresh is LoadState.Loading
        }


    }

    suspend fun getList(list: PagingData<BeersItem>) {
        adapter.submitData(list)
    }


    //Internet Connection Check

    private fun setConnectivityObserver() {
        connectivityLiveData = ConnectionLiveData(this)
        connectivityLiveData.observe(this) {
            if (it) {
                binding.topBar.isVisible = true
                binding.pb.isVisible = true
                binding.mainBeersList.isVisible = true
                binding.icDisconection.isVisible = false
                initList()
            } else {
                binding.topBar.isVisible = false
                binding.pb.isVisible = false
                binding.mainBeersList.isVisible = false
                binding.icDisconection.isVisible = true
            }
        }
    }


}
package com.example.freshproducts.presentation.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.example.freshproducts.databinding.FragmentHomeBinding
import com.example.freshproducts.domain.model.Fresh
import com.example.freshproducts.presentation.listeners.ListenerFresh
import com.example.freshproducts.utils.Status
import dagger.hilt.android.AndroidEntryPoint
import java.util.ArrayList


@AndroidEntryPoint
class HomeFragment : Fragment(), ListenerFresh {
    private lateinit var binding: FragmentHomeBinding
    private val mainViewModel: HomeViewModel by viewModels()
    private lateinit var adapter: HomeFreshAdapter
    private lateinit var list: ArrayList<Fresh>


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        adapter = HomeFreshAdapter(arrayListOf(), this )
        binding.mainAdapter = adapter

        mainViewModel.getFreshResponse()
        mainViewModel.getAllFresh()
        setObserveFresh()
        return binding.root
    }


    private fun setObserveFresh() {
        mainViewModel.freshProducts.observe(viewLifecycleOwner) {
            when (it.status) {
                Status.SUCCESS -> {
                    binding.progressBar.visibility = View.GONE
                    it.data?.let { fresh ->

                        renderList(fresh as ArrayList<Fresh>)
                    }
                    binding.recyclerView.visibility = View.VISIBLE
                }
                Status.ERROR -> {
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(context, it.message, Toast.LENGTH_LONG).show()
                }
                Status.LOADING -> {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.recyclerView.visibility = View.GONE
                }
            }
        }
    }


    private fun setObserveFreshOffLine() {
        mainViewModel.freshProductsOff.observe(viewLifecycleOwner) { it ->
            when (it.status) {
                Status.SUCCESS -> {
                    it.data?.let { fresh ->
                        list.forEach { prod ->
                            if (fresh.any { fav -> prod.plantId == fav.plantId })
                                prod.favorite = true
                        }
                        adapter.updateData(list)
                    }
                }
                Status.ERROR -> {
                    Toast.makeText(context, it.message, Toast.LENGTH_LONG).show()
                }
                Status.LOADING -> {

                }
            }
        }
    }


    private fun renderList(lFresh: ArrayList<Fresh>) {
        list = ArrayList()
        list.addAll(lFresh)
        adapter.updateData(list)
        setObserveFreshOffLine()
    }

    override fun onClickHeart(fresh: Fresh) {
        if(!fresh.favorite){
            fresh.apply {
                this.favorite = true
            }
            mainViewModel.insertFresh(fresh)
        }else{
            mainViewModel.deleteByPlantId(fresh.plantId)
        }
    }
}
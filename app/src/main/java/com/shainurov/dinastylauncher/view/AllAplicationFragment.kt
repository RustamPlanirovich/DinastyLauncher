package com.shainurov.dinastylauncher.view

import android.os.Bundle
import android.view.*
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.*
import com.shainurov.dinastylauncher.view.viewmodels.AllAplicationViewModel
import com.shainurov.dinastylauncher.view.adapters.AppListAdapter
import com.shainurov.dinastylauncher.databinding.FragmentAllAplicationBinding
import com.shainurov.dinastylauncher.domain.models.AppModel
import com.shainurov.dinastylauncher.utils.AppListResponce
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AllAplicationFragment : Fragment() {

    private lateinit var binding: FragmentAllAplicationBinding
    private lateinit var installedAppAdapter: AppListAdapter
    private val viewModel: AllAplicationViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentAllAplicationBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        installedAppAdapter = AppListAdapter()

        getApplicationList()

        val layoutManagerGrid =
            StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL).apply {
                binding.recyclerView.layoutManager = this
            }
        layoutManagerGrid.gapStrategy.apply {
            StaggeredGridLayoutManager.GAP_HANDLING_NONE
            StaggeredGridLayoutManager.GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS
        }

        binding.recyclerView.apply {
            adapter = installedAppAdapter
        }
    }

    private fun getApplicationList() {
        viewModel.getApplicationList().observe(viewLifecycleOwner) { response ->
            when (response) {
                is AppListResponce.Loading -> {
                    binding.appListProgressBar.visibility = View.VISIBLE
                }
                is AppListResponce.Success -> {
                    installedAppAdapter.submitList(response.data)
                    binding.appListProgressBar.visibility = View.GONE
                }
                is AppListResponce.Failure -> {

                }
            }
        }
    }



}
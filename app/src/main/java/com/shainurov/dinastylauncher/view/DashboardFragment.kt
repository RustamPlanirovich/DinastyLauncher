package com.shainurov.dinastylauncher.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.shainurov.dinastylauncher.R
import com.shainurov.dinastylauncher.databinding.DashboardFragmentBinding
import com.shainurov.dinastylauncher.utils.StoikRespone
import com.shainurov.dinastylauncher.view.viewmodels.DashboardViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DashboardFragment : Fragment() {
    private lateinit var binding: DashboardFragmentBinding
    private val viewModel: DashboardViewModel by viewModels()
    private var dailyHistory = ""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = DashboardFragmentBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.stoikSwith.observe(viewLifecycleOwner) { switchStatus ->
            binding.stoikHistoryCardView.isVisible = switchStatus
        }
        getPosts()

        val bundle = bundleOf()

        binding.stoikHistoryCardView.setOnClickListener {
            bundle.putString(
                "key",
                dailyHistory
            )
            findNavController().navigate(R.id.dailyHistoryFragment, bundle)
        }

    }

    private fun getPosts() {
        viewModel.getHistoryPosts().observe(viewLifecycleOwner) { response ->
            when (response) {
                is StoikRespone.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                is StoikRespone.Success -> {
                    binding.dailyQuestion.text = response.data!!.values.toList()[1].toString()
                    dailyHistory = response.data.values.toList()[2].toString()
                        .plus(response.data.values.toList().getOrNull(3).toString())
                    binding.progressBar.visibility = View.GONE
                }
                is StoikRespone.Failure -> {
                    binding.dailyQuestion.text = response.errorMessage
                }
                else -> {}
            }
        }
    }
}
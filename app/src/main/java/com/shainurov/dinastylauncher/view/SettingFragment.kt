package com.shainurov.dinastylauncher.view

import android.app.Activity
import android.app.role.RoleManager
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.shainurov.dinastylauncher.databinding.SettingsFragmentBinding
import com.shainurov.dinastylauncher.view.viewmodels.SettingsViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SettingFragment : Fragment() {

    private lateinit var binding: SettingsFragmentBinding
    private val viewModel: SettingsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = SettingsFragmentBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.stoikSwith.observe(viewLifecycleOwner) { isChecked ->
            binding.stoikSwitch.isChecked = isChecked
        }

        binding.stoikSwitch.setOnCheckedChangeListener { _, isChecked ->
            viewModel.saveStoikSwtichChecked(isChecked)
        }

        binding.openSystemSetting.setOnClickListener {
            startActivity(Intent(Settings.ACTION_SETTINGS))
        }

        binding.switchLauncher.setOnClickListener {
            showLauncherSelection()
        }


    }
    val startForResult = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { activityResult ->
        if (activityResult.resultCode == Activity.RESULT_OK) {
            Toast.makeText(requireContext(), "You are default launcher", Toast.LENGTH_SHORT).show()
        }
    }
    @RequiresApi(Build.VERSION_CODES.Q)
    fun showLauncherSelection() {
        val roleManager = requireActivity().getSystemService(Context.ROLE_SERVICE)
                as RoleManager
        if (roleManager.isRoleAvailable(RoleManager.ROLE_HOME) &&
            !roleManager.isRoleHeld(RoleManager.ROLE_HOME)
        ) {
            val intent = roleManager.createRequestRoleIntent(RoleManager.ROLE_HOME)
            startForResult.launch(intent)
        }
    }
}

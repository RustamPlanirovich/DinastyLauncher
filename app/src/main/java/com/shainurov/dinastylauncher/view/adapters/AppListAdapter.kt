package com.shainurov.dinastylauncher.view.adapters

import android.content.Intent
import android.net.Uri
import android.provider.Settings
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.shainurov.dinastylauncher.databinding.AppAlphabetItemBinding
import com.shainurov.dinastylauncher.databinding.InstalledAppLayoutBinding
import com.shainurov.dinastylauncher.domain.models.AppModel


class AppListAdapter : ListAdapter<AppModel, RecyclerView.ViewHolder>(DiffCallback()) {


    override fun getItemViewType(position: Int): Int =
        when (getItem(position).type) {
            1 -> 1
            else -> 0
        }

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int,
    ): RecyclerView.ViewHolder = when (viewType) {
        0 -> HeaderViewHolder(
            AppAlphabetItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
        1 -> ItemViewholder(
            InstalledAppLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
        else -> throw java.lang.AssertionError()
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is HeaderViewHolder -> {
                val layoutParams =
                    holder.itemView.layoutParams as StaggeredGridLayoutManager.LayoutParams
                layoutParams.isFullSpan = true
                holder.bind(getItem(position))
            }
            is ItemViewholder -> holder.bind(getItem(position))
        }
    }


    class HeaderViewHolder(
        private val binding: AppAlphabetItemBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: AppModel) = with(itemView) {

            binding.appAlphabetText.text = item.name
        }
    }


    class ItemViewholder(
        private val binding: InstalledAppLayoutBinding,
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: AppModel) = with(itemView) {
            binding.listAppName.text = item.name
            binding.appIcon.setImageDrawable(item.icon)
            binding.appPackage.text = item.packages

            setOnClickListener {
                val intent =
                    context.packageManager.getLaunchIntentForPackage(item.packages!!)
                if (intent != null) {
                    context.startActivity(intent)
                } else {
                    Toast.makeText(
                        context, "System app is not open for any reason.",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }

            setOnLongClickListener {

                val intent = Intent(Intent.ACTION_DELETE)
                intent.data = Uri.parse("package:${item.packages}")
                context.startActivity(intent)
                true
            }

        }
    }

    class DiffCallback : DiffUtil.ItemCallback<AppModel>() {
        override fun areItemsTheSame(oldItem: AppModel, newItem: AppModel): Boolean =
            when (oldItem.type) {
                0 -> oldItem.name == newItem.name
                else -> {
                    oldItem.icon == newItem.icon &&
                            oldItem.name == newItem.name &&
                            oldItem.packages == newItem.packages
                }
            }


        override fun areContentsTheSame(oldItem: AppModel, newItem: AppModel): Boolean =
            when (oldItem.type) {
                0 -> oldItem == newItem
                else -> oldItem == newItem
            }


        override fun getChangePayload(oldItem: AppModel, newItem: AppModel): Any =
            when (oldItem.type) {
                0 -> oldItem.name != newItem.name
                else -> {
                    oldItem.icon != newItem.icon &&
                            oldItem.name != newItem.name &&
                            oldItem.packages != newItem.packages
                }
            }


    }
}
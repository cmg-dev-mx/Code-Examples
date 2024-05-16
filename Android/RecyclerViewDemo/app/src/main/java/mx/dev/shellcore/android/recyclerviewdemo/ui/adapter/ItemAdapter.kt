package mx.dev.shellcore.android.recyclerviewdemo.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import mx.dev.shellcore.android.recyclerviewdemo.core.model.ItemAlert
import mx.dev.shellcore.android.recyclerviewdemo.core.model.ItemCommon
import mx.dev.shellcore.android.recyclerviewdemo.core.model.ItemPermission
import mx.dev.shellcore.android.recyclerviewdemo.databinding.ItemAlertBinding
import mx.dev.shellcore.android.recyclerviewdemo.databinding.ItemCommonBinding
import mx.dev.shellcore.android.recyclerviewdemo.databinding.ItemPermissionBinding

class ItemAdapter(
    private val items: List<ItemCommon>,
    private val onItemClicked: (ItemCommon) -> Unit = {}
) : RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {

    override fun getItemViewType(position: Int) = when (items[position]) {
        is ItemAlert -> ITEM_ALERT
        is ItemPermission -> ITEM_PERMISSION
        else -> ITEM_COMMON
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = when (viewType) {
        ITEM_ALERT -> AlertViewHolder(
            ItemAlertBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

        ITEM_PERMISSION -> PermissionViewHolder(
            ItemPermissionBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ), parent, false
            )
        )

        else -> CommonViewHolder(
            ItemCommonBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) =
        holder.bind(items[position], onItemClicked)

    override fun getItemCount() = items.size

    inner class PermissionViewHolder(private val binding: ItemPermissionBinding) :
        ItemViewHolder(binding) {
        override fun bind(item: ItemCommon, onItemClicked: (ItemCommon) -> Unit) {
            binding.itemPermissionTitleTxt.text = item.title
            binding.itemPermissionNextBtn.setOnClickListener {
                onItemClicked(item)
            }
        }
    }

    inner class AlertViewHolder(private val binding: ItemAlertBinding) : ItemViewHolder(binding) {
        override fun bind(item: ItemCommon, onItemClicked: (ItemCommon) -> Unit) {
            binding.itemAlertTitleTxt.text = item.title
        }
    }

    inner class CommonViewHolder(private val binding: ItemCommonBinding) : ItemViewHolder(binding) {
        override fun bind(item: ItemCommon, onItemClicked: (ItemCommon) -> Unit) {
            binding.itemCommonTitleTxt.text = item.title
            binding.itemCommonDescriptionTxt.text = item.description
        }
    }

    abstract class ItemViewHolder(binding: ViewBinding) : RecyclerView.ViewHolder(binding.root) {
        abstract fun bind(item: ItemCommon, onItemClicked: (ItemCommon) -> Unit)
    }
}

private const val ITEM_COMMON = 0
private const val ITEM_ALERT = 1
private const val ITEM_PERMISSION = 2
package me.brandon.budgetthing.ui.categorypage

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import me.brandon.budgetthing.databinding.ItemCategoryBinding
import me.brandon.budgetthing.db.entity.TransactionCategory

class CategoryListAdapter :
    ListAdapter<TransactionCategory, CategoryListAdapter.CategoryViewHolder>(
        differ
    ) {
    companion object {
        val differ = object : DiffUtil.ItemCallback<TransactionCategory>() {
            override fun areItemsTheSame(
                oldItem: TransactionCategory,
                newItem: TransactionCategory
            ) = oldItem.id == newItem.id

            override fun areContentsTheSame(
                oldItem: TransactionCategory,
                newItem: TransactionCategory
            ) = oldItem == newItem
        }
    }

    class CategoryViewHolder(private val binding: ItemCategoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(category: TransactionCategory) {
            binding.apply {
                itemCategoryTextView.text = category.name
                itemCategoryDescriptionView.isVisible = category.description != null
                itemCategoryDescriptionView.text = category.description
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder =
        CategoryViewHolder(
            ItemCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val category = getItem(position)
        holder.bind(category)
    }
}

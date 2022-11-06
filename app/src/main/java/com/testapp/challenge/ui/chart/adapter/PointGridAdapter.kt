package com.testapp.challenge.ui.chart.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.testapp.challenge.R
import com.testapp.challenge.databinding.ItemGridPointBinding
import com.testapp.challenge.model.network.dto.Point

/**
 * @author aliakseicherniakovich
 */
class PointGridAdapter : RecyclerView.Adapter<PointGridAdapter.PointGridViewHolder>() {

    private val differ: AsyncListDiffer<Point> =
        AsyncListDiffer(this, object : DiffUtil.ItemCallback<Point>() {
            override fun areContentsTheSame(oldItem: Point, newItem: Point): Boolean =
                areItemsTheSame(oldItem, newItem)

            override fun areItemsTheSame(oldItem: Point, newItem: Point): Boolean =
                oldItem == newItem
        })

    fun bindSubList(list: List<Point>) {
        differ.submitList(list)
    }

    override fun getItemCount(): Int = differ.currentList.size

    override fun onBindViewHolder(holder: PointGridViewHolder, position: Int) {
        holder.bind(differ.currentList[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PointGridViewHolder {
        val binding: ItemGridPointBinding by lazy {
            ItemGridPointBinding.bind(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.item_grid_point, parent, false
                )
            )
        }
        return PointGridViewHolder(binding)
    }

    class PointGridViewHolder(private val binding: ItemGridPointBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(coordinate: Point) {
            binding.itemXCoordinate.text = coordinate.x.toString()
            binding.itemYCoordinate.text = coordinate.y.toString()
        }
    }
}

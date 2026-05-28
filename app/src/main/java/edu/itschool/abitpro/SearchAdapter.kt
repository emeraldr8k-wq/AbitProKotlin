package edu.itschool.abitpro


import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import edu.itschool.abitpro.databinding.PrefabResultBinding
import edu.itschool.abitpro.domain.model.Hei

class SearchAdapter(
    private val onItemClick: (Hei) -> Unit
) : ListAdapter<Hei, SearchAdapter.SearchViewHolder>(DiffCallback()) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SearchViewHolder {
        Log.i("Info9", "onCreateViewHolder")

        val binding = PrefabResultBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return SearchViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        val curItem = getItem(position)
        holder.bind(curItem, onItemClick)
    }

    class SearchViewHolder(
        private val binding: PrefabResultBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Hei, onItemClick: (Hei) -> Unit) {
            binding.vuzName.text = item.name
            binding.budgBall.text = item.freePassingGrade.toString()
            binding.budgPlace.text = item.freePlace.toString()
            binding.paidBall.text = item.payPassingGrade.toString()
            binding.paidPlaces.text = item.payPlace.toString()
            binding.costValue.text = item.cost.toString()

            binding.root.setOnClickListener {

                Log.i("Info9", "bind")

                onItemClick(item)
            }

        }


    }


    class DiffCallback : DiffUtil.ItemCallback<Hei>() {
        override fun areItemsTheSame(
            oldItem: Hei,
            newItem: Hei
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: Hei,
            newItem: Hei
        ): Boolean {
            return oldItem == newItem
        }

    }
}
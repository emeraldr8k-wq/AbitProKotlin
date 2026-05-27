package edu.itschool.abitpro


import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import edu.itschool.abitpro.SearchAdapter.SearchViewHolder
import edu.itschool.abitpro.domain.model.Hei

class SearchAdapter(
    private val onItemClick: (Hei) -> Unit
) : ListAdapter<Hei, SearchViewHolder>(DiffCallback()) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SearchViewHolder {
        Log.i("Info9", "onCreateViewHolder")

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.prefab_result, parent, false)

        return SearchViewHolder(view)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        val curItem = getItem(position)
        holder.bind(curItem, onItemClick)
    }

    class SearchViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val titleText: TextView = itemView.findViewById(R.id.vuz_name)
        private val budgBall: TextView = itemView.findViewById(R.id.budg_ball)

        private val budgPlace: TextView = itemView.findViewById(R.id.budg_place)

        private val paidBall: TextView = itemView.findViewById(R.id.paid_ball)

        private val paidPlace: TextView = itemView.findViewById(R.id.paid_places)
        private val cost: TextView = itemView.findViewById(R.id.cost_value)

        fun bind(item: Hei, onItemClick: (Hei) -> Unit) {
            titleText.text = item.name
            budgBall.text = item.freePassingGrade.toString()
            budgPlace.text = item.freePlace.toString()
            paidBall.text = item.payPassingGrade.toString()
            paidPlace.text = item.payPlace.toString()
            cost.text = item.cost.toString()

            itemView.setOnClickListener {

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
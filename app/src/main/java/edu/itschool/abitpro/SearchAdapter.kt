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
import edu.itschool.abitpro.data.dto.HeiDto

class SearchAdapter(
    private val onItemClick: (HeiDto) -> Unit
) : ListAdapter<HeiDto, SearchViewHolder>(DiffCallback()) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SearchViewHolder {
        Log.e("Info9", "create")

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

        private val paidPlace: TextView = itemView.findViewById(R.id.paid_place)

        fun bind(item: HeiDto, onItemClick: (HeiDto) -> Unit) {
            titleText.text = item.name
//            budgBall.text = item.budgBall               //todo бюджетный балл
//            budgPlace.text = item.budgPlace
//            paidBall.text = item.paidBall
//            paidPlace.text = item.paidPlace
            itemView.setOnClickListener{

                Log.i("Info9", "bind")

                onItemClick(item)
            }

        }


    }


    class DiffCallback : DiffUtil.ItemCallback<HeiDto>() {
        override fun areItemsTheSame(
            oldItem: HeiDto,
            newItem: HeiDto
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: HeiDto,
            newItem: HeiDto
        ): Boolean {
            return oldItem == newItem
        }

    }
}
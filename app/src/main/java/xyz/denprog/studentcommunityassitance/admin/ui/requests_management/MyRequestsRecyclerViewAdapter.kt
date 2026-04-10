package xyz.denprog.studentcommunityassitance.admin.ui.requests_management

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import xyz.denprog.studentcommunityassitance.R

import xyz.denprog.studentcommunityassitance.admin.ui.requests_management.placeholder.PlaceholderContent.PlaceholderItem
import xyz.denprog.studentcommunityassitance.database.entity.Request
import xyz.denprog.studentcommunityassitance.databinding.FragmentRequestsBinding

/**
 * [RecyclerView.Adapter] that can display a [PlaceholderItem].
 * TODO: Replace the implementation with code for your data type.
 */
class MyRequestsRecyclerViewAdapter(
    private val values: ArrayList<Request>
) : RecyclerView.Adapter<MyRequestsRecyclerViewAdapter.ViewHolder>() {

    var onRequestClicked: ((Request) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            FragmentRequestsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    fun refreshAdapter(requests: List<Request>) {
        values.clear()
        values.addAll(requests)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.idView.text = item.requestId.toString()
        holder.contentView.text = item.title
            holder.itemView.setOnClickListener {
                onRequestClicked?.invoke(item)
            }
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(binding: FragmentRequestsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val idView: TextView = binding.itemNumber
        val contentView: TextView = binding.content

        override fun toString(): String {
            return super.toString() + " '" + contentView.text + "'"
        }
    }

}
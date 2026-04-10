package xyz.denprog.studentcommunityassitance.admin.ui.announcement_management

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import xyz.denprog.studentcommunityassitance.admin.ui.announcement_management.placeholder.PlaceholderContent

import xyz.denprog.studentcommunityassitance.databinding.FragmentAnnouncementBinding

/**
 * [RecyclerView.Adapter] that can display a [xyz.denprog.studentcommunityassitance.admin.ui.announcement_management.placeholder.PlaceholderContent.PlaceholderItem].
 * TODO: Replace the implementation with code for your data type.
 */
class MyAnnouncementRecyclerViewAdapter(
    private val values: List<PlaceholderContent.PlaceholderItem>
) : RecyclerView.Adapter<MyAnnouncementRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            FragmentAnnouncementBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.idView.text = item.id
        holder.contentView.text = item.content
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(binding: FragmentAnnouncementBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val idView: TextView = binding.itemNumber
        val contentView: TextView = binding.content

        override fun toString(): String {
            return super.toString() + " '" + contentView.text + "'"
        }
    }

}
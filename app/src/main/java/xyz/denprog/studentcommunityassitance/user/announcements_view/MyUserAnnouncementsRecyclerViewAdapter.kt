package xyz.denprog.studentcommunityassitance.user.announcements_view

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import xyz.denprog.studentcommunityassitance.R
import xyz.denprog.studentcommunityassitance.database.entity.Announcement

import xyz.denprog.studentcommunityassitance.user.announcements_view.placeholder.PlaceholderContent.PlaceholderItem
import xyz.denprog.studentcommunityassitance.databinding.FragmentUserAnnouncementsBinding

/**
 * [RecyclerView.Adapter] that can display a [PlaceholderItem].
 * TODO: Replace the implementation with code for your data type.
 */
class MyUserAnnouncementsRecyclerViewAdapter(
    private val values: List<Announcement>
) : RecyclerView.Adapter<MyUserAnnouncementsRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            FragmentUserAnnouncementsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    fun refreshAnnouncements(newValues: List<Announcement>) {
        (values as MutableList).clear()
        (values as MutableList).addAll(newValues)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.idView.text = item.announcementId.toString()
        holder.contentView.text = item.content
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(binding: FragmentUserAnnouncementsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val idView: TextView = binding.itemNumber
        val contentView: TextView = binding.content

        override fun toString(): String {
            return super.toString() + " '" + contentView.text + "'"
        }
    }

}
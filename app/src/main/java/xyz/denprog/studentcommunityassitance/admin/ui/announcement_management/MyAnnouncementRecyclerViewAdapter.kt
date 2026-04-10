package xyz.denprog.studentcommunityassitance.admin.ui.announcement_management

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView

import xyz.denprog.studentcommunityassitance.databinding.FragmentAnnouncementBinding

class MyAnnouncementRecyclerViewAdapter(
    private val values: MutableList<AnnouncementView> = mutableListOf()
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

    fun refresh(newValues: List<AnnouncementView>) {
        values.clear()
        values.addAll(newValues)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.titleView.text = item.title
        holder.contentView.text = item.content
        holder.dateView.text = item.formattedDate
        holder.timeView.text = item.formattedTime
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(binding: FragmentAnnouncementBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val titleView: TextView = binding.announcementTitle
        val contentView: TextView = binding.content
        val dateView: TextView = binding.announcementDate
        val timeView: TextView = binding.announcementTime

        override fun toString(): String {
            return super.toString() + " '" + contentView.text + "'"
        }
    }

}

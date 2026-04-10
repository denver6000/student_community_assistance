package xyz.denprog.studentcommunityassitance.user.requests_management

import android.graphics.Color
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import xyz.denprog.studentcommunityassitance.constants.ProjectConstants
import xyz.denprog.studentcommunityassitance.database.entity.Request
import xyz.denprog.studentcommunityassitance.databinding.FragmentUserViewRequestsBinding
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class MyRequestsRecyclerViewAdapter(
    private val values: MutableList<Request> = mutableListOf(),
    private val onRequestClick: (Request) -> Unit
) : RecyclerView.Adapter<MyRequestsRecyclerViewAdapter.ViewHolder>() {

    private val dateFormat = SimpleDateFormat("MMM dd, yyyy", Locale.getDefault())
    var onSetFeedbackClick: ((Request) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            FragmentUserViewRequestsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    fun refreshRequests(newValues: List<Request>) {
        values.clear()
        values.addAll(newValues)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.titleView.text = item.title
        holder.dateView.text = dateFormat.format(Date(item.timestamp))
        holder.statusView.text = ProjectConstants.statusLabel(item.status)
        val statusColor = when (item.status) {
            ProjectConstants.PENDING -> Color.parseColor("#FFA726")
            ProjectConstants.IN_PROGRESS -> Color.parseColor("#42A5F5")
            ProjectConstants.RESOLVED -> Color.parseColor("#66BB6A")
            else -> Color.GRAY
        }
        if (item.status == ProjectConstants.RESOLVED && !item.hasFeedBack) {
            holder.setFeedbackButton.visibility = TextView.VISIBLE
            holder.setFeedbackButton.setOnClickListener {
                onSetFeedbackClick?.invoke(item)
            }
        } else {
            holder.setFeedbackButton.visibility = TextView.GONE
            holder.setFeedbackButton.setOnClickListener(null)
        }
        holder.statusView.setBackgroundColor(statusColor)
        holder.itemView.setOnClickListener { onRequestClick(item) }
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(binding: FragmentUserViewRequestsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val titleView: TextView = binding.requestTitle
        val dateView: TextView = binding.requestDate
        val statusView: TextView = binding.requestStatus
        val setFeedbackButton = binding.setFeedback
    }
}

package xyz.denprog.studentcommunityassitance.user.requests_management

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import xyz.denprog.studentcommunityassitance.constants.ProjectConstants
import xyz.denprog.studentcommunityassitance.databinding.FragmentRequestDetailBinding
import xyz.denprog.studentcommunityassitance.utils.toLocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

class RequestDetailFragment : Fragment() {

    private lateinit var binding: FragmentRequestDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRequestDetailBinding.inflate(inflater, container, false)

        val title = requireArguments().getString(ARG_TITLE).orEmpty()
        val category = requireArguments().getString(ARG_CATEGORY).orEmpty()
        val description = requireArguments().getString(ARG_DESCRIPTION).orEmpty()
        val adminNotes = requireArguments().getString(ARG_ADMIN_NOTES).orEmpty()
        val timestamp = requireArguments().getLong(ARG_TIMESTAMP)
        val status = requireArguments().getInt(ARG_STATUS, ProjectConstants.PENDING)

        val formatter = DateTimeFormatter.ofPattern("MMM d, yyyy h:mm a", Locale.getDefault())

        binding.requestTitle.text = title
        binding.requestStatus.text = ProjectConstants.statusLabel(status)
        binding.requestCategory.text = category
        binding.requestDateTime.text = timestamp.toLocalDateTime().format(formatter)
        binding.requestDescription.text = description
        binding.requestAdminNotes.text = if (adminNotes.isBlank()) "No admin notes yet." else adminNotes

        return binding.root
    }

    companion object {
        const val ARG_TITLE = "title"
        const val ARG_CATEGORY = "category"
        const val ARG_DESCRIPTION = "description"
        const val ARG_TIMESTAMP = "timestamp"
        const val ARG_ADMIN_NOTES = "admin_notes"
        const val ARG_STATUS = "status"
    }
}

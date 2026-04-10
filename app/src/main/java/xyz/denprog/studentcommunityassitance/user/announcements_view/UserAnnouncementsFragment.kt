package xyz.denprog.studentcommunityassitance.user.announcements_view

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import xyz.denprog.studentcommunityassitance.R
import xyz.denprog.studentcommunityassitance.databinding.FragmentAnnouncementsUserListBinding
import xyz.denprog.studentcommunityassitance.user.announcements_view.placeholder.PlaceholderContent

/**
 * A fragment representing a list of Items.
 */
class UserAnnouncementsFragment : Fragment() {

    val announcementUserViewModel: AnnouncementUserViewModel by activityViewModels()

    lateinit var binding: FragmentAnnouncementsUserListBinding;

    private var columnCount = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            columnCount = it.getInt(ARG_COLUMN_COUNT)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAnnouncementsUserListBinding.inflate(
            LayoutInflater.from(container?.context), container, false
        )
        val adapter = MyUserAnnouncementsRecyclerViewAdapter(emptyList())

        // Set the adapter
        if (view is RecyclerView) {
            with(view) {
                binding.root.adapter = adapter
            }
        }

        announcementUserViewModel.getAnnouncements {
            adapter.refreshAnnouncements(it)
        }



        return binding.root
    }

    companion object {

        // TODO: Customize parameter argument names
        const val ARG_COLUMN_COUNT = "column-count"

        // TODO: Customize parameter initialization
        @JvmStatic
        fun newInstance(columnCount: Int) =
            UserAnnouncementsFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_COLUMN_COUNT, columnCount)
                }
            }
    }
}
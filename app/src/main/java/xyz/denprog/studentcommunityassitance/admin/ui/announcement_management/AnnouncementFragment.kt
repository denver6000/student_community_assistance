package xyz.denprog.studentcommunityassitance.admin.ui.announcement_management

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import dagger.hilt.android.HiltAndroidApp
import xyz.denprog.studentcommunityassitance.R
import xyz.denprog.studentcommunityassitance.admin.ui.announcement_management.placeholder.PlaceholderContent
import xyz.denprog.studentcommunityassitance.databinding.FragmentAnnouncementBinding
import xyz.denprog.studentcommunityassitance.databinding.FragmentAnnouncementListBinding

/**
 * A fragment representing a list of Items.
 */

class AnnouncementFragment : Fragment() {


    val viewModel: AnnouncementsManagementViewModel by activityViewModels()

    private var columnCount = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            columnCount = it.getInt(ARG_COLUMN_COUNT)
        }
    }

    lateinit var binding: FragmentAnnouncementListBinding;

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAnnouncementListBinding.inflate(LayoutInflater.from(container?.context), container, false)
        val layoutManager = binding.root.layoutManager
        val adapter = MyAnnouncementRecyclerViewAdapter(emptyList())
        if (view is RecyclerView) {
            with(view) {
                val adapter = MyAnnouncementRecyclerViewAdapter(emptyList())
                binding.root.adapter = adapter
            }
        }

        viewModel.announcements.observe(requireActivity()) { announcements ->
            adapter.refresh(announcements)
        }


        return binding.root
    }

    companion object {

        // TODO: Customize parameter argument names
        const val ARG_COLUMN_COUNT = "column-count"

        // TODO: Customize parameter initialization
        @JvmStatic
        fun newInstance(columnCount: Int) =
            AnnouncementFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_COLUMN_COUNT, columnCount)
                }
            }
    }
}
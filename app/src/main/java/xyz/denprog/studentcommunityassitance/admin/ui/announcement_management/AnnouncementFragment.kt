package xyz.denprog.studentcommunityassitance.admin.ui.announcement_management

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.NavHostFragment
import xyz.denprog.studentcommunityassitance.R
import xyz.denprog.studentcommunityassitance.databinding.FragmentAnnouncementListBinding

class AnnouncementFragment : Fragment() {

    val viewModel: AnnouncementsManagementViewModel by activityViewModels()

    private lateinit var binding: FragmentAnnouncementListBinding
    private val adapter = MyAnnouncementRecyclerViewAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAnnouncementListBinding.inflate(inflater, container, false)
        binding.list.adapter = adapter

        viewModel.announcements.observe(viewLifecycleOwner) {
            adapter.refresh(it)
        }

        viewModel.getAllAnnouncements()

        binding.addAnnouncements.setOnClickListener {
            val navHostFragment = NavHostFragment.findNavController(
                this
            )
            navHostFragment.navigate(R.id.action_announcementFragment2_to_addAnnouncementsFragment)
        }
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        viewModel.getAllAnnouncements()
    }
}

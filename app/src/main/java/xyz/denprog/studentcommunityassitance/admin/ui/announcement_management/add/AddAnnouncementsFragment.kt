package xyz.denprog.studentcommunityassitance.admin.ui.announcement_management.add

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.NavHostFragment
import xyz.denprog.studentcommunityassitance.R
import xyz.denprog.studentcommunityassitance.databinding.FragmentAddAnnouncementsBinding

class AddAnnouncementsFragment : Fragment() {

    companion object {
        fun newInstance() = AddAnnouncementsFragment()
    }

    private val viewModel: AddAnnouncementsViewModel by activityViewModels()
    private lateinit var binding: FragmentAddAnnouncementsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO: Use the ViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddAnnouncementsBinding.inflate(inflater, container, false)
        binding.addAnnouncementsAction.setOnClickListener {
                viewModel.addAnnouncement(
                    binding.announcementTitle.text.toString(),
                    binding.announcementDescription.text.toString(),
                    onSuccess = {
                        NavHostFragment.findNavController(requireParentFragment()).popBackStack()
                    }
                )
        }
        return binding.root
    }
}
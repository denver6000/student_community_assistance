package xyz.denprog.studentcommunityassitance.admin.ui.announcement_management.add

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import xyz.denprog.studentcommunityassitance.R

class AddAnnouncementsFragment : Fragment() {

    companion object {
        fun newInstance() = AddAnnouncementsFragment()
    }

    private val viewModel: AddAnnouncementsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO: Use the ViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_add_announcements, container, false)
    }
}
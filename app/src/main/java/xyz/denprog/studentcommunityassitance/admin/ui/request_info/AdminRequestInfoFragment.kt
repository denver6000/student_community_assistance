package xyz.denprog.studentcommunityassitance.admin.ui.request_info

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import dagger.hilt.android.AndroidEntryPoint
import xyz.denprog.studentcommunityassitance.R
import xyz.denprog.studentcommunityassitance.admin.AdminActivityViewModel

@AndroidEntryPoint
class AdminRequestInfoFragment : Fragment() {

    companion object {
        fun newInstance() = AdminRequestInfoFragment()
    }

    private val viewModel: AdminRequestInfoViewModel by viewModels()
    private val adminActivityViewModel: AdminActivityViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO: Use the ViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Toast.makeText(requireContext(), "Request Info Fragment + `${adminActivityViewModel.requestSelected}", Toast.LENGTH_SHORT).show()
        return inflater.inflate(R.layout.fragment_admin_request_info, container, false)
    }
}
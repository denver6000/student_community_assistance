package xyz.denprog.studentcommunityassitance.admin.ui.request_info

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.SimpleAdapter
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import dagger.hilt.android.AndroidEntryPoint
import xyz.denprog.studentcommunityassitance.R
import xyz.denprog.studentcommunityassitance.admin.AdminActivityViewModel
import xyz.denprog.studentcommunityassitance.constants.ProjectConstants
import xyz.denprog.studentcommunityassitance.databinding.FragmentAdminRequestInfoBinding

@AndroidEntryPoint
class AdminRequestInfoFragment : Fragment() {

    companion object {
        fun newInstance() = AdminRequestInfoFragment()
    }

    private val viewModel: AdminRequestInfoViewModel by viewModels()
    private val adminActivityViewModel: AdminActivityViewModel by activityViewModels()

    private lateinit var binding: FragmentAdminRequestInfoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO: Use the ViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAdminRequestInfoBinding.inflate(inflater, container, false)


        adminActivityViewModel.loadRequest(
            adminActivityViewModel.requestSelected!!) { request ->
            binding.requestTitle.text = request.title
            binding.requestContent.text = request.description
        }

        Toast.makeText(requireContext(), "Request Info Fragment + `${adminActivityViewModel.requestSelected}", Toast.LENGTH_SHORT).show()
        binding.statusSpinner.adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_item,
            ProjectConstants.STATUS_LIST.map { ProjectConstants.statusLabel(it) }
        )

        binding.updateStatus.setOnClickListener {
            adminActivityViewModel.update(
                adminActivityViewModel.requestSelected!!,
                ProjectConstants.STATUS_LIST[binding.statusSpinner.selectedItemPosition]
            ) {
                Toast.makeText(requireContext(), "Request status updated", Toast.LENGTH_SHORT).show()
            }
        }

        return binding.root
    }
}
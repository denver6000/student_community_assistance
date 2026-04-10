package xyz.denprog.studentcommunityassitance.admin.ui.requests_management

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.NavHostFragment
import xyz.denprog.studentcommunityassitance.R
import xyz.denprog.studentcommunityassitance.admin.AdminActivityViewModel
import xyz.denprog.studentcommunityassitance.admin.ui.requests_management.placeholder.PlaceholderContent
import xyz.denprog.studentcommunityassitance.database.entity.Request
import xyz.denprog.studentcommunityassitance.databinding.FragmentRequestsListBinding

/**
 * A fragment representing a list of Items.
 */
class RequestsFragment : Fragment() {
    val viewModel: RequestsViewModel by activityViewModels()
    val adminViewModel: AdminActivityViewModel by activityViewModels()
    private lateinit var binding: FragmentRequestsListBinding;
    private val adapter = MyRequestsRecyclerViewAdapter(ArrayList())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRequestsListBinding.inflate(
            inflater,
            container,
            false
        )
        binding.root.adapter = adapter
        binding.root.layoutManager = LinearLayoutManager(requireContext())


        viewModel.getAllRequests {
            adapter.refreshAdapter(it)
        }

        adapter.onRequestClicked = {
            adminViewModel.requestSelected = it.requestId
            val controller = NavHostFragment.findNavController(requireParentFragment())
            controller.navigate(R.id.action_requestsFragment2_to_adminRequestInfoFragment)
        }

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        viewModel.getAllRequests {
            adapter.refreshAdapter(it)
        }
    }


}
package xyz.denprog.studentcommunityassitance.user.requests_management

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.NavHostFragment
import dagger.hilt.android.AndroidEntryPoint
import xyz.denprog.studentcommunityassitance.R
import xyz.denprog.studentcommunityassitance.databinding.FragmentUserViewRequestsListBinding

@AndroidEntryPoint
class UserViewRequestsFragment : Fragment() {

    private var columnCount = 1
    private val viewModel: UserViewRequestsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            columnCount = it.getInt(ARG_COLUMN_COUNT)
        }
    }

    private lateinit var binding: FragmentUserViewRequestsListBinding
    private val adapter = MyRequestsRecyclerViewAdapter(
        onRequestClick = { request ->
            NavHostFragment.findNavController(this).navigate(
                R.id.action_requestsFragment_to_requestDetailFragment,
                bundleOf(
                    RequestDetailFragment.ARG_TITLE to request.title,
                    RequestDetailFragment.ARG_CATEGORY to request.category,
                    RequestDetailFragment.ARG_DESCRIPTION to request.description,
                    RequestDetailFragment.ARG_TIMESTAMP to request.timestamp,
                    RequestDetailFragment.ARG_ADMIN_NOTES to request.adminNotes,
                    RequestDetailFragment.ARG_STATUS to request.status
                )
            )
        }
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUserViewRequestsListBinding.inflate(inflater, container, false)

        binding.requestsManagementList.layoutManager = when {
            columnCount <= 1 -> LinearLayoutManager(context)
            else -> GridLayoutManager(context, columnCount)
        }
        binding.requestsManagementList.adapter = adapter

        binding.addRequests.setOnClickListener {
            NavHostFragment.findNavController(this)
                .navigate(R.id.action_requestsFragment_to_addRequestsFragment)
        }

        loadRequests()

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        loadRequests()
    }

    private fun loadRequests() {
        viewModel.getRequests { requests ->
            adapter.refreshRequests(requests)
        }
    }

    companion object {

        // TODO: Customize parameter argument names
        const val ARG_COLUMN_COUNT = "column-count"

        // TODO: Customize parameter initialization
        @JvmStatic
        fun newInstance(columnCount: Int) =
            UserViewRequestsFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_COLUMN_COUNT, columnCount)
                }
            }
    }
}

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
import xyz.denprog.studentcommunityassitance.R
import xyz.denprog.studentcommunityassitance.admin.ui.requests_management.placeholder.PlaceholderContent
import xyz.denprog.studentcommunityassitance.database.entity.Request

/**
 * A fragment representing a list of Items.
 */
class RequestsFragment : Fragment() {

    private var columnCount = 1
    val viewModel: RequestsViewModel by activityViewModels()

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
        val view = inflater.inflate(R.layout.fragment_requests_list, container, false)

        // Set the adapter
        if (view is RecyclerView) {
            with(view) {
                layoutManager = when {
                    columnCount <= 1 -> LinearLayoutManager(context)
                    else -> GridLayoutManager(context, columnCount)
                }
                adapter = MyRequestsRecyclerViewAdapter(ArrayList())
            }
        }

        viewModel.requestsMutableLiveData.observe(requireActivity(), {
            val adapter: MyRequestsRecyclerViewAdapter = ((view as RecyclerView).adapter) as MyRequestsRecyclerViewAdapter
            adapter.refreshAdapter(
                it
            )
        })

        return view
    }

    companion object {

        // TODO: Customize parameter argument names
        const val ARG_COLUMN_COUNT = "column-count"

        // TODO: Customize parameter initialization
        @JvmStatic
        fun newInstance(columnCount: Int) =
            RequestsFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_COLUMN_COUNT, columnCount)
                }
            }
    }
}
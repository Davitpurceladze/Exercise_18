package com.example.exercise18.user

import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.exercise18.BaseFragment
import com.example.exercise18.adapter.UsersListAdapter
import com.example.exercise18.databinding.FragmentUserBinding
import com.example.exercise18.paging.UsersPagingViewModel
import kotlinx.coroutines.launch


class UserFragment : BaseFragment<FragmentUserBinding>(FragmentUserBinding::inflate) {


    private val userViewModel: UsersPagingViewModel by viewModels()
    private val pagingAdapter = UsersListAdapter()
    override fun bind() {

    }

    override fun bindViewActionListeners() {
    }

    override fun bindObserves() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                userViewModel.usersList.collect {
                    pagingAdapter.submitData(it)
                }
            }
        }

        with(binding) {
            userRecyclerView.apply {
                layoutManager = LinearLayoutManager(requireContext())
                adapter = pagingAdapter
            }

        }

    }
}
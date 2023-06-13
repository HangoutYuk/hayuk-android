package com.mfarhan08a.hangoutyuk.ui.history

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.mfarhan08a.hangoutyuk.data.Result
import com.mfarhan08a.hangoutyuk.data.model.PollItem
import com.mfarhan08a.hangoutyuk.databinding.FragmentHistoryBinding
import com.mfarhan08a.hangoutyuk.ui.adapter.PollAdapter
import com.mfarhan08a.hangoutyuk.util.ViewModelFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HistoryFragment : Fragment() {

    private var _binding: FragmentHistoryBinding? = null
    private val binding get() = _binding!!

    private val historyViewModel by viewModels<HistoryViewModel> {
        ViewModelFactory.getInstance(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHistoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.recyclerViewPoll.layoutManager = layoutManager

        try {
            historyViewModel.apply {
                getToken().observe(viewLifecycleOwner) { token ->
                    getId().observe(viewLifecycleOwner) { userId ->
                        getPollsUser(token!!, userId!!).observe(viewLifecycleOwner) {
                            when (it) {
                                is Result.Loading -> {
                                    showLoading(true)
                                }
                                is Result.Success -> {
                                    try {
                                        showLoading(false)
                                        if (it.data.data.isNotEmpty()) {
                                            setUserPolls(it.data.data, requireContext())
                                            showEmptyText(false)
                                        } else {
                                            showEmptyText(true)
                                        }
                                    } catch (e: Exception) {
                                        Log.d(TAG, e.toString())
                                    }
                                }
                                is Result.Error -> {
                                    showLoading(false)
                                    Log.d(TAG, "e: ${it.error}")
                                }
                            }
                        }
                    }
                }
            }
        } catch (e: Exception) {
            Log.d(TAG, e.toString())
        }
    }

    private fun showEmptyText(isEmpty: Boolean) {
        try {
            if (isAdded) {
                binding.textViewEmpty.visibility = if (isEmpty) View.VISIBLE else View.GONE
                binding.recyclerViewPoll.visibility = if (isEmpty) View.GONE else View.VISIBLE
            }
        } catch (e: Exception) {
            Log.d(TAG, e.toString())
        }
    }

    private fun setUserPolls(pollItems: List<PollItem>, context: Context) {
        try {
            if (isAdded) {
                val adapter = PollAdapter(pollItems, context)
                binding.recyclerViewPoll.adapter = adapter
            }
        } catch (e: Exception) {
            Log.d(TAG, e.toString())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun showLoading(isLoading: Boolean) {
        try {
            if (isAdded) {
                binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
            }
        } catch (e: Exception) {
            Log.d(TAG, e.toString())
        }
    }

    companion object {
        private val TAG = HistoryFragment::class.java.simpleName
    }
}
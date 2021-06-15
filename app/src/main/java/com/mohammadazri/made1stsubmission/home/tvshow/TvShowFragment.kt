package com.mohammadazri.made1stsubmission.home.tvshow

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.mohammadazri.made1stsubmission.core.data.Resource
import com.mohammadazri.made1stsubmission.core.domain.model.TvShow
import com.mohammadazri.made1stsubmission.core.ui.TvShowRecyclerViewAdapter
import com.mohammadazri.made1stsubmission.databinding.FragmentTvShowBinding
import com.mohammadazri.made1stsubmission.detail.tvshow.DetailTvShowActivity
import org.koin.android.viewmodel.ext.android.viewModel

class TvShowFragment : Fragment(), TvShowRecyclerViewAdapter.TvShowRecyclerViewAdapterInterface {

    private var _binding: FragmentTvShowBinding? = null
    private val binding get() = _binding!!
    private val tvShowViewModel: TvShowViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTvShowBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.let {
            val tvShowRecyclerViewAdapter = TvShowRecyclerViewAdapter(this)

            tvShowViewModel.getTvShows(1).observe(viewLifecycleOwner, { tvShows ->
                tvShows.let {
                    when (tvShows) {
                        is Resource.Loading -> {
                            binding.progressBar4.visibility = View.VISIBLE
                        }
                        is Resource.Success -> {
                            binding.progressBar4.visibility = View.GONE
                            tvShowRecyclerViewAdapter.setTvShows(it.data)
                        }
                        is Resource.Error -> {
                            binding.progressBar4.visibility = View.GONE
                            Toast.makeText(context, "There is an error", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            })

            with(binding.recyclerViewTvShow) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = tvShowRecyclerViewAdapter
            }
        }
    }

    override fun onItemClicked(tvShow: TvShow) {
        val intent = Intent(context, DetailTvShowActivity::class.java)
        intent.putExtra(DetailTvShowActivity.EXTRA_TVSHOW, tvShow.id)
        startActivity(intent)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
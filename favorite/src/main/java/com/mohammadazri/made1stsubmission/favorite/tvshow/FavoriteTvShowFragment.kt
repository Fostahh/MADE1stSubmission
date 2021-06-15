package com.mohammadazri.made1stsubmission.favorite.tvshow

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.mohammadazri.made1stsubmission.core.domain.model.TvShow
import com.mohammadazri.made1stsubmission.core.ui.FavoriteTvShowRecyclerViewAdapter
import com.mohammadazri.made1stsubmission.detail.tvshow.DetailTvShowActivity
import com.mohammadazri.made1stsubmission.di.favoriteModule
import com.mohammadazri.made1stsubmission.favorite.databinding.FragmentFavoriteTvShowBinding
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class FavoriteTvShowFragment : Fragment(),
    FavoriteTvShowRecyclerViewAdapter.FavoriteTvShowRecyclerViewAdapterInterface {

    private var _binding: FragmentFavoriteTvShowBinding? = null
    private val binding get() = _binding!!

    private val favoriteTvShowViewModel: FavoriteTvShowViewModel by viewModel()
    private lateinit var favoriteTvShowRecyclerViewAdapter: FavoriteTvShowRecyclerViewAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentFavoriteTvShowBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loadKoinModules(favoriteModule)

        activity?.let {
            favoriteTvShowRecyclerViewAdapter = FavoriteTvShowRecyclerViewAdapter(this)

            favoriteTvShowViewModel.getFavoriteTvShow().observe(viewLifecycleOwner, { listTvShow ->
                listTvShow.let {
                    favoriteTvShowRecyclerViewAdapter.setTvShows(listTvShow)
                }
            })

            with(binding.recyclerViewFavoriteTvShow) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = favoriteTvShowRecyclerViewAdapter
            }
        }
    }

    override fun onItemClicked(tvShow: TvShow) {
        val intent = Intent(context, DetailTvShowActivity::class.java)
        intent.putExtra(DetailTvShowActivity.EXTRA_TVSHOW, tvShow.id)
        startActivity(intent)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
package com.mohammadazri.made1stsubmission.favorite.movie

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.mohammadazri.made1stsubmission.core.domain.model.Movie
import com.mohammadazri.made1stsubmission.core.ui.FavoriteMovieRecyclerViewAdapter
import com.mohammadazri.made1stsubmission.detail.movie.DetailMovieActivity
import com.mohammadazri.made1stsubmission.di.favoriteModule
import com.mohammadazri.made1stsubmission.favorite.databinding.FragmentFavoriteMovieBinding
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class FavoriteMovieFragment : Fragment(),
    FavoriteMovieRecyclerViewAdapter.FavoriteMovieRecyclerViewAdapterInterface {

    private var _binding: FragmentFavoriteMovieBinding? = null
    private val binding get() = _binding!!
    private val favoriteMovieViewModel: FavoriteMovieViewModel by viewModel()
    private lateinit var favoriteMovieRecyclerViewAdapter: FavoriteMovieRecyclerViewAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoriteMovieBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loadKoinModules(favoriteModule)

        activity?.let {
            favoriteMovieRecyclerViewAdapter = FavoriteMovieRecyclerViewAdapter(this)

            favoriteMovieViewModel.getFavoriteMovie().observe(viewLifecycleOwner, { listMovie ->
                listMovie.let {
                    favoriteMovieRecyclerViewAdapter.setMovies(listMovie)
                }
            })

            with(binding.recyclerViewFavoriteMovie) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = favoriteMovieRecyclerViewAdapter
            }
        }
    }

    override fun onItemClicked(movie: Movie) {
        val intent = Intent(context, DetailMovieActivity::class.java)
        intent.putExtra(DetailMovieActivity.EXTRA_MOVIE, movie.id)
        startActivity(intent)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
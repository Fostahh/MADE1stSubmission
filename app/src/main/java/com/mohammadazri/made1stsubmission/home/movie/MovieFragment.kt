package com.mohammadazri.made1stsubmission.home.movie

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.mohammadazri.made1stsubmission.core.data.Resource
import com.mohammadazri.made1stsubmission.core.domain.model.Movie
import com.mohammadazri.made1stsubmission.core.ui.MovieRecyclerViewAdapter
import com.mohammadazri.made1stsubmission.databinding.FragmentMovieBinding
import com.mohammadazri.made1stsubmission.detail.movie.DetailMovieActivity
import org.koin.android.viewmodel.ext.android.viewModel

class MovieFragment : Fragment(),
    MovieRecyclerViewAdapter.MovieRecyclerViewAdapterInterface {

    private var _binding: FragmentMovieBinding? = null
    private val binding get() = _binding!!

    private val movieViewModel: MovieViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMovieBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.let {
            val movieRecyclerViewAdapter = MovieRecyclerViewAdapter(this)

            movieViewModel.getMovies(1).observe(viewLifecycleOwner, { movies ->
                movies?.let {
                    when (movies) {
                        is Resource.Loading -> binding.progressBar1.visibility = View.VISIBLE
                        is Resource.Success -> {
                            binding.progressBar1.visibility = View.GONE
                            movieRecyclerViewAdapter.setMovies(movies.data)
                        }
                        is Resource.Error -> {
                            binding.progressBar1.visibility = View.GONE
                            Toast.makeText(context, "There is an error", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            })

            with(binding.recyclerViewMovie) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = movieRecyclerViewAdapter
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
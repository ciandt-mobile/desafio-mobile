package br.com.ciandt.application.fellini.ui.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import br.com.ciandt.application.fellini.MovieApplication;
import br.com.ciandt.application.fellini.R;
import br.com.ciandt.application.fellini.domain.movie.Movie;
import br.com.ciandt.application.fellini.service.MovieRepository;
import br.com.ciandt.application.fellini.service.callbacks.OnGettingMoviesCallback;
import br.com.ciandt.application.fellini.service.legacycode.MovieService;
import br.com.ciandt.application.fellini.ui.activity.MovieOverviewActivity;
import br.com.ciandt.application.fellini.ui.datagroups.GeneralAdapter;
import br.com.ciandt.application.fellini.ui.datagroups.OnClickListener;
import br.com.ciandt.application.fellini.ui.datagroups.viewholders.MovieViewHolder;
import retrofit2.Call;


/**
 * A simple {@link Fragment} subclass.
 */
public class MovieSectionFragment extends Fragment {

    private static final String TAG = "moviesectionfrag";

    private RecyclerView moviesListRecyclerView;
    private GeneralAdapter<Movie> generalAdapter;

    private MovieRepository repository;

    public MovieSectionFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        repository = MovieRepository.getInstance();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        String sort = getArguments().getString("sort");

        View itemView = inflater.inflate(R.layout.fragment_movie_section, container, false);
        moviesListRecyclerView = itemView.findViewById(R.id.movies_list);

        repository.getMovies(1, sort, new OnGettingMoviesCallback() {
            @Override
            public void onSuccess(int page, final List<Movie> movieList) {

                generalAdapter = new GeneralAdapter<Movie>(getContext(), movieList) {
                    @Override
                    public RecyclerView.ViewHolder setViewHolder(ViewGroup parent, int viewType) {
                        return new MovieViewHolder(LayoutInflater.from(getContext()).inflate(R.layout.movie_item_layout, parent, false), new OnClickListener() {
                            @Override
                            public void onItemClickListener(int position) {
                                Intent intent = new Intent(getContext(), MovieOverviewActivity.class);
                                intent.putExtra("movie_id", movieList.get(position).getId());
                                startActivity(intent);
                            }

                            @Override
                            public void onItemLongClickListener(int position) {

                            }
                        });
                    }

                    @Override
                    public void onBindData(RecyclerView.ViewHolder viewHolder, Movie item) {
                        MovieViewHolder movieViewHolder = (MovieViewHolder) viewHolder;
                        movieViewHolder.bind(item);
                    }
                };

                moviesListRecyclerView.setAdapter(generalAdapter);
            }

            @Override
            public void onError() {

            }

        });

        /*Call<MovieResponse> latestMovies = movieService.getMoviesFromPath(desiredPath, BuildConfig.THE_MOVIE_DATABASE_API_TOKEN);
        latestMovies.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {

                movies = response.body().getResults();

                generalAdapter = new GeneralAdapter<Movie>(getContext(), movies) {
                    @Override
                    public RecyclerView.ViewHolder setViewHolder(ViewGroup parent, int viewType) {
                        return new MovieViewHolder(LayoutInflater.from(getContext()).inflate(R.layout.movie_item_layout, parent, false), new OnClickListener() {
                            @Override
                            public void onItemClickListener(int position) {
                                Intent intent = new Intent(getActivity(), MovieOverviewActivity.class);
                                intent.putExtra("movie", movies.get(position));
                                startActivity(intent);
                            }

                            @Override
                            public void onItemLongClickListener(int position) {

                            }
                        });
                    }

                    @Override
                    public void onBindData(RecyclerView.ViewHolder viewHolder, Movie item) {
                        MovieViewHolder movieViewHolder = (MovieViewHolder) viewHolder;
                        movieViewHolder.bind(item);
                    }
                };

                moviesListRecyclerView.setAdapter(generalAdapter);

            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {

            }
        });
*/

        return itemView;
    }

}

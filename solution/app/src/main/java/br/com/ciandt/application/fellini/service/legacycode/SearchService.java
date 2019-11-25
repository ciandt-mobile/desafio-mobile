package br.com.ciandt.application.fellini.service.legacycode;

import br.com.ciandt.application.fellini.domain.movie.MovieResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface SearchService {

    @GET("search/movie")
    Call<MovieResponse> searchFor(@Path("query") String query, @Query("api_key") String apiKey);
}

package br.com.ciandt.application.fellini.service.legacycode;


import br.com.ciandt.application.fellini.domain.movie.GenreResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GenresService {

    @GET("genre/movie/list")
    Call<GenreResponse> getAllGenres(@Query("api_key") String apiKey);
}

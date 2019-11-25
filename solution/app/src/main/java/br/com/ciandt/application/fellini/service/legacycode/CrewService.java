package br.com.ciandt.application.fellini.service.legacycode;

import br.com.ciandt.application.fellini.domain.crew.Credit;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface CrewService {

    @GET("movie/{movie_id}/credits")
    Call<Credit> getMovieCredits(@Path("movie_id") int movieId, @Query("api_key") String apiKey);
}

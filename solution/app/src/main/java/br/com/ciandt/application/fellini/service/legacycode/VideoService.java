package br.com.ciandt.application.fellini.service.legacycode;

import br.com.ciandt.application.fellini.domain.movie.VideoResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface VideoService {

    @GET("movie/{movie_id}/videos")
    Call<VideoResponse> getMovieVideos(@Path("movie_id") int movieId, @Query("api_key") String apiKey);
}

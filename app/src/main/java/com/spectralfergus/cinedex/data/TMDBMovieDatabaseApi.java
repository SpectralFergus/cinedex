package com.spectralfergus.cinedex.data;

import java.util.List;

public interface TMDBMovieDatabaseApi {
    interface MoviesServiceCallback<T> {
        void onLoaded(T notes);
    }

    void getAllMovies(MoviesServiceCallback<List<TMDBMovie>> callback);

    void getMovie(String movieId, MoviesServiceCallback<TMDBMovie> callback);

    void saveMovie(TMDBMovie movie);
}

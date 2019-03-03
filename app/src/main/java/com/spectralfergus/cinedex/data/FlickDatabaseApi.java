package com.spectralfergus.cinedex.data;

import java.util.List;

public interface FlickDatabaseApi {
    interface MoviesServiceCallback<T> {
        void onLoaded(T notes);
    }

    void getAllMovies(MoviesServiceCallback<List<Flick>> callback);

    void getMovie(String movieId, MoviesServiceCallback<Flick> callback);

    void saveMovie(Flick movie);
}

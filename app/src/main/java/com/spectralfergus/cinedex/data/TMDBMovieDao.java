package com.spectralfergus.cinedex.data;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface TMDBMovieDao {
    @Insert
    void insert(TMDBMovie movie);

    @Update
    void update(TMDBMovie movie);

    @Delete
    void delete(TMDBMovie movie);

    @Query("DELETE FROM movie_table")
    void deleteAllCards();

    @Query("SELECT * FROM movie_table ORDER BY id")
    List<TMDBMovie> getAllMovies();

    @Query("Select * FROM movie_table WHERE id=:id")
    TMDBMovie getMovie(int id);
}

package com.spectralfergus.cinedex.data;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface FlickDao {

    @Insert
    void insert(Flick movie);

    @Update
    void update(Flick movie);

    @Delete
    void delete(Flick movie);

    @Query("DELETE FROM flick_table")
    void deleteAllCards();

    @Query("SELECT * FROM flick_table ORDER BY id")
    List<Flick> getAllFlicks();

    @Query("Select * FROM flick_table WHERE id=:id")
    Flick getMovie(int id);
}

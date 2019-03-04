package com.spectralfergus.cinedex.data;

import java.util.List;

import androidx.lifecycle.LiveData;
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
    void deleteAllFlicks();

    @Query("SELECT * FROM flick_table ORDER BY id")
    LiveData<List<Flick>> getAllFlicks();

    @Query("Select * FROM flick_table WHERE id=:id")
    Flick getFlick(int id);
}

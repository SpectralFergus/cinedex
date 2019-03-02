package com.spectralfergus.cinedex.data;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "movie_table")
public class TMDBMovie {
    @PrimaryKey(autoGenerate = true)
    private int id;

    private String name;
    private String desc;

    // Constructor allows room to re-create TMDBMovie objects from database
    public TMDBMovie(String name, String desc) {
        this.name = name;
        this.desc = desc;
    }

    // == getters ==
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }

    @Override
    public String toString() {
        return name;
    }

    // == setters ==
    // Only value that does not appear in constructor, required for Room to interface with TMDBMovie
    public void setId(int id) {
        this.id = id;
    }
}


package com.spectralfergus.cinedex.data;

import java.util.ArrayList;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverter;

@Entity(tableName = "flick_table")
public class Flick {
    @PrimaryKey(autoGenerate = true)
    private int id;

    private int vote_count;
    private int tmdb_id;
    private boolean video;
    private double vote_average;
    private String title;
    private double popularity;
    private String poster_path;
    private String original_language;
    private String original_title;
    private String genre_ids;
    private String backdrop_path;
    private boolean adult;
    private String overview;
    private String release_date;

    public Flick(int vote_count, int tmdb_id, boolean video, double vote_average, String title, double popularity, String poster_path, String original_language, String original_title, String genre_ids, String backdrop_path, boolean adult, String overview, String release_date) {
        this.vote_count = vote_count;
        this.tmdb_id = tmdb_id;
        this.video = video;
        this.vote_average = vote_average;
        this.title = title;
        this.popularity = popularity;
        this.poster_path = poster_path;
        this.original_language = original_language;
        this.original_title = original_title;
        this.genre_ids = genre_ids;
        this.backdrop_path = backdrop_path;
        this.adult = adult;
        this.overview = overview;
        this.release_date = release_date;
    }

    // == getters ==
    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getOverview() {
        return overview;
    }

    public int getVote_count() {
        return vote_count;
    }

    public int getTmdb_id() {
        return tmdb_id;
    }

    public boolean isVideo() {
        return video;
    }

    public double getVote_average() {
        return vote_average;
    }

    public double getPopularity() {
        return popularity;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public String getOriginal_language() {
        return original_language;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public String getGenre_ids() {
        return genre_ids;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public boolean isAdult() {
        return adult;
    }

    public String getRelease_date() {
        return release_date;
    }

    @Override
    public String toString() {
        return title;
    }

    // == setters ==
    // Only value that does not appear in constructor, required for Room to interface with Flick
    public void setId(int id) {
        this.id = id;
    }

    public static class GenreIdConverter {
        @TypeConverter
        public static String arrToStr(String[] strings) {
            StringBuilder sb = new StringBuilder();
            if (strings.length > 0) sb.append(strings[0]);
            for(int i = 1; i < strings.length; i++) {
                sb.append(",").append(strings[i]);
            }

            return sb.toString();
        }

        @TypeConverter
        public static ArrayList<String> strToArr(String csvStr) {
            ArrayList<String> genreIds = new ArrayList<>();

            for(String s : csvStr.split(",")) {
                genreIds.add(s);
            }

            return genreIds;
        }
    }
}


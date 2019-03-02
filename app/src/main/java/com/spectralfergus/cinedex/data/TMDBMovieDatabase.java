package com.spectralfergus.cinedex.data;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {TMDBMovie.class}, version = 1)
public abstract class TMDBMovieDatabase extends RoomDatabase {
    private static TMDBMovieDatabase instance;

    public abstract TMDBMovieDao tmdbMovieDao();

    public static synchronized TMDBMovieDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    TMDBMovieDatabase.class, "movie_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private TMDBMovieDao movieDao;
        public PopulateDbAsyncTask(TMDBMovieDatabase db) {
            this.movieDao = db.tmdbMovieDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            //todo: Instantiate date for the first time
            return null;
        }
    }
}

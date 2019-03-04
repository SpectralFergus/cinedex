package com.spectralfergus.cinedex.data;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Flick.class}, version = 1)
public abstract class FlickDatabase extends RoomDatabase {
    private static FlickDatabase instance;

    public abstract FlickDao flickDao();

    public static synchronized FlickDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    FlickDatabase.class, "flick_database")
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
        private FlickDao flickDao;
        public PopulateDbAsyncTask(FlickDatabase db) {
            this.flickDao = db.flickDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            //todo: Instantiate db for the first time
            return null;
        }
    }
}

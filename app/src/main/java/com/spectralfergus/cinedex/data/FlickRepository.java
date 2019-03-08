package com.spectralfergus.cinedex.data;

import android.app.Application;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import com.spectralfergus.cinedex.utils.JsonUtils;
import com.spectralfergus.cinedex.utils.NetworkUtils;

import org.json.JSONException;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import androidx.lifecycle.LiveData;

public class FlickRepository {
    private static final String TAG = FlickRepository.class.getSimpleName();

    private FlickDao flickDao;
    private LiveData<List<Flick>> flickList;

    public FlickRepository(Application application) {
        FlickDatabase db = FlickDatabase.getInstance(application);
        flickDao = db.flickDao();
        flickList = flickDao.getAllFlicks();
    }

    void refreshData() {

    }

    // == API Functions ==
    public void insert(Flick c) {
        new InsertFlickAsyncTask(flickDao).execute(c);
    }

    public void update(Flick c) {
        new UpdateFlickAsyncTask(flickDao).execute(c);
    }

    public void delete(Flick c) {
        new DeleteFlickAsyncTask(flickDao).execute(c);
    }

    public void deleteAllFlicks() {
        new DeleteAllFlicksAsyncTask(flickDao).execute();
    }

    public void fetchFlicksFromURL(String url) {
        new FetchFlicksFromURLAsyncTask(flickDao).execute(url);
    }

    public LiveData<List<Flick>> getFlickList() {
        return flickList;
    }

    // === Background Threads ===
    public static class InsertFlickAsyncTask extends AsyncTask<Flick, Void, Void> {
        private FlickDao flickDao;

        private InsertFlickAsyncTask(FlickDao flickDao) {
            this.flickDao = flickDao;
        }

        @Override
        protected Void doInBackground(Flick... flicks) {
            flickDao.insert(flicks[0]);
            return null;
        }
    }

    public static class UpdateFlickAsyncTask extends AsyncTask<Flick, Void, Void> {
        private FlickDao flickDao;

        private UpdateFlickAsyncTask(FlickDao flickDao) {
            this.flickDao = flickDao;
        }

        @Override
        protected Void doInBackground(Flick... flicks) {
            flickDao.update(flicks[0]);
            return null;
        }
    }

    public static class DeleteFlickAsyncTask extends AsyncTask<Flick, Void, Void> {
        private FlickDao flickDao;

        private DeleteFlickAsyncTask(FlickDao flickDao) {
            this.flickDao = flickDao;
        }

        @Override
        protected Void doInBackground(Flick... flicks) {
            flickDao.delete(flicks[0]);
            return null;
        }
    }

    public static class DeleteAllFlicksAsyncTask extends AsyncTask<Void, Void, Void> {
        private FlickDao flickDao;

        private DeleteAllFlicksAsyncTask(FlickDao flickDao) {
            this.flickDao = flickDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            flickDao.deleteAllFlicks();
            return null;
        }
    }

    // === NETWORK LOGIC TO RETRIEVE CARD DATA ===
    private static class FetchFlicksFromURLAsyncTask extends AsyncTask<String, Void, Void> {
        private static final String URI_BASE = "https://api.themoviedb.org/"; //3/discover/movie?primary_release_date.gte=2014-09-15&primary_release_date.lte=2014-10-22&page=1&api_key=64b6f3a69e5717b13ed8a56fe4417e71";
        FlickDao flickDao;

        private FetchFlicksFromURLAsyncTask(FlickDao flickDao) {
            this.flickDao = flickDao;
        }

        @Override
        protected Void doInBackground(String... strings) {
            // BUILD URL
            String urlString = String.valueOf(strings[0]);
            Uri uri = Uri.parse(URI_BASE)
                    .buildUpon()
                    .appendPath("3")
                    .appendPath("discover")
                    .appendPath("movie")
                    .appendQueryParameter("primary_release_date.gte", "2014-09-15")
                    .appendQueryParameter("primary_release_date.lte", "2014-10-22")
                    .appendQueryParameter("page", "1")
                    .appendQueryParameter("api_key", "64b6f3a69e5717b13ed8a56fe4417e71")
                    .build();
            URL url = null;
            try {
                url = new URL(uri.toString());
            } catch (MalformedURLException e) {
                e.printStackTrace();
                Log.e(TAG, "doInBackground: MalformedURL");
            }

            // QUERY FOR CARDS OVER NETWORK
            if (url == null) return null;
            try {
                String jsonResponse = NetworkUtils.getResponseFromHttpUrl(url);
                List<Flick> flicks = JsonUtils.parseFlicksFromJson(jsonResponse);
                for (Flick c : flicks) {
                    flickDao.insert(c);
                }
            } catch (IOException e) {
                e.printStackTrace();
                Log.e(TAG, "doInBackground: I/O err");
            } catch (JSONException e) {
                Log.e(TAG, "doInBackground: JSON err");
                e.printStackTrace();
            }
            return null;
        }
    }
}

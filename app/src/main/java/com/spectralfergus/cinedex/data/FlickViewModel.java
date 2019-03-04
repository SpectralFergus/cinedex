package com.spectralfergus.cinedex.data;

import android.app.Application;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class FlickViewModel extends AndroidViewModel {
    private FlickRepository repository;
    private LiveData<List<Flick>> FlickList;

    public FlickViewModel(@NonNull Application application) {
        super(application);
        repository = new FlickRepository(application);
        FlickList = repository.getFlickList();
        if (FlickList.getValue() == null || FlickList.getValue().size() <= 0) {
            deleteAllFlicks();
            fetchFlicks("todo: replace with url and custom params");
        }
    }

    public void insert(Flick c) {
        repository.insert(c);
    }

    public void update(Flick c) {
        repository.update(c);
    }

    public void delete(Flick c) {
        repository.delete(c);
    }

    public void deleteAllFlicks() {
        repository.deleteAllFlicks();
    }

    public void fetchFlicks(String urlString) {
        repository.fetchFlicksFromURL(urlString);
    }

    public LiveData<List<Flick>> getFlickList() {
        return FlickList;
    }
}

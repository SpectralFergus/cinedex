package com.spectralfergus.cinedex.data;

import android.app.Application;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class FlickViewModel extends AndroidViewModel {
    private FlickRepository repository;
    private LiveData<List<Flick>> FlickList;
    private MutableLiveData<Integer> iSelected;

    public FlickViewModel(@NonNull Application application) {
        super(application);
        repository = new FlickRepository(application);
        FlickList = repository.getFlickList();
        iSelected = new MutableLiveData<>();
        if (FlickList.getValue() == null || FlickList.getValue().size() <= 0) {
            deleteAllFlicks();
            fetchFlicks("");
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

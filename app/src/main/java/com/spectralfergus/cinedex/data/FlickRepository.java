package com.spectralfergus.cinedex.data;

import java.util.List;

import androidx.annotation.NonNull;

public class FlickRepository {

    private FlickDao flickDao;
    private List<Flick> flickList;

    interface LoadFlicksCallback {
        void onNotesLoaded(List<Flick> flicks);
    }

    interface GetFlickCallback {

        void onNoteLoaded(Flick flick);
    }

    public List<Flick> getFlicks() {
        return null;
    }

    void getFlick(@NonNull String noteId, @NonNull GetFlickCallback callback) {

    }

    void saveNote(@NonNull Flick flick) {

    }

    void refreshData() {

    }
}

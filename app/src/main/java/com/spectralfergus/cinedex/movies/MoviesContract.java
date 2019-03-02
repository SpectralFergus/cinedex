package com.spectralfergus.cinedex.movies;

import android.graphics.Movie;

import com.spectralfergus.cinedex.data.TMDBMovie;

import java.util.List;

import androidx.annotation.NonNull;

public interface MoviesContract {
    interface View {

        void setProgressIndicator(boolean active);

        void showNotes(List<TMDBMovie> notes);

        void showAddNote();

        void showNoteDetailUi(String noteId);
    }

    interface UserActionsListener {

        void loadNotes(boolean forceUpdate);

        void addNewNote();

        void openNoteDetails(@NonNull TMDBMovie requestedNote);
    }
}

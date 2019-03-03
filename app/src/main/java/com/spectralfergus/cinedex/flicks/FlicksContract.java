package com.spectralfergus.cinedex.flicks;

import com.spectralfergus.cinedex.data.Flick;

import java.util.List;

import androidx.annotation.NonNull;

public interface FlicksContract {
    interface View {

        void setProgressIndicator(boolean active);

        void showFlicks(List<Flick> flicks);

        void showAddFlick();

        void showFlickDetailUi(String flickId);
    }

    interface UserActionsListener {

        void loadFlicks(boolean forceUpdate);

        void addNewFlick();

        void openFlickDetails(@NonNull Flick requestedNote);
    }
}

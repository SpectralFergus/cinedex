package com.spectralfergus.cinedex.flicks;

import com.spectralfergus.cinedex.data.Flick;
import com.spectralfergus.cinedex.data.FlickRepository;

import java.util.List;

import androidx.annotation.NonNull;

public class FlicksPresenter implements FlicksContract.UserActionsListener {
    private final FlickRepository mFlickRepository;
    private final FlicksContract.View mFlicksView;

    public FlicksPresenter(@NonNull FlickRepository flickRepository, @NonNull FlicksContract.View flicksView) {
        mFlickRepository = flickRepository;
        mFlicksView = flicksView;
    }

    @Override
    public void loadFlicks(boolean forceUpdate) {
        mFlicksView.setProgressIndicator(true);

        List<Flick> flicks = mFlickRepository.getFlicks();
        mFlicksView.setProgressIndicator(false);
        mFlicksView.showFlicks(flicks);
    }

    @Override
    public void addNewFlick() {

    }

    @Override
    public void openFlickDetails(@NonNull Flick requestedFlick) {

    }
}

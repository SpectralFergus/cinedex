package com.spectralfergus.cinedex.flicks;

import android.graphics.Movie;

import com.spectralfergus.cinedex.data.Flick;
import com.spectralfergus.cinedex.data.FlickRepository;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class FlicksPresenterTest {

    private static Flick flickJohnWick = new Flick(9104, 245891, false, 7.1, "John Wick", 41.334,"/5vHssUeVe25bMrof1HyaPyWgaP.jpg", "en", "John Wick", "\"28\",\"53\"", "/umC04Cozevu8nn3JTDJ1pc7PVTn.jpg", false, "Ex-hitman John Wick comes out of retirement to track down the gangsters that took everything from him.", "2014-10-22");


    private static List<Flick> FLICKS = Arrays.asList(
            flickJohnWick
//            ,new Flick("Title2", "Description2"));
    );
    private FlicksPresenter mFlicksPresenter;
    @Mock
    private FlickRepository mFlickRepository;
    @Mock
    private FlicksContract.View mFlicksView;

//    @Captor
//    private ArgumentCaptor<>

    @Before
    public void setupMoviesPresenter() {
        MockitoAnnotations.initMocks(this);
        mFlicksPresenter = new FlicksPresenter(mFlickRepository, mFlicksView);
    }

    @Test
    public void loadNotesFromRepositoryAndLoadIntoView() {
        // dummy expected data
        when(mFlickRepository.getFlicks()).thenReturn(FLICKS);

        // Calls to Repository
        mFlicksPresenter.loadFlicks(true);
        verify(mFlickRepository).getFlicks();

        // Progress Indicator behavior
        InOrder inOrder = Mockito.inOrder(mFlicksView);
        inOrder.verify(mFlicksView).setProgressIndicator(true);
        inOrder.verify(mFlicksView).setProgressIndicator(false);

        // Calls to View
        verify(mFlicksView).showFlicks(FLICKS);
    }

    @Test
    public void clickOnFlick_ShowDetailScreen() {
        // Given a stubbed flick
        Flick requestedFlick = flickJohnWick;

        // When open flick details is requested
        mFlicksPresenter.openFlickDetails(requestedFlick);

        // Then flick detail UI is shown
        verify(mFlicksView).showFlickDetailUi(any(String.class));
    }
}
package com.spectralfergus.cinedex.flicks;

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

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class FlicksPresenterTest {

    private static List<Flick> FLICKS = Arrays.asList(
            new Flick(9104, 245891, false, 7.1, "John Wick", 41.334,"/5vHssUeVe25bMrof1HyaPyWgaP.jpg", "en", "John Wick", "\"28\",\"53\"", "/umC04Cozevu8nn3JTDJ1pc7PVTn.jpg", false, "Ex-hitman John Wick comes out of retirement to track down the gangsters that took everything from him.", "2014-10-22")
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
        // Given an initialized NotesPresenter with initialized notes
        // When loading of Notes is requested
        when(mFlickRepository.getFlicks()).thenReturn(FLICKS);

        mFlicksPresenter.loadFlicks(true);

        // Callback is captured and invoked with stubbed flicks
        verify(mFlickRepository).getFlicks();
//        mLoadFlicksCallbackCaptor.getValue().onFlicksLoaded(FLICKS);

        // Then progress indicator is hidden and flicks are shown in UI
        InOrder inOrder = Mockito.inOrder(mFlicksView);
        inOrder.verify(mFlicksView).setProgressIndicator(true);
        inOrder.verify(mFlicksView).setProgressIndicator(false);
        verify(mFlicksView).showFlicks(FLICKS);
    }
    
    @Test
    public void loadNotes() {
    }

    @Test
    public void addNewNote() {
    }

    @Test
    public void openNoteDetails() {
    }
}
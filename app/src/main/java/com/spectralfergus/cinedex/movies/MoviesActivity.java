package com.spectralfergus.cinedex.movies;

import android.content.Context;
import android.graphics.Movie;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.spectralfergus.cinedex.R;
import com.spectralfergus.cinedex.data.TMDBMovie;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

public class MoviesActivity extends AppCompatActivity {
    
    private MoviesContract.UserActionsListener mActionsListener;
    
    private MoviesAdapter mMoviesAdapter;

    MovieItemListener mItemListener = new MovieItemListener() {
        @Override
        public void onMovieClick(TMDBMovie clickedMovie) {
            mActionsListener.openNoteDetails(clickedMovie);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);

        mMoviesAdapter = new MoviesAdapter(new ArrayList<TMDBMovie>(), mItemListener);
//        mActionsListener = new MoviesPresenter(Injection.provideNotesRepository(), this);
    }

    private static class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.ViewHolder> {

        private List<TMDBMovie> mMovies;
        private MovieItemListener mItemListener;

        public MoviesAdapter(List<TMDBMovie> movies, MovieItemListener itemListener) {
            setList(movies);
            mItemListener = itemListener;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            Context context = parent.getContext();
            LayoutInflater inflater = LayoutInflater.from(context);
            View MovieView = inflater.inflate(R.layout.movie_item, parent, false);

            return new ViewHolder(MovieView, mItemListener);
        }

        @Override
        public void onBindViewHolder(ViewHolder viewHolder, int position) {
            TMDBMovie movie = mMovies.get(position);

    //        viewHolder.title.setText(Movie.getTitle());
    //        viewHolder.description.setText(Movie.getDescription());
        }

        public void replaceData(List<TMDBMovie> movies) {
            setList(movies);
            notifyDataSetChanged();
        }

        private void setList(List<TMDBMovie> movies) {
            mMovies = (movies != null) ? movies : new ArrayList<TMDBMovie>();
        }

        @Override
        public int getItemCount() {
            return mMovies.size();
        }

        public TMDBMovie getItem(int position) {
            return mMovies.get(position);
        }

        public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

            public TextView title;

            public TextView description;
            private MovieItemListener mItemListener;

            public ViewHolder(View itemView, MovieItemListener listener) {
                super(itemView);
                mItemListener = listener;
    //            title = (TextView) itemView.findViewById(R.id.movie_detail_title);
    //            description = (TextView) itemView.findViewById(R.id.movie_detail_description);
                itemView.setOnClickListener(this);
            }

            @Override
            public void onClick(View v) {
                int position = getAdapterPosition();
                TMDBMovie movie = getItem(position);
                mItemListener.onMovieClick(movie);

            }
        }

    }

    public interface MovieItemListener {
        void onMovieClick(TMDBMovie clickedMovie);
    }
}


package com.spectralfergus.cinedex.flicks;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.spectralfergus.cinedex.R;
import com.spectralfergus.cinedex.data.Flick;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

public class FlicksActivity extends AppCompatActivity implements FlicksContract.View {

    private FlicksContract.UserActionsListener mActionsListener;
    private MoviesAdapter mMoviesAdapter;

    MovieItemListener mItemListener = new MovieItemListener() {
        @Override
        public void onMovieClick(Flick clickedMovie) {
            mActionsListener.openFlickDetails(clickedMovie);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);

        mMoviesAdapter = new MoviesAdapter(new ArrayList<Flick>(), mItemListener);
//        mActionsListener = new FlicksPresenter(Injection.provideNotesRepository(), this);
    }

    @Override
    public void setProgressIndicator(boolean active) {

    }

    @Override
    public void showFlicks(List<Flick> flicks) {

    }

    @Override
    public void showAddFlick() {

    }

    @Override
    public void showFlickDetailUi(String flickId) {

    }

    private static class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.ViewHolder> {

        private List<Flick> mMovies;
        private MovieItemListener mItemListener;

        public MoviesAdapter(List<Flick> movies, MovieItemListener itemListener) {
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
            Flick movie = mMovies.get(position);

            //        viewHolder.title.setText(Movie.getTitle());
            //        viewHolder.description.setText(Movie.getDescription());
        }

        public void replaceData(List<Flick> movies) {
            setList(movies);
            notifyDataSetChanged();
        }

        private void setList(List<Flick> movies) {
            mMovies = (movies != null) ? movies : new ArrayList<Flick>();
        }

        @Override
        public int getItemCount() {
            return mMovies.size();
        }

        public Flick getItem(int position) {
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
                Flick movie = getItem(position);
                mItemListener.onMovieClick(movie);

            }
        }

    }

    public interface MovieItemListener {
        void onMovieClick(Flick clickedMovie);
    }
}


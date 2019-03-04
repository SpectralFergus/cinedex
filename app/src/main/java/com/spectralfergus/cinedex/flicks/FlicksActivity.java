package com.spectralfergus.cinedex.flicks;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.spectralfergus.cinedex.R;
import com.spectralfergus.cinedex.data.Flick;
import com.spectralfergus.cinedex.data.FlickViewModel;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class FlicksActivity extends AppCompatActivity {
    private static final String TAG = FlicksActivity.class.getSimpleName();
    private static final int NUM_COLS = 3;

    public interface FlickItemListener {
        void onFlickClick(Flick clickedMovie);
    }

    private FlicksAdapter mFlicksAdapter;
    private FlickViewModel mFlickModel;

    FlickItemListener mItemListener = new FlickItemListener() {
        @Override
        public void onFlickClick(Flick clickedMovie) {
            //todo: go to Details Screen
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);

        mFlicksAdapter = new FlicksAdapter(new ArrayList<Flick>(), mItemListener);

        RecyclerView mRecyclerView = findViewById(R.id.rv_movies_list);
        mRecyclerView.setAdapter(mFlicksAdapter);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, NUM_COLS));

        mFlickModel = ViewModelProviders.of(this).get(FlickViewModel.class);
        mFlickModel.getFlickList().observe(this, new Observer<List<Flick>>() {
            @Override
            public void onChanged(List<Flick> flicks) {
                showFlicks(flicks);
            }
        });
    }

    public void showFlicks(List<Flick> flicks) {
        mFlicksAdapter.replaceData(flicks);
    }

    public void showFlickDetailUi(String flickId) {
        //todo: go to details screen
//        Intent intent = new Intent(getApplicationContext(), FlickDetailActivity.class);
//        intent.putExtra(FlickDetailActivity.EXTRA_FLICK_ID, flickId);
//        startActivity(intent);
    }

    private static class FlicksAdapter extends RecyclerView.Adapter<FlicksAdapter.ViewHolder> {

        private List<Flick> mMovies = new ArrayList<>(); // avoids NullPointerExceptions
        private FlickItemListener mItemListener;
        private static final String POSTER_BASE_URL = "https://image.tmdb.org/t/p/w500";

        public FlicksAdapter(List<Flick> movies, FlickItemListener itemListener) {
            setList(movies);
            mItemListener = itemListener;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View MovieView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.movie_item, parent, false);

            return new ViewHolder(MovieView, mItemListener);
        }

        @Override
        public void onBindViewHolder(ViewHolder viewHolder, int position) {
            Flick curFlick = mMovies.get(position);
            viewHolder.bind(curFlick);
            //        viewHolder.title.setText(Movie.getTitle());
            //        viewHolder.description.setText(Movie.getDescription());
        }

        public void replaceData(List<Flick> flicks) {
            setList(flicks);
            notifyDataSetChanged();
        }

        private void setList(List<Flick> movies) {
            if (movies != null) mMovies = movies;
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
            public ImageView poster;

            private FlickItemListener mItemListener;

            public ViewHolder(View itemView, FlickItemListener listener) {
                super(itemView);
                mItemListener = listener;
                //            title = (TextView) itemView.findViewById(R.id.movie_detail_title);
                //            description = (TextView) itemView.findViewById(R.id.movie_detail_description);
                poster = itemView.findViewById(R.id.poster_image_view);
                itemView.setOnClickListener(this);
            }

            private void bind(Flick curFlick) {
                poster.setImageDrawable(null);
                Glide.with(itemView.getContext())
                        .load(POSTER_BASE_URL+curFlick.getPoster_path()).into(poster);
            }

            @Override
            public void onClick(View v) {
                int position = getAdapterPosition();
                Flick movie = getItem(position);
                mItemListener.onFlickClick(movie);

            }
        }
    }
}


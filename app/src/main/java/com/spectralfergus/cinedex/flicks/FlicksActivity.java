package com.spectralfergus.cinedex.flicks;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.spectralfergus.cinedex.R;
import com.spectralfergus.cinedex.data.Flick;
import com.spectralfergus.cinedex.data.FlickRepository;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class FlicksActivity extends AppCompatActivity implements FlicksContract.View {
    private static final int NUM_COLS = 3;

    public interface FlickItemListener {
        void onFlickClick(Flick clickedMovie);
    }

    private FlicksContract.UserActionsListener mActionsListener;
    private FlicksAdapter mFlicksAdapter;

    FlickItemListener mItemListener = new FlickItemListener() {
        @Override
        public void onFlickClick(Flick clickedMovie) {
            mActionsListener.openFlickDetails(clickedMovie);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);

        mFlicksAdapter = new FlicksAdapter(new ArrayList<Flick>(), mItemListener);
        mActionsListener = new FlicksPresenter(new FlickRepository(getApplication()), this);

        RecyclerView mRecyclerView = findViewById(R.id.rv_movies_list);
        mRecyclerView.setAdapter(mFlicksAdapter);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, NUM_COLS));

        mActionsListener.loadFlicks(true);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mActionsListener.loadFlicks(false);
    }

    @Override
    public void setProgressIndicator(boolean active) {

    }

    @Override
    public void showFlicks(List<Flick> flicks) {
        mFlicksAdapter.replaceData(flicks);
    }

    @Override
    public void showFlickDetailUi(String flickId) {
//        Intent intent = new Intent(getApplicationContext(), FlickDetailActivity.class);
//        intent.putExtra(FlickDetailActivity.EXTRA_FLICK_ID, flickId);
//        startActivity(intent);
    }

    private static class FlicksAdapter extends RecyclerView.Adapter<FlicksAdapter.ViewHolder> {

        private List<Flick> mMovies = new ArrayList<>(); // avoids NullPointerExceptions
        private FlickItemListener mItemListener;

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
//                Glide.with(itemView.getContext())
//                        .load("https://image.tmdb.org/t/p/w500/iiZZdoQBEYBv6id8su7ImL0oCbD.jpg").into(poster);
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


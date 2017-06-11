package com.pha.pipoca.view;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.pha.pipoca.R;
import com.pha.pipoca.clients.MovieDBService;
import com.pha.pipoca.model.api.ImagesPathBuilder;
import com.pha.pipoca.model.api.Movie;
import com.pha.pipoca.model.api.MovieTrailer;
import com.squareup.picasso.Picasso;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.RootContext;
import org.androidannotations.annotations.ViewById;

/**
 * Created by phabh_000 on 6/11/2017.
 */
@EViewGroup(R.layout.trailer_list_item)
public class TrailerItemView extends RelativeLayout {

    @ViewById(R.id.play_button)
    ImageView playButton;

    @ViewById(R.id.trailer_name)
    TextView trailerName;

    private MovieTrailer movieTrailer;

    @Click(R.id.trailer_item)
    void clickItem() {
        watchYoutubeVideo(movieTrailer.key);
    }

    public TrailerItemView(Context context) {
        super(context);
    }

    public void bind(MovieTrailer movieTrailer) {
        this.movieTrailer = movieTrailer;
        trailerName.setText(movieTrailer.name);
    }

    public void watchYoutubeVideo(String id){
        Intent appIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + id));
        Intent webIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.youtube.com/watch?v=" + id));
        try {
            getContext().startActivity(appIntent);
        } catch (ActivityNotFoundException ex) {
            getContext().startActivity(webIntent);
        }
    }
}

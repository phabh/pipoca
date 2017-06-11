package com.pha.pipoca.model.api;

/**
 * Created by phabh_000 on 6/10/2017.
 */

public class ImagesPathBuilder {
    public static String getPoster(Configuration configuration, Movie movie, int quality){
        StringBuilder builder = new StringBuilder(configuration.images.secureBaseUrl);

        if( quality > configuration.images.posterSizes.length-1 )
            quality = configuration.images.posterSizes.length-1;

        if( quality < 0 )
            quality = 0;

        builder.append(configuration.images.posterSizes[quality]);
        builder.append(movie.posterPath);
        return builder.toString();
    }
}

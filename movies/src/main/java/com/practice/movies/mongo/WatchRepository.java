package com.practice.movies.mongo;

import com.practice.movies.Movie;
import com.practice.movies.WatchList;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

public interface WatchRepository extends MongoRepository <WatchList,String> {

    Optional<WatchList> findByFavorMovies(Movie movie);

    @Query("{'favorMovies.imdbId': ?0}")
    Optional<WatchList> findByFavorMoviesImdbId(String imdbId);

}

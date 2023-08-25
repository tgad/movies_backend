package com.practice.movies;

import com.practice.movies.mongo.MovieRepository;
import com.practice.movies.mongo.WatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class watchService {

    @Autowired
    private WatchRepository watchRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private MovieRepository movieRepository;

    //need the function to delete and store the movie into the watchRepository

    public  List<WatchList> getWatchMovies() {

       return watchRepository.findAll();
    }

    public List<WatchList> addMovie(String imdbId,Boolean isFavor) {

        // Check if the movie exists
        Optional<Movie> movieOptional = movieRepository.findMovieByImdbId(imdbId);

        if (movieOptional.isPresent()) {
            Movie movie = movieOptional.get();

            // Check if there's already a WatchList with this movie
            Optional<WatchList> existingWatchList = watchRepository.findByFavorMoviesImdbId(movie.getImdbId());

            if (existingWatchList.isEmpty()) {
                // If there's no WatchList with this movie, create one
                WatchList newWatchList = new WatchList();
                newWatchList.setFavor(isFavor);
                newWatchList.setFavorMovies(movie);
                watchRepository.insert(newWatchList);
            }
        }

        return watchRepository.findAll();




    }


    //the delete method by using imdbId
    public List<WatchList> deleteMovie(String imdbId){


        //check the list has the movie or not
        Optional<Movie> movieOptional = movieRepository.findMovieByImdbId(imdbId);
        if (movieOptional.isPresent()) {
            Movie movie = movieOptional.get();

            Optional<WatchList> watchListOptional = watchRepository.findByFavorMoviesImdbId(movie.getImdbId());
            //then I need to delete this repository

            watchListOptional.ifPresent(watchList -> watchRepository.delete(watchList));
            //same code
            //if (watchListOptional.isPresent()) {
            // WatchList watchList = watchListOptional.get();
            // watchRepository.delete(watchList);}
            //}

        }
        return watchRepository.findAll();

    }



}

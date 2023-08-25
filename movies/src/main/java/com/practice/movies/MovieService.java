package com.practice.movies;


import com.practice.movies.mongo.MovieRepository;
import com.practice.movies.mongo.WatchRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;



import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MovieService {
    @Autowired
    private MovieRepository movieRepository;



    @Autowired
    private MongoTemplate mongoTemplate;


    public List<Movie> getAllMovies(){
        return movieRepository.findAll();
    }

    public Optional<Movie> singleMoive(String imdbId) {
        return movieRepository.findMovieByImdbId(imdbId);
    }






}

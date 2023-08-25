package com.practice.movies;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/v1/search")

public class serachController {


    @Autowired
    private SearchService searchService;



    @GetMapping
    public ResponseEntity<?> searchAllMovies(){
        return new ResponseEntity<>(searchService.getAllMovies(),HttpStatus.OK);
    }
    @GetMapping("/{query}")
    public ResponseEntity<List<Movie>> searchMovies(@PathVariable("query") String query) {
        List<Movie> movies = searchService.searchMovies(query);
        return new ResponseEntity<>(movies, HttpStatus.OK);
    }
}
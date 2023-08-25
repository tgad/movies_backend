package com.practice.movies;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
//@CrossOrigin(origins = {"http://localhost:3000"})
//@CrossOrigin(origins = "*")
//@CrossOrigin(origins = "http://localhost:3000")
@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/v1/movies")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @Autowired
    private  SearchService searchService;


    @GetMapping
    public ResponseEntity<?> allMovies(){
        if(movieService.getAllMovies().isEmpty()){
            return new ResponseEntity<>("No movie found", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(movieService.getAllMovies(), HttpStatus.OK);
//        return ResponseEntity.ok(movieService.getAllMovies());//is same to the above method;


//        return  ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        // the optional method in the service class
        //could get the null if the movie not exist
        //so in the single movie Get Request
        //I could check from the service whether it has or null
        //if null it return the  status of HttpStatus.Not_Found
        //The .build() method is used to build a new ResponseEntity object without a response body
//        if (movie != null) {
//            return ResponseEntity.ok(movie); // Movie found, return with HttpStatus.OK
//        } else {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // Movie not found, return with HttpStatus.NOT_FOUND
//        }

    }
//    @GetMapping("/search")
//    public ResponseEntity<List<Movie>> searchMovies(@RequestParam String query) {
//        List<Movie> movies = searchService.searchMovies(query);
//        return new ResponseEntity<>(movies, HttpStatus.OK);
//    }
//
//    @GetMapping("/searchAll")
//    public ResponseEntity<?> searchAllMovies(){
//        return new ResponseEntity<>(searchService.getAllMovies(),HttpStatus.OK);
//    }

    @GetMapping("/{Id}")
    public ResponseEntity<Optional<Movie>> getSingleId(@PathVariable("Id") String imdbId){

        return new ResponseEntity<Optional<Movie>>(movieService.singleMoive(imdbId),HttpStatus.OK);
    }



}

package com.practice.movies;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

//@CrossOrigin(origins = {"http://localhost:3000"})
//@CrossOrigin(origins = "*")
@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/v1/reviews")
public class ReviewController {
    @Autowired
    private  ReviewService reviewService;

    @PostMapping
    public ResponseEntity<Review> createReview(@RequestBody Map<String,String> payload){
        return new ResponseEntity<Review>(reviewService.createReview(payload.get("reviewBody"),payload.get("imdbId")), HttpStatus.CREATED);
    }

//    @GetMapping("/{movieId}")
//    public ResponseEntity<Optional<Review>> getReview(@PathVariable String movieId) {
//        return new ResponseEntity<Optional<Review>>(reviewService.getReview(movieId),HttpStatus.OK);
//    }


}

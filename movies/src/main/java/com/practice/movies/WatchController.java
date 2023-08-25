package com.practice.movies;


import co.elastic.clients.elasticsearch.nodes.Http;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000", methods = {RequestMethod.PUT,RequestMethod.GET,RequestMethod.POST,RequestMethod.DELETE})
@RequestMapping("/api/v1/watchList")
public class WatchController {

    @Autowired
    private watchService watchService;

    @PostMapping
    public ResponseEntity<List<WatchList>> addMovie(@RequestBody Map<String,Object> payload) {

        return  new ResponseEntity<List<WatchList>>(watchService.addMovie((String)payload.get("imdbId"),(Boolean) payload.get("isFavor")), HttpStatus.OK);

    }



    @DeleteMapping
    public ResponseEntity<List<WatchList>> delete(@RequestBody Map<String,String> payload) {

        return new ResponseEntity<List<WatchList>>(watchService.deleteMovie(payload.get("imdbId")),HttpStatus.OK);
    }
    //get all data
    @GetMapping
    public ResponseEntity<?> getWatchList() {

        if(watchService.getWatchMovies().isEmpty()){
            return new ResponseEntity<>("No movie found,please search and add", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(watchService.getWatchMovies(), HttpStatus.OK);
    }



}

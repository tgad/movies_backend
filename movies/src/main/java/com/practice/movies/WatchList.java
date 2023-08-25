package com.practice.movies;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;


@Document(collection = "watchList")
@Data
@AllArgsConstructor
public class WatchList {

    @Id
    private String id;


    private Movie favorMovies;

    private String addTimestamp;

    private boolean isFavor;


    public WatchList() {

        this.addTimestamp = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
                .withZone(ZoneId.systemDefault())
                .format(Instant.now());
        this.isFavor = false;
    }



}

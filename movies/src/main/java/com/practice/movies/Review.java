package com.practice.movies;

import com.mongodb.client.model.Collation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@Document(collection = "reviews")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Review {

    @Id
    private ObjectId id;

    private String body;
    //I create this time class, to get the timeStamp
    //when I create the new Review message
    // I could use review.reviewTimestamp
    //to show for the client;
//    private Instant reviewTimestamp;
    private String reviewTimestamp;



    public Review(String body) {
        this.body = body;
//        this.reviewTimestamp = Instant.now();
        this.reviewTimestamp = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
                .withZone(ZoneId.systemDefault())
                .format(Instant.now());
    }


}
